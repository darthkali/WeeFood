//
//  RecipeListScreen.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 11.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeListScreen: View {
    
    // depencies
    private let networkModule: NetworkModule
    private let cacheModule: CacheModule
    private let searchRecipesModule: SearchRecipesModule
    private let foodCategories: [FoodCategory]
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    
    init(
        networkModule: NetworkModule,
        cacheModule: CacheModule
    ){
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchRecipesModule(
            networkModule: self.networkModule,
            cacheModule: self.cacheModule
        )
        let foodCategoryUtil = FoodCategoryUtil()
        self.viewModel = RecipeListViewModel(
            searchRecipes: searchRecipesModule.searchRecipes,
            foodCategorieUtil: foodCategoryUtil
        )
        self.foodCategories = foodCategoryUtil.getAllFoodCategories()
    }
    
    
    var body: some View {
        
        NavigationView{
            VStack{
            SearchAppBar(
                query: viewModel.state.query,
                selectedCategory: viewModel.state.selectedCategory,
                foodCategories: foodCategories,
                onTriggerEvent:viewModel.onTriggerEvent
                
                //onTriggerEvent: {event in
                //    viewModel.onTriggerEvent(stateEvent: event)
                //}
            )
            List{
                ForEach(viewModel.state.recipes, id: \.self.id){recipe in
                    NavigationLink(
                        destination: Text("\(recipe.title)")
                    ){
                        RecipeCard(recipe: recipe)
                             .onAppear(perform: {
                                 if( viewModel.shouldQueryNextPage(recipe: recipe)){
                                     viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
                                     
                                 }
                         })
                       
                     }
                    .listRowInsets(EdgeInsets())
                    .padding(.top,10)
                    }
                }
            .listStyle(PlainListStyle())
            }
            .navigationBarHidden(true)
        }
    }
}

/*struct RecipeListScreen_Previews: PreviewProvider {
    static var previews: some View {
        RecipeListScreen()
    }
}*/
