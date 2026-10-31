package com.vp.week6.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vp.week6.model.Community


@Composable
fun CommunityCard(
    community: Community,
    onJoinClick: () -> Unit,
    onCardClick: () -> Unit
) {
    val (icon, iconTint, iconBg) = when {
        community.name.contains("CyberSecurity") -> Triple(Icons.Default.Security, Color(0xFFD32F2F), Color(0xFFFFEBEE))
        community.name.contains("AI") -> Triple(Icons.Default.AutoAwesome, Color(0xFF7B1FA2), Color(0xFFF3E5F5))
        community.name.contains("Mobile") -> Triple(Icons.Default.Smartphone, Color(0xFF03A9F4), Color(0xFFE1F5FE))
        else -> Triple(Icons.Filled.Code, Color(0xFF1976D2), Color(0xFFE3F2FD))
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
            Row(verticalAlignment = Alignment.CenterVertically) {
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
                Column {
                    Text(text = community.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                    Text(text = "${community.description.take(25)}... • ${community.memberCount} Mahasiswa", fontSize = 11.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = community.description, fontSize = 13.sp, color = Color.DarkGray)

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp)
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (community.isJoined) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LockOpen, contentDescription = "Unlocked", modifier = Modifier.size(14.dp), tint = Color(0xFF1E50FF))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Unlocked - Buka Proyek ->", fontSize = 12.sp, color = Color(0xFF1E50FF), fontWeight = FontWeight.SemiBold)
                    }
                    OutlinedButton(
                        onClick = { },
                        enabled = false,
                        colors = ButtonDefaults.outlinedButtonColors(disabledContentColor = Color(0xFFD32F2F)),
                        border = BorderStroke(1.dp, Color(0xFFD32F2F).copy(alpha = 0.5f))
                    ) {
                        Text("✓ Joined", fontWeight = FontWeight.Bold)
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Lock, contentDescription = "Locked", modifier = Modifier.size(14.dp), tint = Color.Gray)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Terkunci (Gate Akses)", fontSize = 12.sp, color = Color.Gray)
                    }
                    Button(
                        onClick = onJoinClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E50FF))
                    ) {
                        Text("+ Join")
                    }
                }
            }
        }
    }
}