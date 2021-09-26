package com.example.numbersgameapp

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
            userGuess.isEnabled = false
        }
        else {
            var plural = "guesses"
            if (--numberOfGuesses == 1) plural = "guess"
            guessList.add("You guessed $guessToInt\nYou have $numberOfGuesses $plural left")
        }
        if (numberOfGuesses == 0) {
            guessList.add("The number was $random")
            userGuess.isEnabled = false
        }
        userGuess.setText("")
    }

    private fun updateRecyclerView(){
        recyclerView = findViewById(R.id.rvGuesses)
        recyclerView.adapter = RecyclerViewAdapter(guessList)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}