package de.darthkali.weefood.domain.util.enums

enum class CookingTimeUnit(val value: Int) {
    MINUTES(1),
    HOURS(2);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}