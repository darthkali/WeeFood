//
//  IngredientListScreen.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 11.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 14.0, *)
struct IngredientListScreen: View {

    private let networkModule: NetworkModule
    private let cacheModule: CacheModule
    private let searchIngredientsModule: SearchIngredientModule
    //private let foodCategories: [FoodCategory]

    @ObservedObject var viewModel: IngredientListViewModel

    init(
        networkModule: NetworkModule,
        cacheModule: CacheModule
    ) {
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchIngredientsModule = SearchIngredientModule(
            networkModule: self.networkModule,
            cacheModule: self.cacheModule
        )
        //let foodCategoryUtil = FoodCategoryUtil()
        self.viewModel = IngredientListViewModel(
            searchIngredients: searchIngredientsModule.searchIngredient
            //foodCategoryUtil: foodCategoryUtil
        )
       // self.foodCategories = foodCategoryUtil.getAllFoodCategories()
        // dismiss keyboard when drag starts
        UIScrollView.appearance().keyboardDismissMode = .onDrag
    }

    var body: some View {
        
        NavigationView{
         
            ZStack{
                VStack{
                    SearchAppBar(
                        query: viewModel.state.query,
                        //selectedCategory: viewModel.state.selectedCategory,
                        //foodCategories: foodCategories,
                        onTriggerEvent: { event in
                            viewModel.onTriggerEvent(stateEvent: event)
                        }
                    )
                    List{
                        ForEach(viewModel.state.ingredients, id: \.self.id){ ingredients in
                            ZStack{
                                VStack{
                                    IngredientCard(ingredient: ingredients)
                                        .onAppear(perform: {
                                            if viewModel.shouldQueryNextPage(ingredient: ingredients){
                                                viewModel.onTriggerEvent(stateEvent: IngredientListEvents.NextPage())
                                            }
                                        })
                                }
                                /*NavigationLink(
                                    destination: IngredientDetailScreen(
                                        ingredientId: Int(ingredient.id),
                                        cacheModule: self.cacheModule
                                    )
                                ){
                                    // workaround for hiding arrows
                                    EmptyView()
                                }.hidden().frame(width: 0)*/
                            }
                            .listRowInsets(EdgeInsets())
                            .padding(.top, 10)
                        }
                    }
                    .listStyle(PlainListStyle())
                    .background(Color.init(hex: 0xf2f2f2))
                }
                if viewModel.state.isLoading {
                    ProgressView("Searching ingredients...")
                }
            }
            .navigationBarHidden(true)
            
            /*
            .alert(isPresented: $viewModel.showDialog, content: {
                let first = viewModel.state.queue.peek()!
                return GenericMessageInfoAlert().build(
                    message: first,
                    onRemoveHeadMessage: {
                        viewModel.onTriggerEvent(stateEvent: RecipeListEvents.OnRemoveHeadMessageFromQueue())
                    }
                )
            })
            */
            
        }
    }
}

@available(iOS 14.0, *)
struct IngredientListScreen_Previews: PreviewProvider {
    static var previews: some View {
        IngredientListScreen(
            networkModule: NetworkModule(),
            cacheModule: CacheModule()
        )
    }
}
