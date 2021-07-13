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
    
    
    
    
    
    
    
}
