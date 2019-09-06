package dev.louisc.kake

abstract class TaskOrSchedule

data class Task(
    val name: String,
    val description: String,
    val subtasks: Schedule,
    val logic: Logic
) : TaskOrSchedule() {
    constructor(name: String, description: String, subtasks: Schedule) :
        this(name, description, subtasks, {})

    constructor(name: String, description: String, logic: Logic) :
        this(name, description, Schedule(Strategy.SERIES, listOf()), logic)
}

data class Schedule(
    val parallel: Strategy,
    val tasks: List<TaskOrSchedule>
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
