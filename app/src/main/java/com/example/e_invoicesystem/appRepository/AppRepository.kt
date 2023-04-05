package com.example.firebaseproject.appRepository

import android.content.Context
import com.example.e_invoicesystem.appRepository.MyApplication
import com.example.firebaseproject.db.DataModel
import com.example.firebaseproject.db.InvoiceDataModel

class AppRepository(applicationContext: Context) {
    //var apiService = (applicationContext as MyApplication).apiService
    var appdatabase = (applicationContext as MyApplication).appDatabase

   suspend fun insertData(model: DataModel?) = appdatabase.AppDaoAccess()?.insertData(model)
    fun getData() = appdatabase.AppDaoAccess().getData()
    suspend fun updateData(model: DataModel?) = appdatabase.AppDaoAccess().updateData(model)
    suspend fun deleteData(id:Int) = appdatabase.AppDaoAccess()?.deleteData(id)
  //  suspend fun truncateData() = appdatabase.AppDaoAccess()?.truncateData()

    suspend fun insertInvoiceData(model: InvoiceDataModel?) = appdatabase.AppDaoAccess()?.insertInvoiceData(model)

    suspend fun updateDataInInvoice(itemStock:Int?,id:Int?) = appdatabase.AppDaoAccess().updateDataInInvoice(itemStock,id)

    fun getInvoiceData() = appdatabase.AppDaoAccess().getInvoiceData()


}
