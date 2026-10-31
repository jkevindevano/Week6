package com.vp.week6.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vp.week6.ui.widgets.AddProjectDialog
import com.vp.week6.ui.widgets.ProjectCard
import com.vp.week6.viewmodel.CampusViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubProjectsScreen(
    communityId: String,
    viewModel: CampusViewModel,
    onBack: () -> Unit,
    onNavigateToProject: (String) -> Unit
) {
    val communities by viewModel.communities.collectAsState()
    val allProjects by viewModel.projects.collectAsState()

    val community = communities.find { it.id == communityId }
    val clubProjects = allProjects.filter { it.communityId == communityId }

    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                title = { Text("${community?.name ?: "Club"} Projects", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = Color(0xFFE3F2FD),
                contentColor = Color(0xFF1976D2),
                icon = { Icon(Icons.Default.Add, contentDescription = "Add Project") },
                text = { Text("Add Project", fontWeight = FontWeight.Bold) }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            item {
                Text(
                    text = "Daftar Proyek Mahasiswa (${clubProjects.size})",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            items(clubProjects) { project ->
                ProjectCard(
                    project = project,
                    onDeleteClick = {
                        viewModel.deleteProject(project.id)
                    },
                    onCardClick = {
                        onNavigateToProject(project.id)
                    }
                )
            }
        }

        if (showAddDialog) {
            AddProjectDialog(
                communityName = community?.name ?: "Community",
                communityId = communityId,
                onDismiss = { showAddDialog = false },
                onSave = { newProject ->
                    viewModel.addProject(newProject)
                    showAddDialog = false
                }
            )
        }
    }
}