package com.vp.week6.ui.widgets



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vp.week6.model.Project
import java.util.UUID


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectCard(project: Project, onDeleteClick: () -> Unit, onCardClick: () -> Unit) {
    val (icon, iconTint, iconBg) = when {
        project.title.contains("Scanner") -> Triple(Icons.Default.Code, Color(0xFF4CAF50), Color(0xFFE8F5E9))
        project.title.contains("Portal") -> Triple(Icons.Default.Cloud, Color(0xFF9C27B0), Color(0xFFF3E5F5))
        else -> Triple(Icons.Default.Android, Color(0xFF1976D2), Color(0xFFE3F2FD))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onCardClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(iconBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = iconTint)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = project.title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.Black)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = project.description,
                        fontSize = 11.sp,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 16.sp
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFFFEBEE),
                    modifier = Modifier.size(32.dp)
                ) {
                    IconButton(onClick = onDeleteClick, modifier = Modifier.size(32.dp)) {
                        Icon(Icons.Default.Remove, contentDescription = "Delete", tint = Color(0xFFD32F2F), modifier = Modifier.size(16.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                project.techStacks.forEach { tech ->
                    Surface(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = tech,
                            fontSize = 10.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color(0xFFF5F5F5), thickness = 1.dp)
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "ID: proj-${project.id.take(7)}", fontSize = 11.sp, color = Color.Gray)

                val isCompleted = project.isCompleted
                val statusColor = if (isCompleted) Color(0xFF388E3C) else Color(0xFFF57C00)
                val statusBg = if (isCompleted) Color(0xFFE8F5E9) else Color(0xFFFFF3E0)

                Surface(
                    color = statusBg,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "●", color = statusColor, fontSize = 10.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isCompleted) "Completed" else "In Progress",
                            color = statusColor,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddProjectDialog(
    communityName: String,
    communityId: String,
    onDismiss: () -> Unit,
    onSave: (Project) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedTechStacks by remember { mutableStateOf(setOf<String>()) }

    val availableTechStacks = listOf("Kotlin", "Jetpack Compose", "Room", "Firebase", "Ktor", "Python", "Google Maps SDK")
    val isSaveEnabled = title.isNotBlank() && description.isNotBlank() && selectedTechStacks.isNotEmpty()

    AlertDialog(
        onDismissRequest = {  },
        containerColor = Color.White,
        title = {
            Column {
                Text("Add Project", fontWeight = FontWeight.Bold, color = Color.Black)
                Text("Komunitas: $communityName", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Normal)
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = title, onValueChange = { title = it },
                    label = { Text("Judul Proyek *") },
                    singleLine = true, modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF1976D2),
                        focusedLabelColor = Color(0xFF1976D2)
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = description, onValueChange = { description = it },
                    label = { Text("Deskripsi Proyek (Minimal 3 Baris) *") },
                    minLines = 3, modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF1976D2),
                        focusedLabelColor = Color(0xFF1976D2)
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Tech Stack (Pilihan Multi-Selection) *", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    if (selectedTechStacks.isNotEmpty()) {
                        Text("${selectedTechStacks.size} Dipilih", fontSize = 12.sp, color = Color(0xFF1976D2), fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    availableTechStacks.forEach { tech ->
                        val isSelected = selectedTechStacks.contains(tech)
                        FilterChip(
                            selected = isSelected,
                            onClick = {
                                selectedTechStacks = if (isSelected) selectedTechStacks - tech else selectedTechStacks + tech
                            },
                            label = { Text(tech, color = if (isSelected) Color(0xFF1976D2) else Color.DarkGray) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color(0xFFE3F2FD),
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = isSelected,
                                borderColor = if (isSelected) Color(0xFF1976D2) else Color.LightGray,
                            ),
                            leadingIcon = if (isSelected) {
                                { Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF1976D2), modifier = Modifier.size(16.dp)) }
                            } else null
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val newProject = Project(
                        id = UUID.randomUUID().toString(),
                        communityId = communityId,
                        title = title,
                        description = description,
                        techStacks = selectedTechStacks.toList(),
                        isCompleted = false
                    )
                    onSave(newProject)
                },
                enabled = isSaveEnabled,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E50FF))
            ) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel", color = Color(0xFF1976D2), fontWeight = FontWeight.Bold) }
        }
    )
}