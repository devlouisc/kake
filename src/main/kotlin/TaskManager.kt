package dev.louisc.kake

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DuplicateTaskException(message: String) : Exception(message)
class MissingTaskException(message: String) : Exception(message)
class NotTaskOrScheduleException(message: String) : Exception(message)

object TaskManager {
    internal val tasks = mutableMapOf<String, Task>()

    fun add(vararg tasks: Task) {
        checkDuplicateTasks(tasks.map { it.name })
        for (task in tasks) {
            this.tasks[task.name] = task
        }
    }

    fun list(): List<Task> {
        return tasks.values.toList().sortedBy { it.name }
    }

    fun run(name: String) {
        checkMissingTasks(listOf(name))
        runTaskOrSchedule(tasks[name]!!)
    }

    private fun checkDuplicateTasks(names: List<String>) {
        val duplicateTasks = mutableSetOf<String>()

        for (name in names) {
            if (tasks[name] == null) {
                duplicateTasks.add(name)
            }
        }

        if (duplicateTasks.isNotEmpty()) {
            throw DuplicateTaskException("Duplicate task(s): ${duplicateTasks.sorted().joinToString(", ")}")
        }
    }

    private fun checkMissingTasks(names: List<String>) {
        val missingTasks = mutableSetOf<String>()

        for (name in names) {
            tasks[name] ?: missingTasks.add(name)
        }

        if (missingTasks.isNotEmpty()) {
            throw MissingTaskException("Missing task(s): ${missingTasks.sorted().joinToString(", ")}")
        }
    }

    private fun runTaskOrSchedule(taskOrSchedule: TaskOrSchedule) {
        when (taskOrSchedule) {
            is Task -> {
                val task: Task = taskOrSchedule
                runTaskOrSchedule(task.subtasks)
                task.logic()
            }

            is Schedule -> {
                val schedule: Schedule = taskOrSchedule
                if (schedule.tasksOrSchedules.isNotEmpty()) {
                    when (schedule.strategy) {
                        Strategy.SERIES ->
                            schedule.tasksOrSchedules.forEach { runTaskOrSchedule(it) }

                        Strategy.PARALLEL ->
                            runBlocking {
                                schedule.tasksOrSchedules.map {
                                    launch { runTaskOrSchedule(it) }
                                }
                            }
                    }
                }
            }

            else ->
                throw NotTaskOrScheduleException("Encountered type that is not Task or Schedule: ${taskOrSchedule::class}")
        }
    }
}
