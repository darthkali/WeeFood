//
//  RecipeCardView.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 10.08.21.
//
import SwiftUI
import shared
import SDWebImageSwiftUI

struct RecipeCardView: View {

    //private let saveRecipe = SaveRecipe()
    
    let ingredient: Ingredient

    init(
        ingredient: Ingredient
    ) {
        self.ingredient = ingredient
    }

    var body: some View {
        VStack(){
            
            HStack(){
                
                let ingredientImage = ingredient.image ?? "no.jpg"

                WebImage(url: URL(string: "https://spoonacular.com/cdn/ingredients_500x500/" +  ingredientImage ))
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

              
                    DefaultText(ingredient.name ?? "", size: 19)
                        .font(.body)
                        .frame(alignment: .center)
                    
                Spacer()
                
            }
    
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


