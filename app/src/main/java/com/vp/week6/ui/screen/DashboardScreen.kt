package com.vp.week6.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vp.week6.ui.widgets.DashboardCommunityItem
import com.vp.week6.ui.widgets.DashboardProjectItem
import com.vp.week6.ui.widgets.ProfileCard
import com.vp.week6.ui.widgets.StatCard
import com.vp.week6.viewmodel.CampusViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: CampusViewModel) {
    val user by viewModel.userProfile.collectAsState()
    val allCommunities by viewModel.communities.collectAsState()
    val allProjects by viewModel.projects.collectAsState()

    val joinedCommunities = allCommunities.filter { it.isJoined }
    val joinedClubsCount = joinedCommunities.size
    val totalProjectsCount = allProjects.size

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                title = {
                    Column {
                        Text("CampusTech Hub", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text("Student Profile & Portfolio Dashboard", fontSize = 12.sp, color = Color.Gray)
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF3F51B5)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(user.avatarUrl, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
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
                ProfileCard(user = user)
                Spacer(modifier = Modifier.height(12.dp))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    StatCard(
                        title = "Joined Clubs",
                        count = joinedClubsCount,
                        subtitle = "Komunitas yang Diikuti",
                        icon = Icons.Default.Groups,
                        iconBgColor = Color(0xFFE3F2FD),
                        iconTintColor = Color(0xFF1976D2),
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Total Projects",
                        count = totalProjectsCount,
                        subtitle = "Seluruh Proyek Dibuat",
                        icon = Icons.Default.Layers,
                        iconBgColor = Color(0xFFF3E5F5),
                        iconTintColor = Color(0xFF7B1FA2),
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFF1976D2), modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Semua Komunitas yang Diikuti ($joinedClubsCount)", fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
                    }
                    Text("Status: Joined", color = Color(0xFF1976D2), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
            }

            items(joinedCommunities) { community ->
                val projectCount = allProjects.count { it.communityId == community.id }
                DashboardCommunityItem(community = community, projectCount = projectCount)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CropSquare, contentDescription = null, tint = Color(0xFF7B1FA2), modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Semua Proyek Mahasiswa ($totalProjectsCount)", fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
                    }
                    Text("Total: $totalProjectsCount Proyek", color = Color(0xFF1976D2), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
            }

            items(allProjects) { project ->
                val community = allCommunities.find { it.id == project.communityId }
                DashboardProjectItem(
                    project = project,
                    communityName = community?.name ?: "Unknown"
                )
            }

            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}