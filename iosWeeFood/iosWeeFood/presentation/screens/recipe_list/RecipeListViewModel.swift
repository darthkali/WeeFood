//
//  IngredientListViewModel.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 13.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeListViewModel: ObservableObject {

    private let searchRecipe = SearchRecipes()
    private let logger = Logger(className: "RecipeListViewModel")

    // State
    @Published var state: RecipeListState = RecipeListState()

    init(){onTriggerEvent(stateEvent: RecipeListEvents.LoadRecipe())}

    func onTriggerEvent(stateEvent: RecipeListEvents){
        switch stateEvent {
        case is RecipeListEvents.LoadRecipe:
            loadRecipes()
        case is RecipeListEvents.NewSearch:
            newSearch()
        case is RecipeListEvents.NextPage:
            nextPage()
        case is RecipeListEvents.OnUpdateQuery:
            onUpdateQuery(query: (stateEvent as! RecipeListEvents.OnUpdateQuery).query)
        default:
            doNothing()
        }
    }

    func doNothing(){}

    private func loadRecipes(){
        let currentState = (self.state.copy() as! RecipeListState)
        do{
            try searchRecipe.execute(
                query: currentState.query,
                page: Int32(currentState.page)
            ).collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                if dataState != nil {
                    let data = dataState?.data
                    //let message = dataState?.message
                    let loading = dataState?.isLoading ?? false

                    self.updateState(isLoading: loading)

                    if(data != nil){
                        self.appendRecipes(recipes: data as! [Recipe])
                    }
                }else{
                    self.logger.log(msg: "DataState is nil")
                }
            })
        }catch{
            self.logger.log(msg: "\(error)")
        }
    }

    private func newSearch() {
        resetSearchState()
        loadRecipes()
    }

    private func nextPage(){
        incrementPage()
        loadRecipes()
    }

    private func resetSearchState(){
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: 1, // reset
            query: currentState.query,
            recipes: [], // reset
            bottomRecipe:  currentState.bottomRecipe
        )
    }



    private func onUpdateQuery(query: String){
        updateState(query: query)
    }

    private func onUpdateBottomRecipe(recipe: Recipe){
        updateState(bottomRecipe: recipe)
    }

    private func incrementPage(){
        let currentState = (self.state.copy() as! RecipeListState)
        updateState(page: Int(currentState.page) + 1)
    }

    private func appendRecipes(recipes: [Recipe]){
        var currentState = (self.state.copy() as! RecipeListState)
        var currentRecipes = currentState.recipes
        currentRecipes.append(contentsOf: recipes)
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: currentState.page,
            query: currentState.query,
            recipes: currentRecipes, // update recipes
            bottomRecipe:  currentState.bottomRecipe
        )
        currentState = (self.state.copy() as! RecipeListState)
        
        if(currentState.recipes.count != 0){
            self.onUpdateBottomRecipe(recipe: currentState.recipes[currentState.recipes.count - 1 ])
        }
        
    }


    func shouldQueryNextPage(recipe: Recipe) -> Bool {
        // check if looking at the bottom recipe
        // if lookingAtBottom -> proceed
        // if PAGE_SIZE * page <= recipes.length
        // if !queryInProgress
        // else -> do nothing
        let currentState = (self.state.copy() as! RecipeListState)
        if(recipe.databaseId == currentState.bottomRecipe?.databaseId){
            if(RecipeListState.Companion().RECIPE_PAGINATION_PAGE_SIZE * currentState.page <= currentState.recipes.count){
                if(!currentState.isLoading){
                    return true
                }
            }
        }
        return false
    }

    /**
     *  Not everything can be conveniently updated with this function.
     *  Things like recipes, selectedCategory must have their own functions.
     */
    private func updateState(
        isLoading: Bool? = nil,
        page: Int? = nil,
        query: String? = nil,
        bottomRecipe: Recipe? = nil
    ){
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            page: Int32(page ?? Int(currentState.page)),
            query: query ?? currentState.query,
            recipes: currentState.recipes ,
            bottomRecipe:  bottomRecipe ?? currentState.bottomRecipe
        )
    }
}



