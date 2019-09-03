package dev.louisc.kake

typealias TaskLogic = () -> Unit

data class Task(
        val name: String,
        val logic: TaskLogic,
        val subtasks: List<Task> = listOf(),
        val parallel: Boolean = false
)

fun series(name: String, vararg tasks: Task): Task {
    return Task(name, {}, tasks.toList(), false)
}

fun parallel(name: String, vararg tasks: Task): Task {
    return Task(name, {}, tasks.toList(), true)
}
