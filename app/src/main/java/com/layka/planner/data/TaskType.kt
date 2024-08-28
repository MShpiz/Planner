package com.layka.planner.data

enum class TaskType {
    DEFAULT {
        override fun toString(): String {
            return " "
        }
    },
    DAILY,
    WEEKLY,
}
