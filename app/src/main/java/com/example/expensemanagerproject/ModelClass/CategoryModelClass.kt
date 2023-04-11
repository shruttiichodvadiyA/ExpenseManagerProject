package com.example.expensemanagerproject.ModelClass

data class CategoryModelClass(val categoryname:String) {
}
data class IncomeExpenseModelClass(
    var id :Int,
    var date:String,
    var amount:String,
    var category:String,
    var mode:String,
    var type: Int,
    val note:String){

}
