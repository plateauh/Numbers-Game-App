package com.example.numbersgameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

lateinit var guessList: ArrayList<String>
lateinit var guessBtn: Button
lateinit var userGuess: EditText
lateinit var recyclerView: RecyclerView
var numberOfGuesses = 3

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        guessList = arrayListOf()
        val random = Random.nextInt(11)
        userGuess = findViewById(R.id.etNumGuessed)
        guessBtn = findViewById(R.id.guessBtn)
        guessBtn.setOnClickListener{
            check(random)
            userGuess.setText("")
            updateRecyclerView()
        }
    }

    private fun check(random: Int){
        val guessToInt = userGuess.text.toString().toInt()
        if (random == guessToInt){
            guessList.add("That's right! it is $random")
        }
        else {
            guessList.add("You guessed $guessToInt\nYou have ${--numberOfGuesses} guesses left")
        }
        if (numberOfGuesses == 0)
            guessList.add("The number was $random")
    }

    private fun updateRecyclerView(){
        recyclerView = findViewById(R.id.rvGuesses)
        recyclerView.adapter = RecyclerViewAdapter(guessList)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}