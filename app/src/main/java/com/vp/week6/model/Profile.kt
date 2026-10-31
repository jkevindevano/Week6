package com.vp.week6.model

data class Profile(
    val name: String,
    val nim: String,
    val major: String,
    val batch: String,
    val avatarUrl: String = ""
)