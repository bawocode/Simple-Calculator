package com.bawonelson.threedcalculator

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bawonelson.threedcalculator.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.voiceText?.setOnClickListener{

        }

        binding.clearButton?.setOnClickListener {
            binding.inputText.text = ""
            binding.resultOutput.text = ""
        }

        binding.percentageButton?.setOnClickListener {
            binding.inputText.text = addToInputText("%")
        }

        binding.button0?.setOnClickListener {
            binding.inputText.text = addToInputText("0")
        }
        binding.button1?.setOnClickListener {
            binding.inputText.text = addToInputText("1")
        }
        binding.button2?.setOnClickListener {
            binding.inputText.text = addToInputText("2")
        }
        binding.button3?.setOnClickListener {
            binding.inputText.text= addToInputText("3")
        }
        binding.button4?.setOnClickListener {
            binding.inputText.text = addToInputText("4")
        }
        binding.button5?.setOnClickListener {
            binding.inputText.text = addToInputText("5")
        }
        binding.button6?.setOnClickListener {
            binding.inputText.text = addToInputText("6")

        }
        binding.button7?.setOnClickListener {
            binding.inputText.text = addToInputText("7")
        }
        binding.button8?.setOnClickListener {
            binding.inputText.text = addToInputText("8")
        }
        binding.button9?.setOnClickListener {
            binding.inputText.text = addToInputText("9")
        }
        binding.pointButton?.setOnClickListener {
            if(binding.inputText.text.isEmpty() || binding.inputText.text.toString() != "."){
                binding.inputText.text = addToInputText("0.")
            }else if(binding.inputText.text.isNotEmpty() && binding.inputText.text.toString()[binding.inputText.text.toString().length-1] == '.'){
                binding.inputText.text = addToInputText("")
            }else{
                binding.inputText.text = addToInputText("")
            }
        }
        binding.divisionButton?.setOnClickListener {
            if(binding.inputText.text.isNotEmpty()){
                binding.inputText.text = addToInputText("÷")
            }else{
                binding.inputText.text = addToInputText("")
            }

        }
        binding.multiplicationButton?.setOnClickListener {
            if(binding.inputText.text.isNotEmpty()){
                binding.inputText.text = addToInputText("×")
            }else{
                binding.inputText.text = addToInputText("")
            }

        }
        binding.subtractionButton?.setOnClickListener {
            if(binding.inputText.text.isEmpty()){
                binding.inputText.text = addToInputText("-")
            }else if(binding.inputText.text.isNotEmpty() && binding.inputText.text.toString()[binding.inputText.text.toString().length-1] == '-'){
                binding.inputText.text = addToInputText("")
            }else{
                binding.inputText.text = addToInputText("-")
            }

        }
        binding.additionButton?.setOnClickListener {
            if(binding.inputText.text.isNotEmpty()){
                binding.inputText.text = addToInputText("+")
            }else{
                binding.inputText.text = addToInputText("")
            }

        }

        binding.equalButton?.setOnClickListener {
            if(binding.inputText.text.isNotEmpty()){
                showResult()
            }

        }
        binding.backButton?.setOnClickListener{
            deleteLastChar()

        }
    }

    private fun addToInputText(buttonValue: String): String {
        return "${ binding.inputText.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        var expression =  binding.inputText.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                binding.resultOutput.text = R.string.error.toString()
                binding.resultOutput.setTextColor(ContextCompat.getColor(this, R.color.blue))
            } else {
                // Show Result
                binding.resultOutput.text = DecimalFormat("0.######").format(result).toString()
                binding.resultOutput.setTextColor(ContextCompat.getColor(this, R.color.blue))
            }
        } catch (e: Exception) {
            // Show Error Message
            binding.resultOutput.text = R.string.error.toString()
            binding.resultOutput.setTextColor(ContextCompat.getColor(this, R.color.blue))
        }

    }
    private fun deleteLastChar(){
        var str: String = binding.inputText.text.toString()
        if (str.length > 1) {
            str = str.substring(0, str.length - 1)
            binding.inputText.text = str
        } else if (str.length <= 1) {
            binding.inputText.text = ""
        }
    }
}