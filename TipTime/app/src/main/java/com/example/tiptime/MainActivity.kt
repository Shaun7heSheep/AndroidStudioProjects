package com.example.tiptime

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    //    Declare a top-level variable in the class of binding objects
    //    It's defined at this level because it will be used across multiple methods in MainActivity
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
        binding.manualTip.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
    }

    private fun calculateTip() {
        val stringInTextView = (binding.costOfServiceEditText.text).toString()
//        costOfService is an Editable type, not String
//        Hence, cannot call toDouble() to a non-String type
//        --> Convert to String first, then to Double
        val cost = stringInTextView.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            disPlayTip(0.0)
            return
        }

//        extract the buttonID that was selected
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.twenty_percent -> 0.20
            R.id.fifteen_percent -> 0.15
            R.id.ten_percent -> 0.10
            else -> getManualTip()
        }

        var tip = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        disPlayTip(tip)
    }

    private fun disPlayTip(tip: Double) {
        // Number formatter that will format numbers as currency (i.e. $)
        // NumberFormat.getCurrencyInstance()
        // Chain a call to format() method
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        // Display result on the screen (In the textView particularly)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun getManualTip(): Double {
        val stringManualTip = (binding.manualTip.text).toString()
        var manualTip = stringManualTip.toDoubleOrNull()
        if (manualTip == null) {
            return 0.0
        }
        manualTip /= 100
        return manualTip
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}