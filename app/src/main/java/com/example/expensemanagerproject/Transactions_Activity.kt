package com.example.expensemanagerproject

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanagerproject.Adapter.CategoryAdapter
import com.example.expensemanagerproject.Adapter.IncomeExpenseAdapter
import com.example.expensemanagerproject.Helper.DataHelper
import com.example.expensemanagerproject.ModelClass.IncomeExpenseModelClass
import com.example.expensemanagerproject.databinding.ActivityTransactionsBinding
import com.example.expensemanagerproject.databinding.CategoryDialogBinding
import com.example.expensemanagerproject.databinding.DeleteDialogBinding

class Transactions_Activity : AppCompatActivity() {

    lateinit var binding: ActivityTransactionsBinding
    var incomeExpenselist = ArrayList<IncomeExpenseModelClass>()
    lateinit var adapter: IncomeExpenseAdapter
    lateinit var db: DataHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DataHelper(this)


        incomeExpense()
    }

    private fun incomeExpense() {

        incomeExpenselist = db.displayIncomeExpense()
        var title = "Update Details"
        var icon = "Update"
        adapter = IncomeExpenseAdapter(incomeExpenselist, {
            val i = Intent(this, IncomeExpense_Activity::class.java)
            i.putExtra("id", +it.id)
            Log.e("TAG", "incomeExpense: " + it.id)
            i.putExtra("type", it.type)
            i.putExtra("amount", it.amount)
            i.putExtra("note", it.note)
            i.putExtra("title", title)
            i.putExtra("key_icon", icon)
            i.putExtra("updateRecord", true)
            startActivity(i)
        }) {id ->


            val dialog = Dialog(this)
            val dialogBinding: DeleteDialogBinding = DeleteDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.btnset.setOnClickListener {

                db.delete(id)
                incomeExpenselist = db.displayIncomeExpense()
                adapter.update(incomeExpenselist)
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            dialogBinding.btncancel.setOnClickListener {
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialog.show()
        }

        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvIncomeExpense.layoutManager = manager
        binding.rcvIncomeExpense.adapter = adapter

        incomeExpenselist = db.displayIncomeExpense()
        adapter.update(incomeExpenselist)

        binding.back.setOnClickListener {
           onBackPressed()
        }

    }
}
