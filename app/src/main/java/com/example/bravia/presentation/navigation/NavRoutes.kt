package com.example.bravia.presentation.navigation

/**
 * NavRoutes is a sealed class that defines the navigation routes for the application.
 * It contains various data objects representing different screens or destinations in the app.
 */
sealed class NavRoutes {
    //Business==================================
    data object BusinessHome : NavRoutes() {
        const val ROUTE = "businessHome"
    }

    data object BusinessInternshipDetail : NavRoutes() {
        const val ROUTE = "businessInternshipDetail/{internshipId}"
        const val ARG_INTERNSHIP_ID = "internshipId"
        fun createRoute(internshipId: Long) = "businessInternshipDetail/$internshipId"
    }

    data object BusinessStarred : NavRoutes() {
        const val ROUTE = "businessStarred"
    }

    data object BusinessProfile : NavRoutes() {
        const val ROUTE = "businessProfile"
    }

    data object BusinessNewInternship : NavRoutes() {
        const val ROUTE = "businessNewInternship"
    }

    data object BusinessEditProfile : NavRoutes() {
        const val ROUTE = "businessEditProfile"
    }

    //START=====================================
    data object Login : NavRoutes() {
        const val ROUTE = "login"
    }

    data object LoginSaved : NavRoutes() {
        const val ROUTE = "loginSaved"
    }

    data object Start : NavRoutes() {
        const val ROUTE = "start"
    }

    data object SignIn : NavRoutes() {
        const val ROUTE = "signIn"
    }
    //SING-UP====================================
    data object SignUp : NavRoutes() {
        const val ROUTE = "signup"
    }

    data object ProfileSignUp : NavRoutes() {
        const val ROUTE = "profileSignup"
    }

    data object InterestsSignUp : NavRoutes() {
        const val ROUTE = "interestsSignup"
    }


    data object InternshipDetail : NavRoutes() {
        const val ROUTE = "internshipDetail/{internshipId}"
        const val ARG_INTERNSHIP_ID = "internshipId"
        fun createRoute(internshipId: Long) = "internshipDetail/$internshipId"
    }

    //Shared
    //student settings
    data object Settings : NavRoutes(){
        const val ROUTE = "settings"

    }

    //admin
    data object CompanyList : NavRoutes() {
        const val ROUTE = "companyList"
    }

    data object StudentList : NavRoutes() {
        const val ROUTE = "studentList"
    }

    data object ReportList : NavRoutes() {
        const val ROUTE = "reportList"
    }

    data object ReportProfile : NavRoutes() {
        const val ARG_REPORT_ID = "reportId"
        const val ROUTE = "reportProfile/{$ARG_REPORT_ID}"
        fun createRoute(reportId: Long) = "reportProfile/$reportId"
    }

    data object StudentProfile : NavRoutes() {
        const val ARG_STUDENT_ID = "studentId"
        const val ROUTE = "studentProfile/{$ARG_STUDENT_ID}"
        fun createRoute(studentId: Long) = "studentProfile/$studentId"
    }

    data object CompanyProfile : NavRoutes() {
        const val ARG_COMPANY_ID = "companyId"
        const val ROUTE = "companyProfile/{$ARG_COMPANY_ID}"
        fun createRoute(companyId: Long) = "companyProfile/$companyId"
    }
}