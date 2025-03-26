package com.example.bravia.presentation.ui.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bravia.presentation.viewmodel.SignupViewModel
import com.example.studentapp.presentation.ui.theme.ThemeDefaults
import com.example.bravia.presentation.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun InterestsScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    signupViewModel: SignupViewModel
) {
    // Example list of interests
    val interests = listOf(
        "Android",
        "iOS",
        "Web",
        "Machine Learning",
        "Data Science",
        "UI/UX",
        "Game Development",
        "Cybersecurity",
        "Cloud Computing",
        "Blockchain",
        "IoT",
        "AR/VR",
        "Robotics",
        "Quantum Computing",
        "5G",
        "Big Data",
        "Artificial Intelligence",
        "Digital Marketing",
        "Product Management",
        "Finance",
        "Human Resources",
        "Operations",
        "Sales",
        "Customer Service",
        "Legal",
        "Research",
        "Education",
        "Healthcare",
        "Social Work",
        "Nonprofit",
        "Other"
    )

    // Mutable state to hold the selected interests
    val (selectedInterests, setSelectedInterests) = remember {
        mutableStateOf<Set<String>>(emptySet())
    }

    // Function to handle interest clicks
    fun onInterestClick(interest: String) {
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



        // FlowRow to display chips in multiple lines
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxLines = 7,
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
                        Text(text = interest)
                    }
                )
            }
        }

        // "Create account" button
        Button(
            onClick = { /*TODO*/ navController.navigate("home") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 70.dp)
        ) {
            Text("Create account")
        }

        Spacer(modifier = Modifier.height(ThemeDefaults.spacerHeight))

        // "Cancel" button
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
        ) {
            Text("Cancel")
        }

        Spacer(modifier = Modifier.height(60.dp))
    }


}