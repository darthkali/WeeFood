package de.darthkali.weefood.domain.util.enums

enum class Weekday(val value: Int) {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}