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
    private let databaseModule: DatabaseModule
    private let searchIngredientsModule: SearchIngredientModule
    private let saveIngredientsModuel: SaveIngredientModule
    private let getAllIngredientsModule: GetAllIngredientsModule

    @ObservedObject var viewModel: IngredientListViewModel

    init(
        networkModule: NetworkModule,
        cacheModule: DatabaseModule
    ) {
        self.networkModule = networkModule
        self.databaseModule = cacheModule
        self.saveIngredientsModuel = SaveIngredientModule(databaseModule: self.databaseModule)
        self.getAllIngredientsModule = GetAllIngredientsModule(databaseModule: self.databaseModule)
        self.searchIngredientsModule = SearchIngredientModule(
            networkModule: self.networkModule,
            databaseModule: self.databaseModule
        )
        self.viewModel = IngredientListViewModel(
            searchIngredients: searchIngredientsModule.searchIngredient,
            saveIngredient: saveIngredientsModuel.saveIngredient,
            getAllIngredients: getAllIngredientsModule.getAllIngredient
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
                    .background(Color.init(hex: 0xf2f2f2))
                }
                if viewModel.state.isLoading {
                    ProgressView("Searching ingredients...")
                }
            }
            .navigationBarHidden(true)
        }
    }
}

@available(iOS 14.0, *)
struct IngredientListScreen_Previews: PreviewProvider {
    static var previews: some View {
        IngredientListScreen(
            networkModule: NetworkModule(),
            cacheModule: DatabaseModule()
        )
    }
}
