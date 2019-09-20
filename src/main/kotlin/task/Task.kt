package dev.louisc.kake.task

abstract class TaskOrSchedule

data class Task(
    val name: String,
    val description: String = "",
    val subtasks: TaskOrSchedule = Schedule(Strategy.SERIES, listOf()),
    val logic: Logic = {}
) : TaskOrSchedule()

data class Schedule(
    val strategy: Strategy,
    val tasksOrSchedules: List<TaskOrSchedule>
) : TaskOrSchedule()

enum class Strategy {
    SERIES,
    PARALLEL
}

typealias Logic = () -> Unit

fun series(vararg tasksOrSchedules: TaskOrSchedule): Schedule {
    return Schedule(Strategy.SERIES, tasksOrSchedules.toList())
}

fun parallel(vararg tasksOrSchedules: TaskOrSchedule): Schedule {
    return Schedule(Strategy.PARALLEL, tasksOrSchedules.toList())
}
