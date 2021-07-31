package de.darthkali.weefood.domain.util.enums

enum class IngredientUnit(val value: Int) {
    TEASPOON(1),
    TABLESPOON(2),
    MILLILITER(3),
    LITER(4),
    CUP(5),
    GRAM(6),
    KILOGRAM(7);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}
