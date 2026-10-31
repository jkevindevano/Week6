package com.vp.week6.model

data class Project(
    val id: String,
    val communityId: String,
    val title: String,
    val description: String,
    val techStacks: List<String>,
    val isCompleted: Boolean = false
)