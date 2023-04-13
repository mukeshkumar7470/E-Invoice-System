package com.example.e_invoicesystem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_invoicesystem.R
import com.example.e_invoicesystem.InvoiceDetails

class MarksRecyclerAdapter(private val subjectMarksList: List<InvoiceDetails>) :
    RecyclerView.Adapter<MarksRecyclerAdapter.MarksViewHolder>() {
    class MarksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.txt_item_name)
        val itemMrp: TextView = view.findViewById(R.id.txt_item_mrp)
        val itemPrice: TextView = view.findViewById(R.id.txt_item_price)
        val itemQty: TextView = view.findViewById(R.id.txt_item_qty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarksViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_marks, parent, false)
        return MarksViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarksViewHolder, position: Int) {
        holder.itemName.text = subjectMarksList[position].itemName
        holder.itemMrp.text = subjectMarksList[position].itemMrp
        holder.itemPrice.text = subjectMarksList[position].itemPrice.toString()
        holder.itemQty.text = subjectMarksList[position].itemQty
    }

    override fun getItemCount(): Int {
        return subjectMarksList.size
    }
}