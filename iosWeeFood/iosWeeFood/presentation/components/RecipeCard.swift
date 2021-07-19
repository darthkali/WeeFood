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
    private let recipe: Recipe
    
    init(recipe: Recipe){
        self.recipe = recipe
    }
    var body: some View {
        WebImage(url: URL(string: recipe.featuredImage))
            .resizable()
            .placeholder(Image(systemName: "photo"))
            .placeholder{
                Rectangle().foregroundColor(.white)
            }
            .indicator(.activity)
            .transition(.fade(duration: 0.5))
            .scaledToFill()
            .frame(height: 250, alignment: .center)
            .clipped()
    }
}


