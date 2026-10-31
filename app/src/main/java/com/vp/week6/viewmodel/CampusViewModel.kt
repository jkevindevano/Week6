package com.vp.week6.viewmodel

import androidx.lifecycle.ViewModel
import com.vp.week6.data.CommunitiesRepository
import com.vp.week6.data.ProjectRepository
import com.vp.week6.data.StudentRepository
import com.vp.week6.model.Community
import com.vp.week6.model.Profile
import com.vp.week6.model.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CampusViewModel : ViewModel() {

    private val _userProfile = MutableStateFlow(StudentRepository.getDummyProfile())
    val userProfile: StateFlow<Profile> = _userProfile.asStateFlow()

    val communities: StateFlow<List<Community>> = CommunitiesRepository.communities

    val projects: StateFlow<List<Project>> = ProjectRepository.projects

    fun joinCommunity(communityId: String) {
        CommunitiesRepository.joinCommunity(communityId)
    }

    fun deleteProject(projectId: String) {
        ProjectRepository.deleteProject(projectId)
    }

    fun addProject(project: Project) {
        ProjectRepository.addProject(project)
    }

    fun toggleProjectStatus(projectId: String) {
        ProjectRepository.toggleProjectStatus(projectId)
    }
}