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
        
        
    }
    
    func updateState(
        isLoading: Bool? = nil,
        page: Int? = nil,
        query: String? = nil
        //queue: Queue<GenericMessageInfo>? = nil //TODO Errorhandling
    ){
        
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            page: Int32(page ?? Int(currentState.page)),
            query: query ?? currentState.query,
            selectedCategory: currentState.selectedCategory,
            recipes: currentState.recipes)
    }
    
    
    
    
    
}
