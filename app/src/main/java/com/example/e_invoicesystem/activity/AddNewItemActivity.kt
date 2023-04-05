package com.example.e_invoicesystem.activity

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.e_invoicesystem.R
import com.example.e_invoicesystem.appRepository.MainActivityViewModelFactory
import com.example.e_invoicesystem.databinding.ActivityAddNewItemBinding
import com.example.e_invoicesystem.databinding.ActivityMainBinding
import com.example.firebaseproject.appRepository.AppRepository
import com.example.firebaseproject.db.DataModel

class AddNewItemActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityAddNewItemBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var edtItemName: EditText
    lateinit var edtItemPrice: EditText
    lateinit var edtItemStock: EditText
    var itemName:String=""
    var itemPrice:Int=0
    var itemStok:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_add_new_item)
        var appRepository = AppRepository(applicationContext)
        mainActivityViewModel = ViewModelProvider(this, MainActivityViewModelFactory(
            applicationContext as Application,appRepository)
        )[MainActivityViewModel::class.java]

        edtItemName= mBinding.edtItemName
        edtItemPrice= mBinding.edtItemPrice
        edtItemStock= mBinding.edtItemStock

        mBinding.btnSave.setOnClickListener {

            if (CheckAllFields()){
                itemName=edtItemName.text.toString().trim()
                itemPrice=Integer.parseInt( edtItemPrice.text.toString().trim())
                itemStok=Integer.parseInt(edtItemStock.text.toString().trim())
                var model= DataModel(0,itemName,itemPrice,itemStok)
                mainActivityViewModel.insertData(model)
                Toast.makeText(this, "Data Save", Toast.LENGTH_SHORT).show()
            }

        }

        mBinding.btnShow.setOnClickListener{
            startActivity(Intent(this@AddNewItemActivity, ShowDataActivity::class.java))
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