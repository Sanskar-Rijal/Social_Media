package com.example.aprily.data

//we are adding null because firebase require us to give value before , we don't have any value so we are giving null
data class UserData(
    var userId:String?=null,
    var name:String?=null,
    var userName:String?=null, //username is unique
    var imageUrl:String?=null,
    var bio:String?=null,
    var following:List<String>?=null
){
    fun toMap() = mapOf(
        "userId" to userId,
        "name" to name,
        "userName" to userName,
        "imageUrl" to imageUrl,
        "bio" to bio,
        "following" to following
    )
}
