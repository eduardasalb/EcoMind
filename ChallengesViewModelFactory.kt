package com.example.ecomind.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecomind.repository.ChallengeRepository
import com.example.ecomind.usecase.CompleteChallengeUseCase
import kotlinx.coroutines.Dispatchers

class ChallengesViewModelFactory(
    private val repo: ChallengeRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChallengesViewModel::class.java)) {

            val useCase = CompleteChallengeUseCase(
                repo = repo,
                ioDispatcher = Dispatchers.IO
            )

            return ChallengesViewModel(
                repo = repo,
                completeUseCase = useCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
