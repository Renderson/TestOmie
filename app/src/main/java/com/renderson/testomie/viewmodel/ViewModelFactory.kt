package com.renderson.testomie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renderson.testomie.data.OmieRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: OmieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OmieViewModel::class.java)) {
            return modelClass.getDeclaredConstructor(OmieRepository::class.java)
                .newInstance(repository)
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}