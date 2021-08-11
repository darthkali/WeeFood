//
//  RecipeCardView.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 10.08.21.
//
import SwiftUI
import shared
import SDWebImageSwiftUI

struct RecipeCard: View {

    //private let saveRecipe = SaveRecipe()
    
    let recipe: Recipe

    
    init(
        recipe: Recipe
    ) {
        self.recipe = recipe
    
    }

    var body: some View {
        VStack(){
            
            HStack(){
                
                // Image
                // Text: RecipeName
                
                //let recipeImage = recipe.image ?? "no.jpg"

                WebImage(url: URL(string: "https://spoonacular.com/cdn/ingredients_500x500/" +  "" ))
                    //TODO replace with correct image implementation
                            .resizable()
                            .placeholder(Image(systemName: "photo")) // Placeholder Image
                            .placeholder {
                                Rectangle().foregroundColor(.white)
                            }
                            .indicator(.activity)
                            .transition(.fade(duration: 0.5))
                            .scaledToFit() // 1
                            .frame(height: 64, alignment: .center) // 2
                            .clipped() // 3

              
                DefaultText(recipe.name , size: 19)
                        .font(.body)
                        .frame(alignment: .center)
                    
                Spacer()
                
            }
            
            HStack(){
                NavigationLink(destination: NewRecipeScreen(recipeId: recipe.databaseId as! Int)) {
                      Text("Öffnen")
                   }
                /*
                Button("Öffnen"){
                    viewModel.onTriggerEvent(event: RecipeListEvents.OnLoadRecipe(recipe: viewModel.state.recipe))
                }
                .padding()
                .foregroundColor(.white)
                .background(Color.green)
                .cornerRadius(8)
                Spacer()
                */
                Button("Hinzufügen"){
                    
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
            }
            //HStack
            // Button Öffnen
            // Button Hinzufügen


        }
        .background(Color.white)
        .cornerRadius(8)
        .shadow(radius: 5)

    }
}

/*struct IngredientCard_Previews: PreviewProvider {
    static let ingredient = Ingredient(
        id: 1,
        name: "Apfel",
        image: "apple.png"
    )
    static var previews: some View {
        IngredientCard(ingredient: ingredient)
    }
}*/


