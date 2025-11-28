package com.konnettoco.konnetto.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.konnettoco.konnetto.data.repository.PostRepository
import com.konnettoco.konnetto.data.repository.UserDummyRepository
import com.konnettoco.konnetto.ui.screen.profile.otheruserprofile.OtherUserProfileViewModel

class OtherUserProfileViewModelFactory(
    private val userRepository: UserDummyRepository,
    private val postRepository: PostRepository,
    private val userId: Long,
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OtherUserProfileViewModel::class.java)) {
            return OtherUserProfileViewModel(
                userRepository = userRepository,
                postRepository = postRepository,
                userId = userId,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}