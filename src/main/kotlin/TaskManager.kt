package dev.louisc.kake

class TaskNotFoundException(name: String) : Exception(name)

object TaskManager {

    private val tasks = mutableMapOf<String, Task>()

    fun add(vararg tasks: Task): TaskManager {
        for (task in tasks) {
            this.tasks[task.name] = task
        }
        return this
    }

    fun run(vararg names: String): TaskManager {
        for (name in names) {
            this.tasks[name]?.logic?.invoke()
        }
        return this
    }

}
