package com.example.expensemanagerproject.Helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.expensemanagerproject.ModelClass.CategoryModelClass
import com.example.expensemanagerproject.ModelClass.IncomeExpenseModelClass

class DataHelper(context: Context) : SQLiteOpenHelper(context, "Categorydb", null, 1) {
    var datalist = ArrayList<CategoryModelClass>()
    var incomeExpenselist = ArrayList<IncomeExpenseModelClass>()
    override fun onCreate(db: SQLiteDatabase?) {
        var table1 = "Create table Categorytb(name text)"

        var table2 =
            "Create table incomeExpensetb(incomeexpense_id integer primary key Autoincrement,date text,amount integer,category_name text ,mode text,type integer,note text)"
        db?.execSQL(table1)


        db?.execSQL(table2)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertCategory(name: String) {
        val db = writableDatabase
        var c = ContentValues()
        c.put("name", name)
        db.insert("Categorytb", null, c)

    }

    fun displaycategory(): ArrayList<CategoryModelClass> {
        datalist.clear()

        val db = readableDatabase
        val sql = "select * from Categorytb"
        var cursor = db.rawQuery(sql, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {

                val name = cursor.getString(0)

                var categorylist = CategoryModelClass(name)
                datalist.add(categorylist)
            } while (cursor.moveToNext())


            Log.e("TAG", "display:size " + datalist.size)
        } else {
            Log.e("TAG", "display: " + "no data found")
        }
        return datalist
    }

    fun insertIncomeExpense(
        datevalue: String,
        amount: String,
        selectcategory: String,
        selectmode: String,
        type: Int,
        note: String
    ) {
        var db = writableDatabase
        var add = ContentValues()
        add.put("date", datevalue)
        add.put("amount", amount)
        add.put("category_name", selectcategory)
        add.put("mode", selectmode)
        add.put("type", type)
        add.put("note", note)
        db.insert("incomeExpensetb", null, add)

    }

    fun displayIncomeExpense(): ArrayList<IncomeExpenseModelClass> {
        incomeExpenselist.clear()
        var db = readableDatabase
        val sql1 = "select * from incomeExpensetb"
        var cursor = db.rawQuery(sql1, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                var id = cursor.getInt(0)
                val date = cursor.getString(1)
                val amount = cursor.getString(2)
                val category = cursor.getString(3)
                val mode = cursor.getString(4)
                val type = cursor.getInt(5)
                val note = cursor.getString(6)

                var incomeExpense =
                    IncomeExpenseModelClass(id, date, amount, category, mode, type, note)
                incomeExpenselist.add(incomeExpense)
            } while (cursor.moveToNext())
        }

        return incomeExpenselist
    }

    fun update(amount: String, note: String, id_number: Int) {
        val update = writableDatabase
        val updateSQl =
            "update incomeExpensetb set amount='$amount',note='$note'where incomeexpense_id='$id_number'"
        update.execSQL(updateSQl)
    }

    fun delete(id: Int) {
        var delete = writableDatabase
        var sqldelete = "delete from incomeExpensetb where incomeexpense_id='$id'"
        delete.execSQL(sqldelete)
    }

}