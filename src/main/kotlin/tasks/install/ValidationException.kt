package dev.louisc.kake.tasks.install

class ValidationException(val errors: List<String>) : Exception() {
    override val message = "Errors:\n    ${errors.joinToString("\n    ")}"
}
