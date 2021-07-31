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
    private let cacheModule = DatabaseModule()
    
    
    var body: some View {
        TabView{
            
            // Meine Rezepte
            NavigationView{
                RecipeListScreen().navigationBarTitle("Meine Rezepte")
            }
            .tabItem{
                Image(systemName: "book.fill")
                Text("Rezepte")
            }
            
            // Wochenplan / Home
            NavigationView{
                WeekListScreen().navigationBarTitle("WeeFood")
            }
            .tabItem{
                Image(systemName: "house.fill")
                Text("Wochenplan")
            }
            
            // Einkaufsliste
            NavigationView{
                IngredientListScreen(
                    networkModule: networkModule,
                    cacheModule: cacheModule
                ).navigationBarTitle("Zutatensuche")
            }
            .tabItem{
                Image(systemName: "cart.fill")
                Text("Einkaufsliste")
            }
        }.edgesIgnoringSafeArea(.top)
    }
}

struct BottomBar_Previews: PreviewProvider {
    static var previews: some View {
        BottomBar()
    }
}
