//
//  NewRecipeScreen.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 26.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//
import SwiftUI
import shared

struct NewRecipeScreen: View {

    private let recipeId: Int
    
    @ObservedObject var viewModel: NewRecipeViewModel
    
    init(
        recipeId: Int
    ) {
        self.recipeId = recipeId
        self.viewModel = NewRecipeViewModel(recipeId: recipeId)
    }

    var body: some View {
        
        //Button (Speichern)
        Button("Speichern"){
            viewModel.onTriggerEvent(event: NewRecipeEvents.OnSaveRecipe(recipe: viewModel.state.recipe))
        }
        .padding()
        .foregroundColor(.white)
        .background(Color.green)
        .cornerRadius(8)
        Spacer()
        
     
        //ScrollView
        ScrollView {
                VStack(alignment: .leading) {
                    //TextField -> Name
                    TextField(
                        "Name...",
                        text: $viewModel.state.recipe.name
                    )
                    .onChange(of: viewModel.state.recipe.name, perform: { value in
                        viewModel.onTriggerEvent(event: NewRecipeEvents.OnUpdateName(name: value))
                    })

                    // Text -> Zutaten pro Portion
                    Text("Zutaten pro Portion")
                    
                    // Button -> AddIngredient
                    Button("Zutat hinzufügen"){
          
                      
                    }
                    .padding()
                    .foregroundColor(.white)
                    .background(Color.green)
                    .cornerRadius(8)
                    Spacer()
                    
                    // Ingredient list
                    
                    List{
                        ForEach(viewModel.state.recipe.ingredients, id: \.self.internalId){ ingredient in
                            ZStack{
                                VStack{
                                    IngredientCard(ingredient: ingredient)
                                }
                            }
                            .listRowInsets(EdgeInsets())
                            .padding(.top, 10)
                        }
                    }
                    .listStyle(PlainListStyle())
                    
                    // Text -> Kochzeit
                    Text("Kochzeit")
                    
                    
                    HStack{
                        // TextFeld Kochzeit
                        TextField(
                            "Kochzeit...",
                            value: $viewModel.state.recipe.cooking_time,
                            formatter: NumberFormatter()
                        )
                        .onChange(of: viewModel.state.recipe.cooking_time, perform: { value in
                            viewModel.onTriggerEvent(event: NewRecipeEvents.OnUpdateCookingTime(cooking_time: value as! Int32))
                        })
                        
                        
                        
                        // TextFeld Unit
                        TextField(
                            "Unit...",
                            text: $viewModel.state.recipe.cooking_time_unit
                        )
                        .onChange(of: viewModel.state.recipe.cooking_time_unit, perform: { value in
                            viewModel.onTriggerEvent(event: NewRecipeEvents.OnUpdateCookingTimeUnit(cooking_time_unit: value))
                        })
                    }
            
                    
                    
                    // Text -> Rezept
                    Text("Rezept")
                    
                    // TextFeld Rezept
                    TextField(
                        "Rezept...",
                        text: $viewModel.state.recipe.recipeDescription ?? "dedasfsdf"
                    )
                    .onChange(of: viewModel.state.recipe.recipeDescription! , perform: { value in
                        viewModel.onTriggerEvent(event: NewRecipeEvents.OnUpdateDescription(description: value))
                    })
                    
                    
                    
                    
                }
        }
        
        
        
 
        
        
        
        
        
           
            
            
           
        
        
    }
}


func ??<T>(lhs: Binding<Optional<T>>, rhs: T) -> Binding<T> {
    Binding(
        get: { lhs.wrappedValue ?? rhs },
        set: { lhs.wrappedValue = $0 }
    )
}
