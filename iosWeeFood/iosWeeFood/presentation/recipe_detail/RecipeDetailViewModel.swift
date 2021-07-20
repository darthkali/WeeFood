//
//  RecipeDetailViewModel.swift
//  iosWeeFood
//
//  Created by Danny Steinbrecher on 20.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeDetailViewModel: ObservableObject {
    
    private let logger = Logger(className: "RecipeDetailViewModel")

    // Dependencies
    let getRecipe: GetRecipe

    // State
    @Published var state: RecipeDetailState =  RecipeDetailState()

    @Published var showDialog: Bool = false

    init(
        recipeId: Int,
        getRecipe: GetRecipe
    ) {
        self.getRecipe = getRecipe
        executeGetRecipe(recipeId: recipeId)
    }

    private func executeGetRecipe(recipeId: Int){
        do{
            try self.getRecipe.execute(
                recipeId: Int32(recipeId)
            ).collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                if dataState != nil {
                    let data = dataState?.data
                    let message = dataState?.message
                    let loading = dataState?.isLoading ?? false
                    self.updateState(isLoading: loading)

                    if(data != nil){
                        self.updateState(recipe: data! as Recipe)
                    }
                    if(message != nil){
                        self.handleMessageByUIComponentType(message!.build())
                    }
                }else{
                    self.logger.log(msg: "GetRecipe: DataState is nil")
                }
            })
        }catch{
            self.logger.log(msg: "\(error)")
        }
    }

    private func handleMessageByUIComponentType(_ message: GenericMessageInfo){
        switch message.uiComponentType{
        case UIComponentType.Dialog():
            appendToQueue(message: message)
        case UIComponentType.None():
            logger.log(msg: "\(message.description)")
        default:
            doNothing()
        }
    }

    private func doNothing(){}

    private func appendToQueue(message: GenericMessageInfo){
        let currentState = (self.state.copy() as! RecipeDetailState)
        let queue = currentState.queue
        let queueUtil = GenericMessageInfoQueueUtil() // prevent duplicates
        if !queueUtil.doesMessageAlreadyExistInQueue(queue: queue, messageInfo: message) {
            queue.add(element: message)
            updateState(queue: queue)
        }
    }

    private func updateState(
        isLoading: Bool? = nil,
        recipe: Recipe? = nil,
        queue: Queue<GenericMessageInfo>? = nil
    ){
        let currentState = (self.state.copy() as! RecipeDetailState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            recipe: recipe ?? currentState.recipe,
            queue: queue ?? currentState.queue
        )
        shouldShowDialog()
    }

    func shouldShowDialog(){
        let currentState = (self.state.copy() as! RecipeDetailState)
        showDialog = currentState.queue.items.count > 0
    }

    /**
     *  Remove the head message from queue
     */
    func removeHeadFromQueue(){
        let currentState = (self.state.copy() as! RecipeDetailState)
        let queue = currentState.queue
        do {
            try queue.remove()
            updateState(queue: queue)
        }catch{
            self.logger.log(msg: "\(error)")
        }
    }
}
