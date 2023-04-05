package com.example.firebaseproject.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "ITEM_DATA")
class DataModel : Serializable {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("Id")
    var id: Int = 0

    @SerializedName("ItemName")
    var itemName: String ?= ""

    @SerializedName("ItemPrice")
    var itemPrice: Int? = 0

    @SerializedName("ItemStock")
    var itemStock: Int ?= 0

    constructor(id: Int ,itemName: String?, itemPrice: Int?, itemStock: Int?) {
        this.id=id
        this.itemName = itemName
        this.itemPrice = itemPrice
        this.itemStock = itemStock
    }

    override fun toString(): String {
        return "DataModel(id=$id, itemName=$itemName, itemPrice=$itemPrice, itemStock=$itemStock)"
    }


}
