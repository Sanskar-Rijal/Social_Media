package com.example.aprily.data

open class Event<out T>(private val content:T) {

    //it will check whethera
    // event has handled or not
    var hasBeenHandled = false
    private set //only the element inside this class can update the variable
    fun getContentorNull():T?{
        return if(hasBeenHandled){
            null
        }
        else{
            hasBeenHandled=true
            content
        }
    }

}