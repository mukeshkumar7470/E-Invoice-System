package com.example.e_invoicesystem
data class PdfDetails(
    val studentName:String,
    val studentMobile:String,
    val totalMarks:Double,
    val invoiceDetailsList: List<InvoiceDetails>
)
