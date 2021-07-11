//
//  RecipeListScreen.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 11.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeListScreen: View {
    
    // depencies
    private let networkModule: NetworkModule
    private let cacheModule: CacheModule
    private let searchRecipesModule: SearchRecipesModule
    
    
    init(
        networkModule: NetworkModule,
        cacheModule: CacheModule
    ){
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipesModule = SearchRecipesModule(
            networkModule: self.networkModule,
            cacheModule: self.cacheModule
        )
    }
    
    
    var body: some View {
        Text("Recipe_List_Screen")
    }
}

/*struct RecipeListScreen_Previews: PreviewProvider {
    static var previews: some View {
        RecipeListScreen()
    }
}*/
