//
//  RecipeDetailScreen.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 20.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeDetailScreen: View {

    private let cacheModule: CacheModule
    private let getRecipeModule: GetRecipeModule
    private let recipeId: Int
    private let datetimeUtil = DatetimeUtil()

    @ObservedObject var viewModel: RecipeDetailViewModel

    init(
        recipeId: Int,
        cacheModule: CacheModule
         ) {
        self.recipeId = recipeId
        self.cacheModule = cacheModule
        self.getRecipeModule = GetRecipeModule(
            cacheModule: self.cacheModule
        )
        viewModel = RecipeDetailViewModel(
            recipeId: self.recipeId,
            getRecipe: self.getRecipeModule.getRecipe
        )
    }

    var body: some View {
            if viewModel.state.recipe != nil {
                RecipeView(
                    recipe: viewModel.state.recipe!,
                    dateUtil: datetimeUtil
                )
            }
            else{
                NavigationView { // NavigationView is needed for alert to work?
                    Text("Error")
                        .alert(isPresented: $viewModel.showDialog, content: {
                            let first = viewModel.state.queue.peek()!
                            return GenericMessageInfoAlert().build(
                                message: first,
                                onRemoveHeadMessage: viewModel.removeHeadFromQueue
                            )
                        })
                }
            }
            if viewModel.state.isLoading { // this is actually pointless b/c SwiftUI preloads this view
                VStack{
                    Spacer()
                    ProgressView("Loading Recipe Details...")
                    Spacer()
                }
            }
    }
}

//struct RecipeDetailScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeDetailScreen()
//    }
//}
