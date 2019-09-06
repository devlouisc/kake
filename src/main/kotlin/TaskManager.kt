package dev.louisc.kake

class DuplicateTaskException(message: String) : Exception(message)
class MissingTaskException(message: String) : Exception(message)

object TaskManager {
    private val tasks = mutableMapOf<String, Task>()

    fun add(vararg tasks: Task) {
        checkDuplicateTasks(*tasks.map { it.name }.toTypedArray())
        for (task in tasks) {
            this.tasks[task.name] = task
        }
    }

    fun replace(vararg tasks: Task) {
        checkMissingTasks(*tasks.map { it.name }.toTypedArray())
        for (task in tasks) {
            this.tasks[task.name] = task
        }
    }

    fun addOrReplace(vararg tasks: Task) {
        for (task in tasks) {
            this.tasks[task.name] = task
        }
    }

    fun remove(vararg names: String) {
        checkMissingTasks(*names)
        for (name in names) {
            tasks.remove(name)
        }
    }

    fun list(): List<Task> {
        return tasks.values.toList().sortedBy { it.name }
    }

    fun run(vararg names: String) {
        checkMissingTasks(*names)
        for (name in names) {
            tasks[name]?.logic?.invoke()
        }
    }

    private fun checkDuplicateTasks(vararg names: String) {
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

    private fun checkMissingTasks(vararg names: String) {
        val missingTasks = mutableSetOf<String>()

        for (name in names) {
            tasks[name] ?: missingTasks.add(name)
        }

        if (missingTasks.size > 0) {
            throw MissingTaskException("Missing task(s): ${missingTasks.sorted().joinToString(", ")}")
        }
    }
}
