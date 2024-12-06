//package com.example.aprily.repository
//
//import com.example.aprily.di.AppModule
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.storage.FirebaseStorage
//import javax.inject.Inject
//
//class AuthRepository @Inject constructor(
//     private val auth: FirebaseAuth,
//     private val db: FirebaseFirestore,
//     private val storage: FirebaseStorage
//) {
//    fun provideAuthentication()=auth
//     fun provideFireStore()=db
//     fun provideStorage()=storage
//}