//
//  RecipeListScreen.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 26.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//
import SwiftUI
import shared

@available(iOS 14.0, *)
struct RecipeListScreen: View {

    @ObservedObject var viewModel: RecipeListViewModel

    init() {
        self.viewModel = RecipeListViewModel()
        // dismiss keyboard when drag starts
        UIScrollView.appearance().keyboardDismissMode = .onDrag
    }

    var body: some View {
        
        NavigationView{
         
            ZStack{
       
                VStack{

                 
                    /*NavigationLink(destination: NewRecipeScreen(recipeId: 0)) {
                        Button("Neues Rezept"){
                            
                            //saveIngredient.execute(ingredient: ingredient)
                        
                            //for ingredient in getAllIngredients.execute() {
                              //  print(ingredient)
                            //}
                        }
                        .padding()
                        .foregroundColor(.white)
                        .background(Color.green)
                        .cornerRadius(8)
                        Spacer()
                    }*/
                   
                    
                    RecipeSearchAppBar(
                        query: viewModel.state.query,
                        onTriggerEvent: { event in
                            viewModel.onTriggerEvent(stateEvent: event)
                        }
                    )
                    List{
                        ForEach(viewModel.state.recipes, id: \.self.databaseId){ recipe in
                            ZStack{
                                VStack{
                                    RecipeCard(recipe: recipe)
                                        .onAppear(perform: {
                                            if viewModel.shouldQueryNextPage(recipe: recipe){
                                                viewModel.onTriggerEvent(stateEvent: RecipeListEvents.NextPage())
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
                    ProgressView("Searching recipes...")
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
