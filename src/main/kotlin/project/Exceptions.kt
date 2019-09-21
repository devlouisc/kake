package dev.louisc.kake.project

class NotFoundException(message: String) : Exception(message)

class ValidationException(errors: List<String>) : Exception() {
    override val message = "Errors:\n    ${errors.joinToString("\n    ")}"
}
