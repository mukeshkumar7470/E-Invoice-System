package com.example.e_invoicesystem.activity

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.e_invoicesystem.R
import com.example.e_invoicesystem.adapter.InvoiceHistoryAdpter
import com.example.e_invoicesystem.appRepository.MainActivityViewModelFactory
import com.example.e_invoicesystem.databinding.ActivityInvoiceHistoryBinding
import com.example.firebaseproject.appRepository.AppRepository
import com.example.firebaseproject.db.InvoiceDataModel

class InvoiceHistoryActivity : AppCompatActivity() {
    lateinit var mBinding:ActivityInvoiceHistoryBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var adpter: InvoiceHistoryAdpter
    var  histroyList = ArrayList<InvoiceDataModel?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView(this, R.layout.activity_invoice_history)
        var appRepository = AppRepository(applicationContext)
       mainActivityViewModel = ViewModelProvider(this, MainActivityViewModelFactory(
            applicationContext as Application,appRepository)
        )[MainActivityViewModel::class.java]

        histroyList.addAll(mainActivityViewModel.getInvoiceData()!!)

        Log.e("TAG", "fgdsfgs$histroyList: " )

        adpter = InvoiceHistoryAdpter(this, histroyList)
        mBinding.rvList.setHasFixedSize(true)
        mBinding.rvList.adapter= adpter

    }
}