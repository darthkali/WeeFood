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

struct RecipeCard: View {

    let recipe: Recipe

    init(recipe: Recipe) {
        self.recipe = recipe
    }

    var body: some View {
        VStack(alignment: .leading){
            WebImage(url: URL(string: "https://spoonacular.com/cdn/ingredients_500x500/" +  recipe.image))
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
                DefaultText(recipe.name, size: 19)
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

struct RecipeCard_Previews: PreviewProvider {
    static let recipe = Recipe(
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
        RecipeCard(recipe: recipe)
    }
}


