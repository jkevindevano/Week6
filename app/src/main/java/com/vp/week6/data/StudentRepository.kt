package com.vp.week6.data

import com.vp.week6.model.Profile

object StudentRepository {
    fun getDummyProfile(): Profile {
        return Profile(
            name = "Jonathan Kevin Devano",
            nim = "1076012510038",
            major = "Teknik Informatika",
            batch = "Angkatan 2025",
            avatarUrl = "JKD"
        )
    }
}