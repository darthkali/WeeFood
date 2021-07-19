//
//  RecipeListViewModel.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 13.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeListViewModel : ObservableObject{

    //Dependencies
    let searchRecipes : SearchRecipes
    let foodCatergorieUtil: FoodCategoryUtil
    

    @Published var state: RecipeListState = RecipeListState()
    
    
    init(
        searchRecipes: SearchRecipes,
        foodCategorieUtil: FoodCategoryUtil
    ){
        self.searchRecipes = searchRecipes
        self.foodCatergorieUtil = foodCategorieUtil
        onTriggerEvent(stateEvent: RecipeListEvents.LoadRecipes())
        
    }
    
    func onTriggerEvent(stateEvent: RecipeListEvents){
        switch stateEvent{
        case is RecipeListEvents.LoadRecipes:
            loadRecipies()
        case is RecipeListEvents.NewSearch:
            newSearch()
        case is RecipeListEvents.NextPage:
            nextPage()
        case is RecipeListEvents.OnUpdateQuery:
            onUpdateQuery(
                query: (stateEvent as! RecipeListEvents.OnUpdateQuery).query
            )
        case is RecipeListEvents.OnSelectCategory:
            doNothing()
        //case is RecipeListEvents.OnRemoveHeadMessageFromQueue:
        //    doNothing()
        default:
            doNothing()
        }
    }
    
    
    
    
    func updateState(
        isLoading: Bool? = nil,
        page: Int? = nil,
        query: String? = nil,
        bottomRecipe: Recipe? = nil
        //queue: Queue<GenericMessageInfo>? = nil //TODO Errorhandling
    ){
        
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            page: Int32(page ?? Int(currentState.page)),
            query: query ?? currentState.query,
            selectedCategory: currentState.selectedCategory,
            recipes: currentState.recipes,
            bottomRecipe: bottomRecipe ?? currentState.bottomRecipe
        )
    
    }
    
    private func newSearch(){
        resetSearchState()
        loadRecipies()
    }
    
    private func nextPage(){
        let currentState = self.state.copy() as! RecipeListState
        updateState(page: Int(currentState.page) +  1)
        loadRecipies()
    }
    
    private func loadRecipies(){
        let currentState = (self.state.copy() as! RecipeListState)
        do{
            try searchRecipes.execute(
                page: Int32(currentState.page),
                query: currentState.query
            ).collectCommon(
                coroutineScope: nil,
                callback: {dataState in
                    if dataState != nil{
                        let data = dataState?.data
                        let message = dataState?.message
                        let loading = dataState?.isLoading ?? false
                        
                        self.updateState(isLoading: loading)
                        
                        if data != nil{
                            self.appendRecipes(recipes: data as! [Recipe])
                        }
                        
                        if message != nil{
                            //TODO: ErrorHandling: self.handleMessageByUIComponentType(message!.build())
                        }
                    }
                    
                }
            )
        }catch{
            print("\(error)")
        }
    }
    
    
    private func resetSearchState(){
        let currentState = (self.state.copy() as! RecipeListState)
        var foodCatergory = currentState.selectedCategory
        if(foodCatergory?.value != currentState.query){
            foodCatergory = nil
        }
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: 1,
            query: currentState.query,
            selectedCategory: foodCatergory,
            recipes: [],
            bottomRecipe: currentState.bottomRecipe
            //queue: currentState.queue
            )
    }
    
    private func onUpdateQuery(query:String){
        updateState(query: query)
    }
    
    
    private func onUpdateBottomRecipe(recipe: Recipe){
        updateState(bottomRecipe: recipe)
    }
    
    private func appendRecipes(recipes: [Recipe]){
        var currentState = (self.state.copy() as! RecipeListState)
        var currentRecipes = currentState.recipes
        currentRecipes.append(contentsOf: recipes)
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: currentState.page,
            query: currentState.query,
            selectedCategory: currentState.selectedCategory,
            recipes: currentRecipes,
            bottomRecipe: currentState.bottomRecipe
            //queue: currentState.queue
            )
        currentState = self.state.copy() as! RecipeListState
        self.onUpdateBottomRecipe(recipe: currentState.recipes[currentState.recipes.count - 1])
        
    }
    
    func shouldQueryNextPage(recipe: Recipe) -> Bool{
        let currentState = self.state.copy() as! RecipeListState
        if(recipe.id == currentState.bottomRecipe?.id){
            if(RecipeListState.Companion().RECIPE_PAGINATION_PAGE_SIZE *
                currentState.page <= currentState.recipes.count){
                if(!currentState.isLoading){
                    return true
                }
            }
        }
        return false
    }
    
    //private func handleMessageByUIComponentType(_ message: GenericMessageInfo){
      //TODO ErrorHandling
    //}
        
    func doNothing(){
        
    }
    
    
    
    
    
}
