package com.example.e_invoicesystem

import com.example.firebaseproject.db.DataModel

interface ItemClickInterface {
    fun click(id: Int, dataModel: DataModel?)
}