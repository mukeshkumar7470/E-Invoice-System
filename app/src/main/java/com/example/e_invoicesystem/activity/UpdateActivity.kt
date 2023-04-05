package com.example.e_invoicesystem.activity

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.e_invoicesystem.R
import com.example.e_invoicesystem.activity.ShowDataActivity.Companion.mainActivityViewModel
import com.example.e_invoicesystem.appRepository.MainActivityViewModelFactory
import com.example.e_invoicesystem.databinding.ActivityUpdateBinding
import com.example.firebaseproject.appRepository.AppRepository
import com.example.firebaseproject.db.DataModel

class UpdateActivity : AppCompatActivity() {
    lateinit var mBinding:ActivityUpdateBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var edtItemName: EditText
    lateinit var edtItemPrice: EditText
    lateinit var edtItemStock: EditText
    var itemName:String=""
    var itemPrice:Int=0
    var itemStok:Int=0
    var id:Int=0
    var name:String?=""
    var stock:Int=0
    var price:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView(this,R.layout.activity_update)
        var appRepository = AppRepository(applicationContext)

       mainActivityViewModel = ViewModelProvider(this, MainActivityViewModelFactory(
            applicationContext as Application,appRepository)
        )[MainActivityViewModel::class.java]

        edtItemName= mBinding.edtItemName
        edtItemPrice= mBinding.edtItemPrice
        edtItemStock= mBinding.edtItemStock
        id= intent.getIntExtra("Id",0)
        name= intent.getStringExtra("ItemName")
        stock= intent.getIntExtra("ItemStock",0)
        price= intent.getIntExtra("ItemPrice",0)

        edtItemName.setText(name)
        edtItemPrice.setText(price.toString())
        edtItemStock.setText(stock.toString())

        mBinding.btnSave.setOnClickListener {

            if (CheckAllFields()){
                itemName=edtItemName.text.toString().trim()
                itemPrice=Integer.parseInt( edtItemPrice.text.toString().trim())
                itemStok=Integer.parseInt(edtItemStock.text.toString().trim())
                var model=DataModel(id,itemName,itemPrice,itemStok)
                mainActivityViewModel.updateData(model)
                Toast.makeText(this, "Update Data", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@UpdateActivity, ShowDataActivity::class.java))
                finish()
            }

        }



    }
    private fun CheckAllFields(): Boolean {
        if (edtItemName.length() === 0) {
            edtItemName.setError("This field is required")
            return false
        }
        if (edtItemPrice.length() === 0) {
            edtItemPrice.setError("This field is required")
            return false
        }
        if (edtItemStock.length() === 0) {
            edtItemStock.setError("Email is required")
            return false
        }

        // after all validation return true.
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}