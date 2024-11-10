package com.example.composeapp.navigation.profile.ui.uistate

data class LoginState(
    val isLoginSuccessful: Boolean = false,
    val isLoading: Boolean = false, // true when login button is clicked and waiting for response from server
    val reasonMsg: String = "", // error message or success message
) {
    override fun toString(): String {
        return "LoginState(isLoginSuccessful=$isLoginSuccessful, isLoading=$isLoading, reasonMsg='$reasonMsg')@" + hashCode()
    }
}