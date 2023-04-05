package com.example.e_invoicesystem.activity

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.e_invoicesystem.ItemClickInterface
import com.example.e_invoicesystem.R
import com.example.e_invoicesystem.adapter.ShowDataAdpter
import com.example.e_invoicesystem.appRepository.MainActivityViewModelFactory
import com.example.e_invoicesystem.databinding.ActivityShowDataBinding
import com.example.firebaseproject.appRepository.AppRepository
import com.example.firebaseproject.db.DataModel

class ShowDataActivity : AppCompatActivity(), ItemClickInterface {
    lateinit var mBinding:ActivityShowDataBinding
    var  itemList = ArrayList<DataModel?>()
    companion object{
        lateinit var mainActivityViewModel: MainActivityViewModel
        lateinit var adpter: ShowDataAdpter
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView(this, R.layout.activity_show_data)

        var appRepository = AppRepository(applicationContext)

        mainActivityViewModel = ViewModelProvider(this, MainActivityViewModelFactory(
            applicationContext as Application,appRepository)
        )[MainActivityViewModel::class.java]

        itemList.addAll(mainActivityViewModel.getData()!!)

        Log.e("TAG", "fgdsfgs$itemList: " )

        adpter= ShowDataAdpter(this, itemList,this)
        mBinding.rvList.setHasFixedSize(true)
        mBinding.rvList.adapter= adpter
    }

    override fun click(id: Int, dataModel: DataModel?) {
        mainActivityViewModel.deleteData(id)
        onResume()
        Toast.makeText(this, "Delete Data", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        itemList.clear()
        itemList.addAll(mainActivityViewModel.getData()!!)
        adpter.setItemlist(itemList)
    }
}