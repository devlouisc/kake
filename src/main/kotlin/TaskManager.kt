package dev.louisc.kake

object TaskManager {

    private val tasks = mutableMapOf<String, Task>()

    fun add(vararg tasks: Task): TaskManager {
        for (task in tasks) {
            this.tasks[task.name] = task
        }
        return this
    }

    fun remove(vararg names: String): TaskManager {
        for (name in names) {
            tasks.remove(name)
        }
        return this
    }

    fun list(): Set<String> {
        return tasks.keys
    }

    fun run(vararg names: String): TaskManager {
        for (name in names) {
            tasks[name]?.logic?.invoke()
        }
        return this
    }

}
