package com.example.e_invoicesystem.activity

import android.app.Application
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.e_invoicesystem.PDFConverter
import com.example.e_invoicesystem.PdfDetails
import com.example.e_invoicesystem.R
import com.example.e_invoicesystem.InvoiceDetails
import com.example.e_invoicesystem.appRepository.MainActivityViewModelFactory
import com.example.e_invoicesystem.databinding.ActivityInvoicePdfBinding
import com.example.firebaseproject.appRepository.AppRepository


class InvoicePdfActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityInvoicePdfBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var generatePDFBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_invoice_pdf)

        var appRepository = AppRepository(applicationContext)
        mainActivityViewModel = ViewModelProvider(
            this, MainActivityViewModelFactory(
                applicationContext as Application, appRepository
            )
        )[MainActivityViewModel::class.java]

        generatePDFBtn = findViewById(R.id.idBtnGeneratePdf)

        var extras = intent.extras
        var name = ""
        var mobileNumber = ""
        var itemName = ""
        var itemPrice = ""
        var itemQty = ""
        var itemMrp = ""
        if (extras != null) {
            name = extras.getString("name").toString()
            mobileNumber = extras.getString("mobileNumber").toString()
            itemName = extras.getString("itemName").toString()
            itemPrice = extras.getString("itemPrice").toString()
            itemQty = extras.getString("itemQty").toString()
            itemMrp = extras.getString("itemMrp").toString()

            mBinding.tvItemName.text = itemName
            mBinding.tvName.text = name
            mBinding.tvMobileNumber.text = mobileNumber
            mBinding.tvItemStock.text = itemQty
            mBinding.tvItemPrice.text = itemPrice
        }

        generatePDFBtn.setOnClickListener {
                val invoiceDetailsList = listOf(
                    InvoiceDetails(itemName!!, itemPrice!!.toDouble(), itemMrp!!, itemQty!!)
                )
                var totalMarks =0.0
                invoiceDetailsList.forEach {
                    totalMarks += it.itemPrice
                }
                val pdfDetails = PdfDetails(name!!,mobileNumber!!, totalMarks!!, invoiceDetailsList)
                val pdfConverter = PDFConverter()
                pdfConverter.createPdf(this, pdfDetails, this)
        }
    }
}