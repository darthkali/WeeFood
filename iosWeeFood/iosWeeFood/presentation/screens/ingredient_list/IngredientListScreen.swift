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

    private let searchIngredient: SearchIngredient
    private let saveIngredient: SaveIngredient
    private let getAllIngredients: GetAllIngredients

    @ObservedObject var viewModel: IngredientListViewModel

    init() {
        self.searchIngredient = SearchIngredient()
        self.saveIngredient = SaveIngredient()
        self.getAllIngredients = GetAllIngredients()
        self.viewModel = IngredientListViewModel(
            searchIngredients: searchIngredient,
            saveIngredient: saveIngredient,
            getAllIngredients: getAllIngredients
        )
        // dismiss keyboard when drag starts
        UIScrollView.appearance().keyboardDismissMode = .onDrag
    }

    var body: some View {
        
        NavigationView{
         
            ZStack{
                VStack{
                    SearchAppBar(
                        query: viewModel.state.query,
                        onTriggerEvent: { event in
                            viewModel.onTriggerEvent(stateEvent: event)
                        }
                    )
                    List{
                        ForEach(viewModel.state.ingredients, id: \.self.id){ ingredient in
                            ZStack{
                                VStack{
                                    IngredientCard(
                                        ingredient: ingredient,
                                        onSaveIngredient: viewModel.saveIngredient,
                                        getAll: viewModel.getAllIngredients
                                    )
                                        .onAppear(perform: {
                                            if viewModel.shouldQueryNextPage(ingredient: ingredient){
                                                viewModel.onTriggerEvent(stateEvent: IngredientListEvents.NextPage())
                                            }
                                        })
                                }
                            }
                            .listRowInsets(EdgeInsets())
                            .padding(.top, 10)
                        }
                    }
                    .listStyle(PlainListStyle())
        
                }
                if viewModel.state.isLoading {
                    ProgressView("Searching ingredients...")
                }
            }
            .navigationBarHidden(true)
        }
    }
}

/*
@available(iOS 14.0, *)
struct IngredientListScreen_Previews: PreviewProvider {
    static var previews: some View {
        IngredientListScreen(
            networkModule: NetworkModule(),
            cacheModule: DatabaseModule()
        )
    }
}
 */
