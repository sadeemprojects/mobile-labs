package com.example.studyplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.studyplanner.navigation.PlannerHome
import com.example.studyplanner.navigation.TaskDetail
import com.example.studyplanner.ui.PlannerHomeScreen
import com.example.studyplanner.ui.PlannerUiState
import com.example.studyplanner.ui.TaskDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = PlannerHome,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<PlannerHome> {
                            val sampleTasks = listOf(
                                "Lab 5 report",
                                "CSC 402 Final Exam Prep",
                                "Android Architecture Reading"
                            )
                            PlannerHomeScreen(
                                uiState = PlannerUiState(
                                    studentName = "Sadeem",
                                    tasks = sampleTasks
                                ),
                                onTaskClick = { clickedTaskTitle ->
                                    navController.navigate(TaskDetail(title = clickedTaskTitle))
                                }
                            )
                        }

                        composable<TaskDetail> { backStackEntry ->
                            val detailRoute: TaskDetail = backStackEntry.toRoute()
                            TaskDetailScreen(
                                taskTitle = detailRoute.title,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}