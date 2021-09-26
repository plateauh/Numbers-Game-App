package com.example.numbersgameapp

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

lateinit var guessList: ArrayList<String>
lateinit var userGuess: EditText
lateinit var recyclerView: RecyclerView
lateinit var btn: Button
var numberOfGuesses = 3
val random = Random.nextInt(11)


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        guessList = arrayListOf()

        userGuess = findViewById(R.id.etNumGuessed)
        btn = findViewById(R.id.guessBtn)
        btn.setOnClickListener{
            check()
            updateRecyclerView()
        }
    }

    private fun check(){

        if (userGuess.text.isEmpty()){
            Snackbar.make(cl, "You must enter a number", Snackbar.LENGTH_SHORT).show()
            return
        }

        val guessToInt = userGuess.text.toString().toInt()
        if (random == guessToInt){
            guessList.add("That's right! it is $random")
            customAlert()
        }
        else {
            var plural = "guesses"
            if (--numberOfGuesses == 1) plural = "guess"
            guessList.add("You guessed $guessToInt\nYou have $numberOfGuesses $plural left")
        }
        if (numberOfGuesses == 0) {
            guessList.add("The number was $random")
            customAlert()
        }
        userGuess.setText("")
    }

    private fun updateRecyclerView(){
        recyclerView = findViewById(R.id.rvGuesses)
        recyclerView.adapter = RecyclerViewAdapter(guessList)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun customAlert(){
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)
        // then we set up the input
        val input = EditText(this)
        // here we set the message of our alert dialog
        dialogBuilder.setMessage("Want to play again?")
            // positive button text and action
            .setPositiveButton("Sure!", DialogInterface.OnClickListener {
                    _, _ -> recreate()
                    numberOfGuesses = 3
            })
            // negative button text and action
            .setNegativeButton("Nope", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
                    userGuess.isEnabled = false
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Game ended!")
        // show alert dialog
        alert.show()
    }

}