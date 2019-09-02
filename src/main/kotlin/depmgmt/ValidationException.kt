package dev.louisc.kake.depmgmt

class ValidationException(val errors: List<String>) : Exception() {

    override val message = "Errors:\n    ${errors.joinToString("\n    ")}"

}
