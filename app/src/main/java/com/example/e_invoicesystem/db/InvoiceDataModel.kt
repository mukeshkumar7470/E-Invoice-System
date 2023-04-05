package com.example.firebaseproject.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "INVOICE_DATA")
class InvoiceDataModel : Serializable {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("Id")
    var id: Int = 0

    @SerializedName("UserName")
    var userName: String ?= ""

    @SerializedName("MobileNumber")
    var mobileNumber: String ?= ""

    @SerializedName("ItemName")
    var itemName: String ?= ""

    @SerializedName("ItemPrice")
    var itemPrice: String ?= ""

    @SerializedName("ItemQty")
    var itemQty: String ?= ""

    constructor(
        id: Int,
        userName: String?,
        mobileNumber: String?,
        itemName: String?,
        itemPrice: String?,
        itemQty:String?
    ) {
        this.id = id
        this.userName = userName
        this.mobileNumber = mobileNumber
        this.itemName = itemName
        this.itemPrice = itemPrice
        this.itemQty=itemQty
    }

    override fun toString(): String {
        return "InvoiceDataModel(id=$id, userName=$userName, mobileNumber=$mobileNumber, itemName=$itemName, itemPrice=$itemPrice,itemQty=$itemQty)"
    }


}
