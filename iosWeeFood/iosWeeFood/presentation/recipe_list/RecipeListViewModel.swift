//
//  RecipeListViewModel.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 13.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeListViewModel: ObservableObject {

    private let logger = Logger(className: "RecipeListViewModel")

    // Dependencies
    let searchRecipes: SearchRecipes
    let foodCategoryUtil: FoodCategoryUtil

    // State
    @Published var state: RecipeListState = RecipeListState()

    @Published var showDialog: Bool = false

    init(
        searchRecipes: SearchRecipes,
        foodCategoryUtil: FoodCategoryUtil
    ){
        self.searchRecipes = searchRecipes
        self.foodCategoryUtil = foodCategoryUtil
        onTriggerEvent(stateEvent: RecipeListEvents.NewSearch())
    }

    func onTriggerEvent(stateEvent: RecipeListEvents){
        switch stateEvent{
        case RecipeListEvents.NewSearch(): newSearch()
        case RecipeListEvents.NextPage(): nextPage()
        default:
            doNothing()
        }
    }

    func doNothing(){}

    private func newSearch() {
        resetSearchState()
        let currentState = (self.state.copy() as! RecipeListState)
        do{
            try searchRecipes.execute(
                page: Int32(currentState.page),
                query: currentState.query
            ).collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                if dataState != nil {
                    let data = dataState?.data
                    let message = dataState?.message
                    let loading = dataState?.isLoading ?? false

                    self.updateState(isLoading: loading)

                    if(data != nil){
                        self.appendRecipes(recipes: data as! [Recipe])
                    }
                    if(message != nil){
                        self.handleMessageByUIComponentType(message!.build())
                    }
                }else{
                    self.logger.log(msg: "DataState is nil")
                }
            })
        }catch{
            self.logger.log(msg: "\(error)")
        }
    }

    private func nextPage(){
        incrementPage()
        let currentState = (self.state.copy() as! RecipeListState)
        logger.log(msg: "NEXT PAGE \(currentState.page)")
        if(currentState.page > 1){
            do{
                try searchRecipes.execute(
                    page: Int32(currentState.page),
                    query: currentState.query
                ).collectCommon(
                    coroutineScope: nil,
                    callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        let loading = dataState?.isLoading ?? false

                        self.updateState(isLoading: loading)
                        if(data != nil){
                            self.appendRecipes(recipes: data as! [Recipe])
                        }
                        if(message != nil){
                            self.handleMessageByUIComponentType(message!.build())
                        }
                    }else{
                        self.logger.log(msg: "NextPage: DataState is nil")
                    }
                })
            }catch{
                self.logger.log(msg: "\(error)")
            }
        }
    }

    private func resetSearchState(){
        let currentState = (self.state.copy() as! RecipeListState)
        var foodCategory = currentState.selectedCategory
        if(foodCategory?.value != currentState.query){
            foodCategory = nil
        }
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: 1, // reset
            query: currentState.query,
            recipes: [], // reset
            selectedCategory: foodCategory, // Maybe reset (see logic above)
            foodCategories: currentState.foodCategories,
            bottomRecipe:  currentState.bottomRecipe,
            isQueryInProgress: currentState.isQueryInProgress,
            queue: currentState.queue
        )
    }

    func onUpdateSelectedCategory(foodCategory: FoodCategory?){
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(
            isLoading: currentState.isLoading,
            page: currentState.page,
            query: currentState.query,
            recipes: currentState.recipes,
            selectedCategory: foodCategory, // update selected FoodCategory
            foodCategories: currentState.foodCategories,
            bottomRecipe:  currentState.bottomRecipe,
            isQueryInProgress: currentState.isQueryInProgress,
            queue: currentState.queue
        )
        onUpdateQuery(query: foodCategory?.value ?? "")
        onTriggerEvent(stateEvent: RecipeListEvents.NewSearch())
    }

    func onUpdateQuery(query: String){
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
            selectedCategory: currentState.selectedCategory,
            foodCategories: currentState.foodCategories,
            bottomRecipe:  currentState.bottomRecipe,
            isQueryInProgress: currentState.isQueryInProgress,
            queue: currentState.queue
        )
        currentState = (self.state.copy() as! RecipeListState)
        self.onUpdateBottomRecipe(recipe: currentState.recipes[currentState.recipes.count - 1])
    }

    private func handleMessageByUIComponentType(_ message: GenericMessageInfo){
        switch message.uiComponentType{
        case UIComponentType.Dialog():
            appendToQueue(message: message)
        case UIComponentType.None():
            logger.log(msg: "\(message.description)")
        default:
            doNothing()
        }
    }

    func shouldQueryNextPage(recipe: Recipe) -> Bool {
        // check if looking at the bottom recipe
        // if lookingAtBottom -> proceed
        // if PAGE_SIZE * page <= recipes.length
        // if !queryInProgress
        // else -> do nothing
        let currentState = (self.state.copy() as! RecipeListState)
        if(recipe.id == currentState.bottomRecipe?.id){
            if(RecipeListState.Companion().RECIPE_PAGINATION_PAGE_SIZE * currentState.page <= currentState.recipes.count){
                if(!currentState.isQueryInProgress){
                    return true
                }
            }
        }
        return false
    }

    private func appendToQueue(message: GenericMessageInfo){
        let currentState = (self.state.copy() as! RecipeListState)
        let queue = currentState.queue
        let queueUtil = GenericMessageInfoQueueUtil() // prevent duplicates
        if !queueUtil.doesMessageAlreadyExistInQueue(queue: queue, messageInfo: message) {
            queue.add(element: message)
            updateState(queue: queue)
        }
    }

    /**
     *  Remove the head message from queue
     */
    func removeHeadFromQueue(){
        let currentState = (self.state.copy() as! RecipeListState)
        let queue = currentState.queue
        do {
            try queue.remove()
            updateState(queue: queue)
        }catch{
            self.logger.log(msg: "\(error)")
        }
    }

    /**
     *  Not everything can be conveniently updated with this function.
     *  Things like recipes, selectedCategory must have their own functions.
     */
    private func updateState(
        isLoading: Bool? = nil,
        page: Int? = nil,
        query: String? = nil,
        bottomRecipe: Recipe? = nil,
        isQueryInProgress: Bool? = nil,
        queue: Queue<GenericMessageInfo>? = nil
    ){
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            page: Int32(page ?? Int(currentState.page)),
            query: query ?? currentState.query,
            recipes: currentState.recipes ,
            selectedCategory: currentState.selectedCategory,
            foodCategories: currentState.foodCategories,
            bottomRecipe:  bottomRecipe ?? currentState.bottomRecipe,
            isQueryInProgress: isQueryInProgress ?? currentState.isQueryInProgress,
            queue: queue ?? currentState.queue
        )
        shouldShowDialog()
    }

    func shouldShowDialog(){
        let currentState = (self.state.copy() as! RecipeListState)
        showDialog = currentState.queue.items.count > 0
    }
}
