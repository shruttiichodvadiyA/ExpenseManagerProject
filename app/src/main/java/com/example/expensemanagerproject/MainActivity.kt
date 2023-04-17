package com.example.expensemanagerproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expensemanagerproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var title_income="Add Income"
        binding.layoutincome.setOnClickListener {
            val income = Intent(this, IncomeExpense_Activity::class.java)
            income.putExtra("page","income")
            income.putExtra("title",title_income)
            startActivity(income)
        }
        var title_expense="Add Expense"
        binding.layoutexpense.setOnClickListener {
            val expense = Intent(this, IncomeExpense_Activity::class.java)
            expense.putExtra("page","expense")
            expense.putExtra("title",title_expense)
            startActivity(expense)
        }
        binding.layoutcategory.setOnClickListener {
            val intent = Intent(this, AddCategory_Activity::class.java)
            startActivity(intent)
        }
        binding.layouttrans.setOnClickListener {
            val intent = Intent(this, Transactions_Activity::class.java)
            startActivity(intent)
        }

        binding.imgcalender.setOnClickListener {
            val intent = Intent(this, Calender_Activity::class.java)
            startActivity(intent)
        }
        binding.imgmenu.setOnClickListener {

        }
    }
}