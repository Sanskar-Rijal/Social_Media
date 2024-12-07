package com.example.aprily.screen.auth

import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.aprily.data.Event
import com.example.aprily.data.UserData
//import com.example.aprily.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val USERS ="users"

@HiltViewModel
class authViewModel @Inject constructor(private val auth: FirebaseAuth,
                                        private val db: FirebaseFirestore,
                                        private val storage: FirebaseStorage):ViewModel()  {

    val SignedIn = mutableStateOf(false)
    val inProgress = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)

    val popupNotification = mutableStateOf<Event<String>?>(null)


    fun onSignup(username: String, email: String, password: String) {
        //weneed to check whether useris unique or not
        inProgress.value = true
        db.collection(USERS).whereEqualTo("username", username).get().addOnSuccessListener { doc ->
            //checking whether doc is empty or not
            //if it's empty we don't have user
            if (doc.size() > 0) {
                handleException(CustomMsg = "Username already Exists")
                inProgress.value = false
            } else {
                //username is not in the database
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    /**
                     * The task object is an instance of Task<T>, provided by Firebase. It represents the result of an asynchronous operation.
                     *     Key properties of task:
                     *         isSuccessful: Returns true if the operation was completed successfully.
                     *         exception: Provides the exception that caused the failure if the task was not successful.
                     *         result: Retrieves the result of the task if it was successful (e.g., user details for an authentication task).
                     */
                    if (task.isSuccessful) {
                        SignedIn.value = true//since it's success we will create an account
                        CreateorUpdateProfile(username = username)
                    }
                    else {
                        handleException(task.exception, "SignUp failed")
                        inProgress.value = false
                    }

                    inProgress.value = false
                }
            }

        }
            .addOnFailureListener {

            }
    }


    //mutable state displays the same message again and again so we will not be usgin mutablestate
    //so we will create a special data type that will only allow us to display msg once
    //we will make a class Event
    fun handleException(exception: Exception? = null, CustomMsg: String  = "") {

        exception?.printStackTrace()
        val errormessage =exception?.localizedMessage ?: " " //if thats a null then we will have a empty string
        val message = if (CustomMsg.isEmpty()) errormessage else "$CustomMsg : $errormessage"
        popupNotification.value = Event(message)
    }

    private fun CreateorUpdateProfile(
        name: String?=null ,
        username: String?=null,
        bio:String?=null,
        imageUrl:String?=null){

        val userid=auth.currentUser?.uid
        val userData = UserData(
            userId = userid,
            name= name?:userData.value?.name ,//if name is not null then it will assign the value name to name
        //if it's null then we will use UserData
            userName = username?:userData.value?.userName,
            bio = bio?: userData.value?.bio,
            imageUrl = imageUrl?:userData.value?.imageUrl,
            following = userData.value?.following
        )

        //checking whether we already have a user or not
        userid?.let { uid->
            inProgress.value=true
            db.collection(USERS).document(uid).get().addOnSuccessListener {
                if(it.exists()){
                    //we want to update with new data

                    //if we want to update we have to pass map  to this function
                    it.reference.update(userData.toMap()).addOnSuccessListener {
                        this.userData.value = userData
                            inProgress.value=false
                    }.addOnFailureListener{
                        handleException( it,"Can not Update User")
                        inProgress.value=false
                    }

                }
                //if it doesnt exists
                else{
                    db.collection(USERS).document(uid).set(userData)
                    getUserData(uid)
                    inProgress.value=false
                }
            }.addOnFailureListener{
                handleException(it,"Can't Create User")
                inProgress.value=false
            }
        }
    }
private fun getUserData(uid:String){

}

}

