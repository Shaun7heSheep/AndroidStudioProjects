package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        // OBJECT.setOnClickListener performs some actions
        // when user interacts with the OBJECT
        rollButton.setOnClickListener {
            rollDice()
        }
    }

    /* This function rolls the dice randomly and display the result on screen */
    private fun rollDice() {
        val dice = Dice(6) // Create a dice of 6 sides
        val diceRoll = dice.roll()

        // Find ImageView in the layout
        val resultImageView: ImageView = findViewById(R.id.imageView)

        // Change image based on the result of diceRoll
        val drawableResource = when (diceRoll){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        // Update the ImageView to the drawable resource ID
        resultImageView.setImageResource(drawableResource)
        // Update content description
        resultImageView.contentDescription = diceRoll.toString()

        /* Toast is a brief message appears to user at the bottom of the screen
             * val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT)
             * tell a Toast to display itself my calling show() method
             * toast.show()
             * combining 2 lines of codes, we get */
        Toast.makeText(this, "Dice Rolled ${diceRoll}", Toast.LENGTH_SHORT).show()
    }
}

/* This class create a dice with a number of sides.
   Including in this class is the roll() method,
   which generates a random number in range (1 -> numSides)
 */
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}