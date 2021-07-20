//
//  RecipeView.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 20.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct RecipeView: View {
    
    private let recipe: Recipe
    private let dateUtil: DatetimeUtil
    
    init(
        recipe: Recipe,
        dateUtil: DatetimeUtil
    ) {
        self.recipe = recipe
        self.dateUtil = dateUtil
    }
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading){
                WebImage(url: URL(string: recipe.featuredImage))
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
                
                VStack(alignment: .leading){
                    
                    HStack(alignment: .lastTextBaseline){
                        Text(
                            "Updated \(dateUtil.humanizeDatetime(date: recipe.dateUpdated)) by \(recipe.publisher)"
                        )
                        .foregroundColor(Color.gray)

                        Spacer()
                        
                        Text(String(recipe.rating))
                            .frame(alignment: .trailing)
                    }
                    
                    ForEach(recipe.ingredients as Array<String>, id: \.self){ ingredient in
                        Text(ingredient)
                            .padding(.top, 4)
                    }
                }
                .background(Color.white)
                .padding(12)
            }
        }
        .navigationBarTitle(Text(recipe.title), displayMode: .inline)
    }
}

//struct RecipeView_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeView()
//    }
//}
