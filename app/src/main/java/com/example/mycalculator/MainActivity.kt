package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastnumeric: Boolean = false
    var lastdot: Boolean= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastnumeric = true
        lastdot = false
    }
    fun OnClear(view: View){
        tvInput?.text = ""
    }
    fun OnDecimalPOint(view: View){
        if(lastnumeric && !lastdot == true){
           tvInput?.append(".")
           lastdot = false
           lastnumeric = true
        }

    }

    fun Onoperator(view: View){
        tvInput?.text?.let {
            if(lastnumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastnumeric = false
                lastdot = false
            }
        }
    }

    fun OnEqual(view:View){

        if(lastnumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try{
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroafterDot((one.toDouble() - two.toDouble()).toString())
                }
                else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroafterDot((one.toDouble() + two.toDouble()).toString())
                }
                else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroafterDot((one.toDouble() * two.toDouble()).toString())
                }
                else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroafterDot((one.toDouble() / two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removeZeroafterDot(result: String): String {
        var value = result
        if(result.contains(".0")){
            value = result.substring(0,result.length - 2)
        }
        return value
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/") || value.contains("*") || value.contains("+") ||
                    value.contains("-")
        }
    }

}