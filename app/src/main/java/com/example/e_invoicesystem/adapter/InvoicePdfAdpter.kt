package com.example.e_invoicesystem.adapter
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_invoicesystem.InvoicePdfActivity
import com.example.e_invoicesystem.R
import com.example.e_invoicesystem.activity.ShowDataActivity.Companion.adpter
import com.example.e_invoicesystem.activity.ShowDataActivity.Companion.mainActivityViewModel
import com.example.e_invoicesystem.activity.UpdateActivity
import com.example.e_invoicesystem.databinding.InvoicePdfBinding
import com.example.e_invoicesystem.databinding.ShowInvoiceHistoryBinding
import com.example.firebaseproject.db.InvoiceDataModel
import java.io.File
import java.io.FileOutputStream

class InvoicePdfAdpter(
    var contaxt: InvoicePdfActivity,
    var historyList: ArrayList<InvoiceDataModel?>,
) : RecyclerView.Adapter<InvoicePdfAdpter.ViewHolder>() {

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
                    LayoutInflater.from(parent.context), R.layout.invoice_pdf, parent, false
                )
            )
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model = historyList!![position]



        val inflater = LayoutInflater.from(contaxt)
        val view = inflater.inflate(R.layout.invoice_pdf, null)



//Fetch the dimensions of the viewport
        val displayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            contaxt.display?.getRealMetrics(displayMetrics)
            displayMetrics.densityDpi
        }
        else{
            contaxt.windowManager.defaultDisplay.getMetrics(displayMetrics)
        }
        view.measure(
            View.MeasureSpec.makeMeasureSpec(
                displayMetrics.widthPixels, View.MeasureSpec.EXACTLY
            ),
            View.MeasureSpec.makeMeasureSpec(
                displayMetrics.heightPixels, View.MeasureSpec.EXACTLY
            )
        )


        //Create a bitmap with the measured width and height. Attach the bitmap to a canvas object and draw the view inside the canvas
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)

        Bitmap.createScaledBitmap(bitmap, 595, 842, true)

        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()

        val page = pdfDocument.startPage(pageInfo)
        page.canvas.drawBitmap(bitmap, 0F, 0F, null)
        pdfDocument.finishPage(page)

        val filePath = File(contaxt.getExternalFilesDir(null), "bitmapPdf.pdf")
        pdfDocument.writeTo(FileOutputStream(filePath))

        pdfDocument.close()


        if (historyList!!.size!=null){

           /* holder.mBinding.tvItemId.text=model!!.id.toString()
            holder.mBinding.tvUserName.text=model!!.userName
            holder.mBinding.tvMobileNo.text=model!!.mobileNumber
            holder.mBinding.tvItemName.text=model!!.itemName
            holder.mBinding.tvItemPrice.text=model!!.itemPrice
            holder.mBinding.tvItemQty.text=model!!.itemQty*/

        }
       /* holder.mBinding.tvInvoicePdf.setOnClickListener {
            var  intent = Intent(contaxt, UpdateActivity::class.java)
            intent.putExtra("Id", model!!.id)
            contaxt.startActivity(intent)
        }*/


    }


    override fun getItemCount(): Int {
        return 1
    }

    class ViewHolder(var mBinding:InvoicePdfBinding) :
        RecyclerView.ViewHolder(mBinding.root)

    fun pdf(){

    }
}

