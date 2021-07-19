//
//  FoodCategorieChip.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 19.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct FoodCategorieChip: View {
    
    private let category: String
    private let isSelected: Bool
    
    init(category: String, isSelected:Bool = false){
        self.category = category
        self.isSelected = isSelected
    }
    var body: some View {
        HStack{
            Text(category)
                .padding(8)
                .background(isSelected ? Color.gray : Color.blue)
                .foregroundColor(Color.white)
            
        }
        .cornerRadius(10)
    }
}


