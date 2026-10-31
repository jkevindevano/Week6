package com.vp.week6.data

import com.vp.week6.model.Community
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object CommunitiesRepository {
    private val _communities = MutableStateFlow<List<Community>>(getDummyCommunities())
    val communities: StateFlow<List<Community>> = _communities.asStateFlow()
    private fun getDummyCommunities(): List<Community> {
        return listOf(
            Community("com1", "GDSC", "Fokus pada ekosistem Google: Android, Compose, Flutter, Firebase, dan Google Cloud Platform.", 142, isJoined = true),
            Community("com2", "CyberSecurity Guild", "Eksplorasi ethical hacking, CTF challenges, reverse engineering, dan pertahanan jaringan kampus.", 56, isJoined = true),
            Community("com3", "Mobile Dev Community", "Native Jetpack Compose & iOS.", 98, isJoined = true),
            Community("com4", "AI Society", "Riset terapan deep learning, computer vision, agen cerdas, dan fine-tuning large language models.", 115, isJoined = false),
            Community("com5","Web Development", "Membuat web menggunakan Laravel, dengan MVC", 100, isJoined = false),
            Community("com6", "Computer Network", "Mengenali tentang jaringan melalui aplikasi Cisco Packet Tracer,", 120, isJoined = true)
        )
    }
    fun joinCommunity(communityId: String) {
        _communities.update { list ->
            list.map { if (it.id == communityId) it.copy(isJoined = true) else it }
        }
    }
}