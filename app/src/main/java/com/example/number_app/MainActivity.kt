package com.example.number_app

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var scoreText: TextView
    private lateinit var errorText: TextView
    private lateinit var guide: TextView
    private lateinit var box1: Button
    private lateinit var box2: Button
    private lateinit var start: Button
    private lateinit var layout: LinearLayout
    private var score = 0
    private var error = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scoreText = findViewById(R.id.score)
        errorText = findViewById(R.id.strikes)
        box1 = findViewById(R.id.box1)
        box2 = findViewById(R.id.box2)
        start = findViewById(R.id.start)
        guide = findViewById(R.id.guide)
        layout = findViewById(R.id.main)
        play()
    }
    private fun play(){
        scoreText.text = "Score: $score"
        errorText.text = "Error: $error"
        start.setOnClickListener {
            start.text = getString(R.string.restart)
            guide.text = getString(R.string.tap_larger)
            start.setOnClickListener {
                restart()
            }
            var result = setRandomNum()
            box1.setOnClickListener {
                result(result,box1)
                updateText()
                if (checkGameEnd()){
                    gameEnd()
                }else {
                    result = setRandomNum()
                }
            }
            box2.setOnClickListener {
                result(result,box2)
                updateText()
                if (checkGameEnd()){
                    gameEnd()
                }else {
                    result = setRandomNum()
                }
            }
        }
    }
    private fun restart(){
        layout.setBackgroundColor(Color.parseColor("#FFFDEF74"))
        scoreText.setTextColor(Color.BLACK)
        errorText.setTextColor(Color.BLACK)
        score = 0
        error = 0
        updateText()
        start.text = getString(R.string.restart)
        guide.text = getString(R.string.tap_larger)
        start.setOnClickListener {
            restart()
        }
        var result = setRandomNum()
        box1.setOnClickListener {
            result(result,box1)
            updateText()
            if (checkGameEnd()){
                gameEnd()
            }else{
                result = setRandomNum()
            }
        }
        box2.setOnClickListener {
            result(result,box2)
            updateText()
            if (checkGameEnd()){
                gameEnd()
            }else {
                result = setRandomNum()
            }
        }
    }
    private fun setRandomNum(): TextView{
        var num1 = Random.nextInt(1,100)
        var num2 = Random.nextInt(1,100)
        if (num1 != num2) {
            box1.text = "$num1"
            box2.text = "$num2"
        }else{
            setRandomNum()
        }
        if (num1 > num2){
            return box1
        }else {
            return box2
        }
    }
    private fun checkGameEnd(): Boolean{
        if (score == 10){
            scoreText.setTextColor(Color.GREEN)
            val win: Toast = Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT)
            win.show()
            return true
        }else if (error == 3){
            errorText.setTextColor(Color.RED)
            val lose: Toast = Toast.makeText(this, "You Lose!", Toast.LENGTH_SHORT)
            lose.show()
            return true
        }else{
            return false
        }
    }
    private fun updateText(){
        scoreText.text = "Score: $score"
        errorText.text = "Error: $error"
    }
    private fun gameEnd(){
        layout.setBackgroundColor(Color.YELLOW)
        box1.text = ""
        box2.text = ""
        guide.text = getString(R.string.tap_restart)
        box1.setOnClickListener{

        }
        box2.setOnClickListener{

        }
    }
    private fun result(answer: TextView, box: TextView){
        if (answer == box) {
            score++
            layout.setBackgroundColor(Color.GREEN)
            scoreText.setTextColor(Color.YELLOW)
            errorText.setTextColor(Color.BLACK)
        } else {
            error++
            layout.setBackgroundColor(Color.RED)
            scoreText.setTextColor(Color.BLACK)
            errorText.setTextColor(Color.YELLOW)
        }
    }
}