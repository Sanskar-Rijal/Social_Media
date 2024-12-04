package com.example.aprily.di

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //it means that we require this module to provide whatever we declare in the view model
class AppModule {

    @Provides
    @Singleton
    fun provideAuthentication():FirebaseAuth
    =FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFireStore(): FirebaseFirestore
    =FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideStorage():FirebaseStorage
    =FirebaseStorage.getInstance()

}