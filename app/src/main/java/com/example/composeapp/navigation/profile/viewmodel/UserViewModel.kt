package com.example.composeapp.navigation.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composeapp.common.Logger
import com.example.composeapp.navigation.profile.repos.UserRepository
import com.example.composeapp.navigation.profile.ui.uistate.LoginState
import com.example.composeapp.navigation.profile.ui.uistate.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel() : ViewModel() {
//class UserViewModel(val userRepository: UserRepository) : ViewModel() {


    override fun onCleared() {
        super.onCleared()
        Logger.d(TAG,"onCleared ...")
    }

    // user info state flow
    private val _userInfo = MutableStateFlow(UserInfo())
    val userInfo: StateFlow<UserInfo> = _userInfo.asStateFlow()

    fun updateUser(name: String, age: Int) {
        _userInfo.value = UserInfo(name, age)
    }

    // login state
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun doLogin(userName: String, password: String) {
        Logger.d(TAG,"doLogin() called... $userName - $password")
        // repository login
        _loginState.value = LoginState(isLoading = true)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000) // simulate network delay

            // login success
            val isLoginSuccessful = true
            val reasonMsg = "Login Successful"

            // login failed
//            val isLoginSuccessful = false
//            val reasonMsg = "Login failed"

            Logger.d(TAG,"doLogin() done:  isLoginSuccessful:$isLoginSuccessful - reasonMsg:$reasonMsg")
            // login result
            _loginState.value = LoginState(isLoginSuccessful, false, reasonMsg)

            if (isLoginSuccessful) {
                updateUser("ngoc", 30)
            }
        }
    }

    companion object {
        private const val TAG = "UserViewModel"
    }
}