package dev.louisc.kake.task

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskManagerTest {

    @BeforeEach
    fun beforeEach() {
        TaskManager.tasks.clear()
    }

    @Nested
    inner class Add {

        @Test
        fun `should add provided tasks`() {
        }

        @Test
        fun `should throw an exception if any provided tasks already exist`() {
        }

        @Test
        fun `should be atomic, either all task are added or no tasks are added`() {
        }
    }

    @Nested
    inner class List {

        @Test
        fun `should list all tasks`() {
        }
    }

    @Nested
    inner class Run {

        @Test
        fun `should run specified task`() {
        }

        @Test
        fun `should throw an exception if specified task is missing`() {
        }

        @Test
        fun `should bubble up an exception if specified task throws an exception`() {
        }
    }
}
