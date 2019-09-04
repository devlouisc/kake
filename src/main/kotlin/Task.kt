package dev.louisc.kake

typealias TaskLogic = () -> Unit

data class Task(
        val name: String,
        val logic: TaskLogic
)

data class TaskSchedule(
        val tasks: List<Task>,
        val parallel: Boolean
)

fun series(vararg tasks: Task): TaskSchedule {
    return TaskSchedule(tasks.toList(), false)
}

fun parallel(vararg tasks: Task): TaskSchedule {
    return TaskSchedule(tasks.toList(), true)
}
