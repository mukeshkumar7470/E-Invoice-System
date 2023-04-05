package com.example.e_invoicesystem.adapter
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_invoicesystem.ItemClickInterface
import com.example.e_invoicesystem.R
import com.example.e_invoicesystem.activity.ShowDataActivity
import com.example.e_invoicesystem.activity.UpdateActivity
import com.example.e_invoicesystem.databinding.ShowDataBinding
import com.example.firebaseproject.db.DataModel

class ShowDataAdpter(
    var contaxt: ShowDataActivity,
    var itemList: ArrayList<DataModel?>,
    var itemClickInterface: ItemClickInterface
) : RecyclerView.Adapter<ShowDataAdpter.ViewHolder>() {

    fun setItemlist(mList: ArrayList<DataModel?>) {
        this.itemList = mList
        notifyDataSetChanged()
    }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            return ViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context), R.layout.show_data, parent, false
                )
            )
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model = itemList!![position]
        if (itemList!!.size!=null){
            holder.mBinding.tvItemId.text=model!!.id.toString()
            holder.mBinding.tvItemName.text=model!!.itemName
            holder.mBinding.tvItemPrice.text=model!!.itemPrice.toString()
            holder.mBinding.tvItemStock.text=model!!.itemStock.toString()
        }
        holder.mBinding.tvUpdate.setOnClickListener {
            var  intent = Intent(contaxt, UpdateActivity::class.java)
            intent.putExtra("Id", model!!.id)
            intent.putExtra("ItemName", model!!.itemName)
            intent.putExtra("ItemPrice", model!!.itemPrice)
            intent.putExtra("ItemStock", model!!.itemStock)
            contaxt.startActivity(intent)
        }

        holder.mBinding.tvDelete.setOnClickListener {
            itemClickInterface.click(model!!.id, itemList!![position])
            itemClickInterface.click(model!!.id,itemList!![position])
        }
    }


    override fun getItemCount(): Int {
        return itemList!!.size
    }

    class ViewHolder(var mBinding:ShowDataBinding) :
        RecyclerView.ViewHolder(mBinding.root)
}

