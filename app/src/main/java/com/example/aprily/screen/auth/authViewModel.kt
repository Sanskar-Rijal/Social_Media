package com.example.aprily.screen.auth

import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.aprily.data.UserData
import com.example.aprily.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val USERS ="users"

@HiltViewModel
class authViewModel @Inject constructor(private val auth: FirebaseAuth,
                                        private val db: FirebaseFirestore,
                                        private val storage: FirebaseStorage
):ViewModel() {

    val SignedIn= mutableStateOf(false)
    val inProgress = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)


    fun onSignup(username:String,email:String,password:String){
        //weneed to check whether useris unique or not
        inProgress.value=true
        db.collection(USERS).whereEqualTo("username",username).get().addOnSuccessListener {doc->
            //checking whether doc is empty or not
            //if it's empty we don't have user
            if(doc.size()>0){
                handleException(CustomMsg = "Username already Exists")
                inProgress.value=false
            }
            else{
                //username is not in the database
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {task->
                    /**
                     * The task object is an instance of Task<T>, provided by Firebase. It represents the result of an asynchronous operation.
                     *     Key properties of task:
                     *         isSuccessful: Returns true if the operation was completed successfully.
                     *         exception: Provides the exception that caused the failure if the task was not successful.
                     *         result: Retrieves the result of the task if it was successful (e.g., user details for an authentication task).
                     */
                    if(task.isSuccessful)
                        SignedIn.value=true//since it's success we will create an account
                    else{
                        handleException(task.exception, "SignUp failed")
                        inProgress.value=false
                    }

                    inProgress.value=false
                }
            }

        }
            .addOnFailureListener{

            }
    }

    fun handleException(exception: Exception?=null,CustomMsg:String?=""){

    }


}