package com.example.expencetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.expencetracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ChangeFragment(Expense_Fragment())

        binding.BottomNavView.setOnItemSelectedListener{


            when(it.itemId){
                R.id.Expence ->{
                    ChangeFragment(Expense_Fragment())
                }
                R.id.Income ->{
                    ChangeFragment(Income())
                }
                R.id.Balance ->{
                    ChangeFragment(Balanse())
                }

            }
            return@setOnItemSelectedListener true
        }
    }
    fun ChangeFragment(fragment: Fragment){

        var fragmentManager:FragmentManager = supportFragmentManager
        var fragmenttransaction:FragmentTransaction = fragmentManager.beginTransaction()
        fragmenttransaction.replace(R.id.MainFrame,fragment)
        fragmenttransaction.commit()
    }


}