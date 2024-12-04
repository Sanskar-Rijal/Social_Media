package com.example.aprily.screen.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.aprily.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class authViewModel @Inject constructor(repository: AuthRepository):ViewModel() {

}