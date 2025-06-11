package com.example.bravia.presentation.ui.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.domain.model.Interest
import com.example.bravia.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.ui.theme.Typography
import com.example.bravia.presentation.viewmodel.SignupViewModel

/**
 * InterestsScreen is a Composable function that displays a screen for selecting user interests.
 * It allows users to choose from a list of interests represented as chips.
 *
 * @param navController The NavController used for navigation.
 * @param paddingValues Padding values for the screen.
 * @param signupViewModel The ViewModel responsible for managing the signup process.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun InterestsScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    signupViewModel: SignupViewModel
) {

    var interests by remember { mutableStateOf<List<Interest>>(emptyList() ) }


    val listOfInterests = signupViewModel.listOfInterest.collectAsState()

    LaunchedEffect(listOfInterests.value) {
        signupViewModel.findAllInterests()
        interests = listOfInterests.value
    }


    /*
     * State to hold the selected interests.
     * It uses a mutable state to keep track of the selected interests.
     */
    val (selectedInterests, setSelectedInterests) = remember {
        mutableStateOf<Set<Interest>>(emptySet())
    }

    /*
     * Function to handle the click event on an interest chip.
     * It updates the selected interests based on whether the interest is already selected or not.
     */
    fun onInterestClick(interest: Interest) {
        setSelectedInterests(
            if (interest in selectedInterests)
                selectedInterests - interest
            else
                selectedInterests + interest
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(start = 0.dp, top = 0.dp, end = 0.dp)

    ) {
        Text(
            text = "Select your interest",
            style = Typography.displayMedium,
            modifier = Modifier
                .padding(vertical = 50.dp)
                .align(Alignment.Center)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 150.dp)
    ) {

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxLines = 9,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f) // allow FlowRow to expand and scroll if needed
                .weight(1f) // allow FlowRow to expand and scroll if needed
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 8.dp)
        ) {
            interests.forEach { interest ->
                FilterChip(
                    selected = interest in selectedInterests,
                    onClick = { onInterestClick(interest) },
                    label = {
                        Text(text = interest.name)
                    }
                )
            }
        }


        CreateAccountButton(navController = navController, signupViewModel = signupViewModel)

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))


        RedirectToLoginButton(navController = navController)

        Spacer(modifier = Modifier.height(60.dp))
    }


}

/*
 * Button to redirect to the login screen.
 * This button is used to cancel the signup process and go back to the login screen.
 */
@Composable
fun RedirectToLoginButton(
    navController: NavController
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        onClick = { /*TODO*/ },

    ) {
        Text(
            text = "Cancel",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

/*
 * Button to create an account.
 * This button is used to proceed with the signup process after selecting interests.
 */
@Composable
fun CreateAccountButton(
    navController: NavController,
    signupViewModel: SignupViewModel
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 70.dp),
        onClick = {
            signupViewModel.registerStudent()
            navController.navigate("login")
        },

    ) {
        Text(
            text = "Create account",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}