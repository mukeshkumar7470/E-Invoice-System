package com.example.e_invoicesystem.activity

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.e_invoicesystem.R
import com.example.e_invoicesystem.appRepository.MainActivityViewModelFactory
import com.example.e_invoicesystem.databinding.ActivityInvoiceBinding
import com.example.firebaseproject.appRepository.AppRepository
import com.example.firebaseproject.db.DataModel
import com.example.firebaseproject.db.InvoiceDataModel


class InvoiceActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityInvoiceBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var edtUserName: EditText
    lateinit var edtItemQty: EditText
    lateinit var edtUserMobileNumbere: EditText
    lateinit var spinner: Spinner
    var userName: String = ""
    var UserMobileNumber: String = ""
    var itemPrice: String = ""
    var itemName: String = ""
    var itemStockQty: String = ""
    var itemMrp: String = ""
    var id: Int = 0
    var itemNameList = ArrayList<String>()
    lateinit var dataList: List<DataModel?>
    var isStock = true
    var currentItemStock:Int?=0
    var itemId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_invoice)

        var appRepository = AppRepository(applicationContext)
        mainActivityViewModel = ViewModelProvider(
            this, MainActivityViewModelFactory(
                applicationContext as Application, appRepository
            )
        )[MainActivityViewModel::class.java]


        itemNameList.add(0, "Select Item Name")

        dataList = mainActivityViewModel.getData()!!
        if (!dataList.isNullOrEmpty()) {
            dataList.forEach {
                if (!it!!.itemName.isNullOrEmpty()) {
                    itemNameList.add(it.itemName!!)
                }
            }
        }


        edtUserName = mBinding.edtUserName
        edtUserMobileNumbere = mBinding.edtUserMobileNumbere
        spinner = mBinding.spinner
        edtItemQty=mBinding.edtStockQuantity
        //var languages = resources.getStringArray(R.array.Languages)

        if (mBinding.spinner != null) {

            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemNameList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mBinding.spinner.adapter = adapter


            mBinding.spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    Toast.makeText(
                        this@InvoiceActivity,
                        "Selected item:" + " " + "" + itemNameList[position],
                        Toast.LENGTH_SHORT
                    ).show()

                    if (!dataList.isNullOrEmpty()) {
                        isStock = true
                        if (isStock) {
                            dataList.forEach {
                                if (!it!!.itemName.isNullOrEmpty()) {
                                    if (itemNameList[position] == it!!.itemName) {
                                        itemPrice = it.itemPrice.toString()
                                        itemName = it.itemName.toString()
                                        itemMrp = it.itemPrice.toString()
                                        currentItemStock=it.itemStock
                                        itemId=it.id

                                        if (it.itemStock!! > 0) {
                                            Log.e("TAG", "onCredcxsZXcate1: ")
                                        } else {
                                            Log.e("TAG", "onCredcxsZXsdfsfsfdsfasdffacate:2 ")

                                        }
                                        isStock = false
                                    }

                                }

                            }
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action

                }
            }
        }



        mBinding.btnInvoiceHistory.setOnClickListener {
            if (CheckAllFields()) {
                userName = edtUserName.text.toString().trim()
                UserMobileNumber = edtUserMobileNumbere.text.toString()
                itemStockQty = edtItemQty.text.toString().trim()

                if (isStock) {

                } else {
                    if(currentItemStock!=0){
                        var currentItemStocks= (currentItemStock)?.minus((Integer.parseInt(itemStockQty)))
                        if (currentItemStocks!! > 0){
                            var model = InvoiceDataModel(0, userName, UserMobileNumber, itemName, itemPrice,itemStockQty, itemMrp)
                            mainActivityViewModel.insertInvoiceData(model)
                            mainActivityViewModel.updateData(DataModel(itemName = itemName, id = itemId, itemPrice = itemPrice.toInt(), itemStock = currentItemStocks))
                            Toast.makeText(this, "Item Added Successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@InvoiceActivity, MainActivity::class.java))
                            finishAffinity()
                        }else{
                            Toast.makeText(this, "This Item is out of Stock", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun CheckAllFields(): Boolean {
        if (edtUserName.length() === 0) {
            edtUserName.setError("This field is required")
            return false
        }
        if (edtUserMobileNumbere.length() === 0) {
            edtUserMobileNumbere.setError("This field is required")
            return false
        }
        if (edtItemQty.length() === 0) {
            edtItemQty.setError("This field is required")
            return false
        }
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}