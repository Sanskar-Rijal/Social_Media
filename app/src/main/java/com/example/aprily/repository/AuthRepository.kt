package com.example.aprily.repository

import com.example.aprily.di.AppModule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class AuthRepository @Inject constructor(
     private val firebaseAuth: FirebaseAuth,
     private val fireStore: FirebaseFirestore,
     private val storage: FirebaseStorage
) {
    fun provideAuthentication()=firebaseAuth
     fun provideFireStore()=fireStore
     fun provideStorage()=storage
}