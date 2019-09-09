package dev.louisc.kake.dependency.project

class ValidationException(val errors: List<String>) : Exception() {
    override val message = "Errors:\n    ${errors.joinToString("\n    ")}"
}
