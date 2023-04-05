package com.example.e_invoicesystem.appRepository

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_invoicesystem.activity.MainActivityViewModel
import com.example.firebaseproject.appRepository.AppRepository

class MainActivityViewModelFactory(val app: Application, private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(app,repository) as T
    }
}