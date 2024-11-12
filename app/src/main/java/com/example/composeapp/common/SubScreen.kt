package com.example.composeapp.common

import com.example.composeapp.R

enum class SubScreen(val titleId: Int) {
//     App
    APP_MAIN(titleId = R.string.app_main_screen),
    APP_SSM(titleId = R.string.app_ssm_screen),
    APP_NAVIGATION(titleId = R.string.app_navigation_screen),
    APP_BASIC(titleId = R.string.app_basic_screen),
    APP_CONVERSATION(titleId = R.string.app_conversation_screen),

//    SSM
    SSM_MAIN(titleId = R.string.ssm_main_screen),
    SSM_CONNECTION(titleId = R.string.ssm_connection_screen),
    SSM_SELECT_OS(titleId = R.string.ssm_select_os_screen),
    SSM_SEARCHING(titleId = R.string.ssm_searching_screen),
    SSM_MENU(titleId = R.string.ssm_menu_screen),
    SSM_NEW(titleId = R.string.ssm_new_activity),

//    Navigation
    NAV_HOME(titleId = R.string.nav_home_screen),
    NAV_PROFILE(titleId = R.string.nav_profile_screen),
    NAV_LOGIN(titleId = R.string.nav_login_screen),

//    Basic
    BASIC_BASIC(titleId = R.string.basic_basic_screen),
    BASIC_LAZY_LIST(titleId = R.string.basic_lazy_list_screen),
}