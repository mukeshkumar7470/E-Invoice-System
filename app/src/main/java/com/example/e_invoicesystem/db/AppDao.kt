package com.example.firebaseproject.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface AppDao {
    @Insert
    suspend fun insertData(model: DataModel?)

    @Query("SELECT * FROM ITEM_DATA")
    fun getData(): List<DataModel?>?

  /*  @Query("DELETE FROM AppDataBase")
    suspend fun truncateData()
*/
    @Query("DELETE FROM ITEM_DATA Where Id = :id")
    suspend fun deleteData(id:Int?)

    @Update
    suspend fun updateData(model: DataModel?)

    @Insert
    suspend fun insertInvoiceData(model: InvoiceDataModel?)

    @Query("UPDATE ITEM_DATA SET ItemStock = :itemStock WHERE Id = :id")
    suspend fun updateDataInInvoice(itemStock:Int?,id:Int?)

    @Query("SELECT * FROM INVOICE_DATA")
    fun getInvoiceData(): List<InvoiceDataModel?>?
}
