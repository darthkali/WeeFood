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
    @Published var state: RecipeDetailState =  RecipeDetailState()

    init(
        recipeId: Int
    ) {
        onTriggerEvent(event: RecipeDetailEvents.GetRecipe(recipeId: Int32(recipeId)))
    }

    func onTriggerEvent(event: RecipeDetailEvents){
        var currentRecipe = (self.state.recipe.copy() as! Recipe)
        
        switch event {
            case is RecipeDetailEvents.GetRecipe:
                
                let recipeId = Int32((event as! RecipeDetailEvents.GetRecipe).recipeId)
                
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
                
            case is RecipeDetailEvents.OnUpdateName:
                currentRecipe.name = (event as! RecipeDetailEvents.OnUpdateName).name
                self.updateState(recipe: currentRecipe)

            case is RecipeDetailEvents.OnUpdateImage:
                currentRecipe.image = (event as! RecipeDetailEvents.OnUpdateImage).image
                self.updateState(recipe: currentRecipe)

            case is RecipeDetailEvents.OnUpdateCookingTime:
                currentRecipe.cooking_time = Int32(exactly: (event as! RecipeDetailEvents.OnUpdateCookingTime).cooking_time) as? KotlinInt
                self.updateState(recipe: currentRecipe)
                
            case is RecipeDetailEvents.OnUpdateCookingTimeUnit:
                currentRecipe.cooking_time_unit = (event as! RecipeDetailEvents.OnUpdateCookingTimeUnit).cooking_time_unit
                self.updateState(recipe: currentRecipe)
                
            case is RecipeDetailEvents.OnUpdateDescription:
                currentRecipe.recipeDescription = (event as! RecipeDetailEvents.OnUpdateDescription).description
                self.updateState(recipe: currentRecipe)
                
            case is RecipeDetailEvents.OnAddIngredient:
                
                currentRecipe.databaseId = saveRecipe.execute(recipe: (event as! RecipeDetailEvents.OnAddIngredient).recipe)
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
            case is RecipeDetailEvents.OnSaveRecipe:
                currentRecipe.databaseId = saveRecipe.execute(recipe: state.recipe)
                self.updateState(recipe: currentRecipe)

            default: doNothing()
        }
    }



    private func doNothing(){}

    private func updateState(
        recipe: Recipe? = nil
    ){
        let currentState = (self.state.copy() as! RecipeDetailState)
        self.state = self.state.doCopy(
            changed: currentState.changed + 1,
            recipe:recipe ?? currentState.recipe
        )
    }
    


}
