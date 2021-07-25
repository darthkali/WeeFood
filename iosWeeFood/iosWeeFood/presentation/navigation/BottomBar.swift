//
//  BottomBar.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 25.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared


struct BottomBar: View {
    
    private let networkModule = NetworkModule()
    private let cacheModule = CacheModule()
    
    
    var body: some View {
        TabView{
            Text("RecipeScreen")
                .tabItem{
                    Image(systemName: "book.fill")
                    Text("Rezepte")
                }
            Text("WeekListScreen")
                .tabItem{
                    Image(systemName: "house.fill")
                    Text("Wochenplan")
                }
            IngredientListScreen(
                networkModule: networkModule,
                cacheModule: cacheModule
            )
                .tabItem{
                    Image(systemName: "cart.fill")
                    Text("Einkaufsliste")
                }
        }
    }
}

struct BottomBar_Previews: PreviewProvider {
    static var previews: some View {
        BottomBar()
    }
}
