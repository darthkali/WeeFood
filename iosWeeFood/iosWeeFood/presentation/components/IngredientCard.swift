//
//  RecipeCard.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 19.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct IngredientCard: View {

    let ingredient: Ingredient

    init(ingredient: Ingredient) {
        self.ingredient = ingredient
    }

    var body: some View {
        VStack(alignment: .leading){
            WebImage(url: URL(string: "https://spoonacular.com/cdn/ingredients_500x500/" +  ingredient.image))
                        .resizable()
                        .placeholder(Image(systemName: "photo")) // Placeholder Image
                        .placeholder {
                            Rectangle().foregroundColor(.white)
                        }
                        .indicator(.activity)
                        .transition(.fade(duration: 0.5))
                        .scaledToFill() // 1
                        .frame(height: 250, alignment: .center) // 2
                        .clipped() // 3

            HStack(alignment: .lastTextBaseline){
                DefaultText(ingredient.name, size: 19)
                    .font(.body)
                    .frame(alignment: .center)

                Spacer()

                //DefaultText(String(recipe.rating))
                   // .frame(alignment: .trailing)
            }
            .padding(.top, 8)
            .padding(.leading, 8)
            .padding(.trailing, 8)
            .padding(.bottom, 12)
        }
        .background(Color.white)
        .cornerRadius(8)
        .shadow(radius: 5)

    }
}

struct IngredientCard_Previews: PreviewProvider {
    static let ingredient = Ingredient(
        id: 1,
        name: "Apfel",
        image: "apple.png",
        aisle: "meat",

        possibleUnits: [
            "ml",
            "l",
        ]
        //dateAdded: DatetimeUtil().now(),
        //dateUpdated: DatetimeUtil().now()
    )
    static var previews: some View {
        IngredientCard(ingredient: ingredient)
    }
}


