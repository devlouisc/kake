package dev.louisc.kake.task

class DuplicateTaskException(message: String) : Exception(message)
class MissingTaskException(message: String) : Exception(message)
class NotTaskOrScheduleException(message: String) : Exception(message)
