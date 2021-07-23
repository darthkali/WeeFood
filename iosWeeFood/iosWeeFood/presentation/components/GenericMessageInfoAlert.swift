//
//  GenericMessageInfoAlert.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 20.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GenericMessageInfoAlert  {
    
    func build(
        message: GenericMessageInfo,
        onRemoveHeadMessage: @escaping () -> Void
    ) -> Alert {
       return Alert(
            title: Text(message.name),
            message: Text(message.description_ ?? "Something went wrong"),
            primaryButton: .default(
                Text(message.positiveAction?.positiveBtnTxt ?? "Ok"),
                action: {
                    if(message.positiveAction != nil){
                        message.positiveAction!.onPositiveAction()
                    }
                    onRemoveHeadMessage()
                }
            ),
            secondaryButton: .default(
                Text(message.negativeAction?.negativeBtnTxt ?? "Cancel"),
                action: {
                    if(message.negativeAction != nil){
                        message.negativeAction!.onNegativeAction()
                    }
                    onRemoveHeadMessage()
                }
            )
        )
    }
}
