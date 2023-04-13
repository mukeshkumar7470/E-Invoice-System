package com.example.e_invoicesystem.activity

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.e_invoicesystem.R
import com.example.e_invoicesystem.adapter.InvoiceHistoryAdpter
import com.example.e_invoicesystem.appRepository.MainActivityViewModelFactory
import com.example.e_invoicesystem.databinding.ActivityMainBinding
import com.example.firebaseproject.appRepository.AppRepository
import com.example.firebaseproject.db.InvoiceDataModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    //new
    // Make sure to use the FloatingActionButton for all the FABs
    private lateinit var mAddFab: FloatingActionButton
    private lateinit var mAddAlarmFab: FloatingActionButton
    private lateinit var mAddPersonFab: FloatingActionButton
    private lateinit var mAddShowStockFab: FloatingActionButton

    // These are taken to make visible and invisible along with FABs
    private lateinit var addAlarmActionText: TextView
    private lateinit var addPersonActionText: TextView
    private lateinit var showStockActionText: TextView

    // to check whether sub FAB buttons are visible or not.
    private var isAllFabsVisible: Boolean? = null

    lateinit var mBinding:ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var adpter: InvoiceHistoryAdpter
    var  histroyList = ArrayList<InvoiceDataModel?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        var appRepository = AppRepository(applicationContext)
        mainActivityViewModel = ViewModelProvider(this, MainActivityViewModelFactory(
            applicationContext as Application,appRepository)
        )[MainActivityViewModel::class.java]
        mAddFab = findViewById(R.id.add_fab)

        mAddAlarmFab = findViewById(R.id.add_alarm_fab)
        mAddPersonFab = findViewById(R.id.add_person_fab)
        mAddShowStockFab = findViewById(R.id.show_item_fab)

        addAlarmActionText = findViewById(R.id.add_alarm_action_text)
        addPersonActionText = findViewById(R.id.add_person_action_text)
        showStockActionText = findViewById(R.id.show_item_action_text)

        mAddAlarmFab.visibility = View.GONE
        mAddPersonFab.visibility = View.GONE
        mAddShowStockFab.visibility = View.GONE
        addAlarmActionText.visibility = View.GONE
        addPersonActionText.visibility = View.GONE
        showStockActionText.visibility = View.GONE

        isAllFabsVisible = false

        mAddFab.setOnClickListener(View.OnClickListener {
            (if (!isAllFabsVisible!!) {
                mAddAlarmFab.show()
                mAddPersonFab.show()
                mAddShowStockFab.show()
                addAlarmActionText.visibility = View.VISIBLE
                addPersonActionText.visibility = View.VISIBLE
                showStockActionText.visibility = View.VISIBLE

                true
            } else {
                mAddAlarmFab.hide()
                mAddPersonFab.hide()
                mAddShowStockFab.hide()
                addAlarmActionText.visibility = View.GONE
                addPersonActionText.visibility = View.GONE
                showStockActionText.visibility = View.GONE

                false
            }).also { isAllFabsVisible = it }
        })
        mAddPersonFab.setOnClickListener {
            startActivity(Intent(this@MainActivity, InvoiceActivity::class.java))
        }

        mAddAlarmFab.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddNewItemActivity::class.java))
        }

        mAddShowStockFab.setOnClickListener {
            startActivity(Intent(this@MainActivity, ShowDataActivity::class.java))
        }


    }

    override fun onResume() {
        super.onResume()
        callToSetData()
    }

    private fun callToSetData() {
        histroyList.clear()
        histroyList.addAll(mainActivityViewModel.getInvoiceData()!!)
        adpter = InvoiceHistoryAdpter(this, histroyList)
        mBinding.rvList.setHasFixedSize(true)
        mBinding.rvList.adapter= adpter
    }
}