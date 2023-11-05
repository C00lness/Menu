package com.example.dodomainlayout.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dodomainlayout.data.Repository
import com.example.dodomainlayout.helper.Utility
import com.example.dodomainlayout.network.model.CategoryArray
import com.example.dodomainlayout.view.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(private val repository: Repository) : ViewModel() {
    val categoryArray = MutableLiveData<CategoryArray>()

    fun getcategory(){
        viewModelScope.launch(Dispatchers.IO) {
            categoryArray.postValue(repository.getData())
        }
    }

    fun getcategorybyDb(){
        viewModelScope.launch(Dispatchers.IO) {
            categoryArray.postValue(repository.getDataFromDb())
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val myRepository = application.container.repository
                MyViewModel(repository = myRepository)
            }
        }
    }
}