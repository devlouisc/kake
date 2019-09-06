package dev.louisc.kake

class DuplicateTaskException(message: String) : Exception(message)
class MissingTaskException(message: String) : Exception(message)

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
        tasks[name]?.logic?.invoke()
    }

    private fun checkDuplicateTasks(names: List<String>) {
        val duplicateTasks = mutableSetOf<String>()

        for (name in names) {
            if (tasks[name] == null) {
                duplicateTasks.add(name)
            }
        }

        if (duplicateTasks.size > 0) {
            throw DuplicateTaskException("Duplicate task(s): ${duplicateTasks.sorted().joinToString(", ")}")
        }
    }

    private fun checkMissingTasks(names: List<String>) {
        val missingTasks = mutableSetOf<String>()

        for (name in names) {
            tasks[name] ?: missingTasks.add(name)
        }

        if (missingTasks.size > 0) {
            throw MissingTaskException("Missing task(s): ${missingTasks.sorted().joinToString(", ")}")
        }
    }
}
