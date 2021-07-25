//
//  RecipeListViewModel.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 13.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class IngredientListViewModel: ObservableObject {

    private let logger = Logger(className: "RecipeListViewModel")

    // Dependencies
    let searchIngredients: SearchIngredient
    //let foodCategoryUtil: FoodCategoryUtil

    // State
    @Published var state: IngredientListState = IngredientListState()

    //@Published var showDialog: Bool = false

    init(
        searchIngredients: SearchIngredient
        //foodCategoryUtil: FoodCategoryUtil
    ){
        self.searchIngredients = searchIngredients
        //self.foodCategoryUtil = foodCategoryUtil
        onTriggerEvent(stateEvent: IngredientListEvents.LoadIngredient())
    }

    func onTriggerEvent(stateEvent: IngredientListEvents){
        switch stateEvent {
        case is IngredientListEvents.LoadIngredient:
            loadIngredients()
        case is IngredientListEvents.NewSearch:
            newSearch()
        case is IngredientListEvents.NextPage:
            nextPage()
        case is IngredientListEvents.OnUpdateQuery:
            onUpdateQuery(query: (stateEvent as! IngredientListEvents.OnUpdateQuery).query)
        //case is RecipeListEvents.OnSelectCategory:
          //  onUpdateSelectedCategory(foodCategory: (stateEvent as! RecipeListEvents.OnSelectCategory).category)
        //case RecipeListEvents.OnRemoveHeadMessageFromQueue():
          //  removeHeadFromQueue()
        default:
            doNothing()
        }
    }

    func doNothing(){}

    private func loadIngredients(){
        let currentState = (self.state.copy() as! IngredientListState)
        do{
            try searchIngredients.execute(
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
                        self.appendIngredients(ingredients: data as! [Ingredient])
                    }
                   // if(message != nil){
                     //   self.handleMessageByUIComponentType(message!.build())
                    //}
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
        loadIngredients()
    }

    private func nextPage(){
        incrementPage()
        loadIngredients()
    }

    private func resetSearchState(){
        let currentState = (self.state.copy() as! IngredientListState)
        //var foodCategory = currentState.selectedCategory
       // if(foodCategory?.value != currentState.query){
         //   foodCategory = nil
        //}
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: 1, // reset
            query: currentState.query,
            ingredients: [], // reset
            //selectedCategory: foodCategory, // Maybe reset (see logic above)
            bottomIngredient:  currentState.bottomIngredient
            //queue: currentState.queue
        )
    }



    private func onUpdateQuery(query: String){
        updateState(query: query)
    }

    private func onUpdateBottomIngredient(ingredient: Ingredient){
        updateState(bottomIngredient: ingredient)
    }

    private func incrementPage(){
        let currentState = (self.state.copy() as! IngredientListState)
        updateState(page: Int(currentState.page) + 1)
    }

    private func appendIngredients(ingredients: [Ingredient]){
        var currentState = (self.state.copy() as! IngredientListState)
        var currentIngredients = currentState.ingredients
        currentIngredients.append(contentsOf: ingredients)
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: currentState.page,
            query: currentState.query,
            ingredients: currentIngredients, // update recipes
            bottomIngredient:  currentState.bottomIngredient
        )
        currentState = (self.state.copy() as! IngredientListState)
        
        if(currentState.ingredients.count != 0){
            self.onUpdateBottomIngredient(ingredient: currentState.ingredients[currentState.ingredients.count - 1 ])
        }
        
    }


    func shouldQueryNextPage(ingredient: Ingredient) -> Bool {
        // check if looking at the bottom recipe
        // if lookingAtBottom -> proceed
        // if PAGE_SIZE * page <= recipes.length
        // if !queryInProgress
        // else -> do nothing
        let currentState = (self.state.copy() as! IngredientListState)
        if(ingredient.id == currentState.bottomIngredient?.id){
            if(IngredientListState.Companion().RECIPE_PAGINATION_PAGE_SIZE * currentState.page <= currentState.ingredients.count){
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
        bottomIngredient: Ingredient? = nil
    ){
        let currentState = (self.state.copy() as! IngredientListState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            page: Int32(page ?? Int(currentState.page)),
            query: query ?? currentState.query,
            ingredients: currentState.ingredients ,
            bottomIngredient:  bottomIngredient ?? currentState.bottomIngredient
        )
    }
}


