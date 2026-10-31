package com.example.studyplanner.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class PlannerUiState(
    val studentName: String = "Student",
    val tasks: List<String> = emptyList()
)

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onTaskClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Welcome, ${uiState.studentName}!",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Study Tasks:",
            style = MaterialTheme.typography.titleMedium
        )

        uiState.tasks.forEach { taskTitle ->
            Button(
                onClick = { onTaskClick(taskTitle) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = taskTitle)
            }
        }
    }
}

@Composable
fun TaskDetailScreen(
    taskTitle: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Task Details",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Selected Task: $taskTitle",
            style = MaterialTheme.typography.bodyLarge
        )
        Button(onClick = onBackClick) {
            Text(text = "Back to Home")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlannerHomePreview() {
    MaterialTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                studentName = "Sadeem",
                tasks = listOf("Lab 5 report", "CSC 402 Final Exam Prep", "Android Architecture Reading")
            ),
            onTaskClick = {}
        )
    }
}