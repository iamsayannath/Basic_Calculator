package com.example.sncalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNum : Boolean = false
    var lastDot : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNum = true
    }

    fun onClear(view: View) {
        tvInput.text = ""
        tvOutput.text = ""
        lastDot = false
        lastNum = false
    }

    fun onDot(view: View) {
        if(lastNum && !lastDot){
            tvInput.append(".")
            lastNum = false
            lastDot = true
        }
    }

    private fun removeZeroAfterDot(result: String) : String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return value
    }

    fun onOperator(view: View){
        if (lastNum && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNum = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String) : Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") ||
            value.contains("x") ||
            value.contains("+") ||
            value.contains("-")
        }
    }

    fun onEqual(view: View) {
        if(lastNum){
            var tvValue = tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvOutput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvOutput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }else if (tvValue.contains("x")){
                    val splitValue = tvValue.split("x")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvOutput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvOutput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onBack(view: View) {

    }
}