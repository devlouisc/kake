package dev.louisc.kake

class TaskNotFoundException(name: String) : Exception(name)

object TaskManager {

    private val tasks = mutableMapOf<String, Task>()

    fun addTask(task: Task): TaskManager {
        tasks[task.name] = task
        return this
    }

    fun addTasks(vararg tasks: Task): TaskManager {
        for (task in tasks) {
            this.tasks[task.name] = task
        }
        return this
    }

    fun runTask(name: String) {
        val task = tasks.getOrElse(name) {
            throw TaskNotFoundException("Task not found: $name")
        }
        task.logic()
    }

}
