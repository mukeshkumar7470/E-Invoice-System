package com.example.e_invoicesystem.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseproject.appRepository.AppRepository
import com.example.firebaseproject.db.DataModel
import com.example.firebaseproject.db.InvoiceDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityViewModel(app: Application, private val repository: AppRepository): AndroidViewModel(app) {

  fun insertData(model: DataModel?) = viewModelScope.launch(Dispatchers.Main) {
        repository.insertData(model)
    }

    fun getData() = repository.getData()

    fun updateData(model: DataModel?) = viewModelScope.launch(Dispatchers.Main) {
        repository.updateData(model)
    }


    fun deleteData(id:Int) = viewModelScope.launch(Dispatchers.Main) {
        repository.deleteData(id)
    }

   /* fun truncateViewData() = viewModelScope.launch(Dispatchers.Main) {
           repository.truncateViewData()
    }*/

   fun insertInvoiceData(model: InvoiceDataModel?) = viewModelScope.launch(Dispatchers.Main) {
       repository.insertInvoiceData(model)
   }

    fun updateDataInInvoice(itemStock:Int?,id:Int?) = viewModelScope.launch(Dispatchers.Main) {
        repository.updateDataInInvoice(itemStock,id)
    }

    fun getInvoiceData() = repository.getInvoiceData()



}