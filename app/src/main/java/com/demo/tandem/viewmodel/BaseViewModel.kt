package com.demo.tandem.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    val anyUseCaseInProgress = MutableLiveData<Boolean>()
    val anyUseCaseFailure = MutableLiveData<Throwable>()

    protected fun executeUseCase(
        inProgress: ((Boolean) -> Unit)? = null,
        onFailure: ((Throwable) -> Unit)? = null,
        useCase: suspend () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                inProgress?.let { it(true) } ?: run { anyUseCaseInProgress.postValue(true) }
                useCase()
            } catch (cause: Throwable) {
                onFailure?.let { it(cause) } ?: run {
                    Timber.e(cause.localizedMessage ?: cause.toString())
                    anyUseCaseFailure.postValue(cause)
                }
            } finally {
                inProgress?.let { it(false) } ?: run { anyUseCaseInProgress.postValue(false) }
            }
        }
    }
}