package com.vp.week6.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vp.week6.model.Community
import com.vp.week6.model.Profile
import com.vp.week6.model.Project


@Composable
fun ProfileCard(user: Profile) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF1E3A8A), Color(0xFF3B82F6))
                    )
                )
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = user.avatarUrl, fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A), fontSize = 24.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Surface(
                        color = Color.White.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "MAHASISWA AKTIF",
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = user.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "${user.major} • ${user.batch}", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
                    Text(text = "NIM: ${user.nim}", color = Color.White.copy(alpha = 0.9f), fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun StatCard(title: String, count: Int, subtitle: String, icon: ImageVector, iconBgColor: Color, iconTintColor: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(iconBgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconTintColor, modifier = Modifier.size(24.dp))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = count.toString(), fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = subtitle, fontSize = 11.sp, color = Color.Gray)
        }
    }
}


@Composable
fun DashboardCommunityItem(community: Community, projectCount: Int) {
    val (icon, iconTint) = when {
        community.name.contains("CyberSecurity") -> Icons.Default.Security to Color(0xFFD32F2F)
        community.name.contains("Mobile") -> Icons.Default.Smartphone to Color(0xFF03A9F4)
        else -> Icons.Default.Code to Color(0xFF3B82F6)
    }

    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF0F4FF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconTint)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = community.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
                Text(
                    text = "${community.description.take(30)}... • $projectCount Proyek",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Surface(
                border = BorderStroke(1.dp, Color(0xFFEF5350)),
                color = Color(0xFFFFEBEE),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Joined ✓",
                    color = Color(0xFFD32F2F),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun DashboardProjectItem(project: Project, communityName: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF0F4FF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Android, contentDescription = "Android", tint = Color(0xFF3B82F6), modifier = Modifier.size(28.dp))
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(text = communityName, color = Color(0xFF3B82F6), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Text(text = project.title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                Text(
                    text = project.techStacks.take(3).joinToString(" • "),
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
}