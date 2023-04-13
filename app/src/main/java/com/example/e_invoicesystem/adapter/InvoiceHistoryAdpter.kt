package com.example.e_invoicesystem.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_invoicesystem.activity.InvoicePdfActivity
import com.example.e_invoicesystem.R
import com.example.e_invoicesystem.databinding.ShowInvoiceHistoryBinding
import com.example.firebaseproject.db.InvoiceDataModel

class InvoiceHistoryAdpter(
    var contaxt: Context,
    var historyList: ArrayList<InvoiceDataModel?>,
) : RecyclerView.Adapter<InvoiceHistoryAdpter.ViewHolder>() {

    fun setItemlist(mList: ArrayList<InvoiceDataModel?>) {
        this.historyList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.show_invoice_history, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model = historyList!![position]
        if (historyList!!.size != null) {

            holder.mBinding.tvItemId.text = model!!.id.toString()
            holder.mBinding.tvUserName.text = model!!.userName
            holder.mBinding.tvMobileNo.text = model!!.mobileNumber
            holder.mBinding.tvItemName.text = model!!.itemName
            holder.mBinding.tvItemPrice.text = model!!.itemPrice
            holder.mBinding.tvItemQty.text = model!!.itemQty

        }
        holder.mBinding.tvInvoicePdf.setOnClickListener {
            var intent = Intent(contaxt, InvoicePdfActivity::class.java)
            intent.putExtra("name",model!!.userName)
            intent.putExtra("mobileNumber",model!!.mobileNumber)
            intent.putExtra("itemName",model!!.itemName)
            intent.putExtra("itemPrice",model!!.itemPrice)
            intent.putExtra("itemQty",model!!.itemQty)
            intent.putExtra("itemMrp",model!!.itemMrp)
            contaxt.startActivity(intent)
        }

    }


    override fun getItemCount(): Int {
        return historyList!!.size
    }

    class ViewHolder(var mBinding: ShowInvoiceHistoryBinding) :
        RecyclerView.ViewHolder(mBinding.root)
}

