//
//  NewRecipeViewModel.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 10.08.21.
//
import SwiftUI
import shared

class NewRecipeViewModel: ObservableObject {
    
    private let logger = Logger(className: "NewRecipeViewModel")

    // Dependencies
    private let saveRecipe =  SaveRecipe()
    private let getRecipe = GetRecipe()
    private let deleteRecipeIngredient = DeleteRecipeIngredient()
    private let getIngredientsFromRecipe = GetIngredientsFromRecipe()
   

    // State
    @Published var state: NewRecipeState =  NewRecipeState()

    init(
        recipeId: Int
    ) {
        onTriggerEvent(event: NewRecipeEvents.GetRecipe(recipeId: Int32(recipeId)))
    }

    func onTriggerEvent(event: NewRecipeEvents){
        var currentRecipe = (self.state.recipe.copy() as! Recipe)
        
        switch event {
            case is NewRecipeEvents.GetRecipe:
                
                let recipeId = Int32((event as! NewRecipeEvents.GetRecipe).recipeId)
                
                let emptyRecipe = Recipe(
                    databaseId: 0,
                    name: "",
                    image: "",
                    cooking_time: 0,
                    cooking_time_unit: "",
                    recipeDescription: "",
                    portion: 0,
                    ingredients: [])
                currentRecipe = getRecipe.execute(recipeId: recipeId) ?? emptyRecipe
                
                
                self.updateState(recipe: currentRecipe)
                
            case is NewRecipeEvents.OnUpdateName:
                currentRecipe.name = (event as! NewRecipeEvents.OnUpdateName).name
                self.updateState(recipe: currentRecipe)

            case is NewRecipeEvents.OnUpdateImage:
                currentRecipe.image = (event as! NewRecipeEvents.OnUpdateImage).image
                self.updateState(recipe: currentRecipe)

            case is NewRecipeEvents.OnUpdateCookingTime:
                currentRecipe.cooking_time = Int32(exactly: (event as! NewRecipeEvents.OnUpdateCookingTime).cooking_time) as? KotlinInt
                self.updateState(recipe: currentRecipe)
                
            case is NewRecipeEvents.OnUpdateCookingTimeUnit:
                currentRecipe.cooking_time_unit = (event as! NewRecipeEvents.OnUpdateCookingTimeUnit).cooking_time_unit
                self.updateState(recipe: currentRecipe)
                
            case is NewRecipeEvents.OnUpdateDescription:
                currentRecipe.recipeDescription = (event as! NewRecipeEvents.OnUpdateDescription).description
                self.updateState(recipe: currentRecipe)
                
            case is NewRecipeEvents.OnAddIngredient:
                
                currentRecipe.databaseId = saveRecipe.execute(recipe: (event as! NewRecipeEvents.OnAddIngredient).recipe)
                self.updateState(recipe: currentRecipe)
                
          /* case is NewRecipeEvents.OnDeleteIngredient:
                if (deleteRecipeIngredient.execute(
                        state.value.recipe.databaseId!!,
                        event.ingredient.internalId!!
                    )
                ) {
                    val ingredients =
                        getIngredientsFromRecipe.execute(state.value.recipe.databaseId!!)
                    self.updateState(state.value.recipe.copy(ingredients = ingredients!!))
                }*/
            case is NewRecipeEvents.OnSaveRecipe:
                currentRecipe.databaseId = saveRecipe.execute(recipe: (event as! NewRecipeEvents.OnSaveRecipe).recipe)
                self.updateState(recipe: currentRecipe)

            default: doNothing()
        }
    }



    private func doNothing(){}

    private func updateState(
        recipe: Recipe? = nil
    ){
        let currentState = (self.state.copy() as! NewRecipeState)
        self.state = self.state.doCopy(
            changed: currentState.changed + 1,
            recipe:recipe ?? currentState.recipe
        )
    }
    


}
