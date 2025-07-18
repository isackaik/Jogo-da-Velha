package com.isackaik.jogodavelha2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var isPlayer1 = true
    private var gameEnd = false
    private lateinit var top: ImageView
    private lateinit var topStart: ImageView
    private lateinit var topEnd: ImageView

    private lateinit var center: ImageView
    private lateinit var centerStart: ImageView
    private lateinit var centerEnd: ImageView

    private lateinit var bottom: ImageView
    private lateinit var bottomStart: ImageView
    private lateinit var bottomEnd: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        top = findViewById(R.id.top)
        topStart = findViewById(R.id.topStart)
        topEnd = findViewById(R.id.topEnd)

        center = findViewById(R.id.center)
        centerStart = findViewById(R.id.centerStart)
        centerEnd = findViewById(R.id.centerEnd)

        bottom = findViewById(R.id.bottom)
        bottomStart = findViewById(R.id.bottomStart)
        bottomEnd = findViewById(R.id.bottomEnd)

        configureBox(top)
        configureBox(topStart)
        configureBox(topEnd)
        configureBox(center)
        configureBox(centerStart)
        configureBox(centerEnd)
        configureBox(bottom)
        configureBox(bottomStart)
        configureBox(bottomEnd)

        val reset: Button = findViewById(R.id.buttonReset)
        reset.setOnClickListener {
            gameEnd = false
            isPlayer1 = true
            resetBox(top)
            resetBox(topStart)
            resetBox(topEnd)
            resetBox(center)
            resetBox(centerStart)
            resetBox(centerEnd)
            resetBox(bottom)
            resetBox(bottomStart)
            resetBox(bottomEnd)
        }
    }

    private fun resetBox(box: ImageView) {
        box.setImageDrawable(null)
        box.tag = null
    }

    private fun configureBox(box: ImageView) {
        box.setOnClickListener {
            if(box.tag != null || gameEnd){
                return@setOnClickListener
            }
            if(isPlayer1){
                box.setImageResource(R.drawable.baseline_close_24)
                box.tag = 1
            } else {
                box.setImageResource(R.drawable.outline_brightness_1_24)
                box.tag = 2
            }
            val actualPlayer: Int = if (isPlayer1) 1 else 2
            if(playerWin(actualPlayer)){
                Toast.makeText(this@MainActivity,
                    "Player $actualPlayer ganhou!",
                    Toast.LENGTH_SHORT).show()
                gameEnd = true
                return@setOnClickListener
            }
            isPlayer1 = !isPlayer1
        }
    }

    private fun playerWin(tagPlayer: Int): Boolean {
        return (top.tag == tagPlayer && center.tag == tagPlayer && bottom.tag == tagPlayer) ||
            (topStart.tag == tagPlayer && centerStart.tag == tagPlayer && bottomStart.tag == tagPlayer) ||
            (topEnd.tag == tagPlayer && centerEnd.tag == tagPlayer && bottomEnd.tag == tagPlayer) ||
            (topStart.tag == tagPlayer && top.tag == tagPlayer && topEnd.tag == tagPlayer) ||
            (centerStart.tag == tagPlayer && center.tag == tagPlayer && centerEnd.tag == tagPlayer) ||
            (bottomStart.tag == tagPlayer && bottom.tag == tagPlayer && bottomEnd.tag == tagPlayer) ||
            (topStart.tag == tagPlayer && center.tag == tagPlayer && bottomEnd.tag == tagPlayer) ||
            (bottomStart.tag == tagPlayer && center.tag == tagPlayer && topEnd.tag == tagPlayer)
    }

}