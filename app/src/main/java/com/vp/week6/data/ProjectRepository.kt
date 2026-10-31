package com.vp.week6.data

import com.vp.week6.model.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object ProjectRepository {
    private val _projects = MutableStateFlow<List<Project>>(getDummyProjects())
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()
    private fun getDummyProjects(): List<Project> {
        return listOf(
            Project("pro1", "com1", "Smart Campus Navigation", "Sistem navigasi peta gedung kampus interaktif berbasis Jetpack Compose dan Google Maps SDK.", listOf("Kotlin", "Jetpack Compose", "Room", "Maps SDK"), isCompleted = false),
            Project("pro2", "com1", "Student Attendance QR Scanner", "Pemindai barcode & QR presensi kehadiran workshop berbasis CameraX & Firebase.", listOf("Jetpack Compose", "Firebase", "CameraX"), isCompleted = true),
            Project("pro3", "com1", "Developer RSVP Portal", "Portal pendaftaran webinar dan bootcamp teknologi sinkronisasi Ktor API server.", listOf("Kotlin", "Ktor", "Compose"), isCompleted = false)
        )
    }

    fun deleteProject(projectId: String) {
        _projects.update { list ->
            list.filter { it.id != projectId }
        }
    }

    fun addProject(project: Project) {
        _projects.update { list -> list + project }
    }

    fun toggleProjectStatus(projectId: String) {
        _projects.update { list ->
            list.map { if (it.id == projectId) it.copy(isCompleted = !it.isCompleted) else it }
        }
    }
}