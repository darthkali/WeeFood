package de.darthkali.weefood.shared.domain.util

sealed class UIComponentType{

    object Dialog: UIComponentType()

    object None: UIComponentType()
}