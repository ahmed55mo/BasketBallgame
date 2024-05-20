package com.example.basketball

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.basketball.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(androidx.appcompat.R.id.action_bar_title))
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewmodel = ViewModelProvider(this).get(ScoreViewModel::class.java)

        viewmodel.newscore1.observe(this){
            binding.scoreTeamA.text = it.toString()
        }

        viewmodel.newscore2.observe(this){
            binding.scoreTeamB.text = it.toString()
        }

        binding.teamAb3.setOnClickListener{
            viewmodel.increment(viewmodel.score1 , 3)
        }

        binding.teamAb2.setOnClickListener{
            viewmodel.increment(viewmodel.score1 , 2)
        }

        binding.teamAb1.setOnClickListener{
            viewmodel.increment(viewmodel.score1 , 1)
        }

        binding.teamBb3.setOnClickListener{
            viewmodel.increment(viewmodel.score2 , 3)
        }

        binding.teamBb2.setOnClickListener{
            viewmodel.increment(viewmodel.score2 , 2)
        }

        binding.teamBb1.setOnClickListener{
            viewmodel.increment(viewmodel.score2 , 1)
        }

        var winner = "draw"


        binding.endButton.setOnClickListener {
            if (binding.scoreTeamA.text.toString().toInt() > binding.scoreTeamB.text.toString().toInt()){
                winner = "TeamA"
            }else if(binding.scoreTeamA.text.toString().toInt() < binding.scoreTeamB.text.toString().toInt()){
                winner = "TeamB"
            }
            Toast.makeText(this, "${winner} has won the game", Toast.LENGTH_SHORT).show()
        }

        binding.resetbtn.setOnClickListener {
            viewmodel.score1.value = 0
            viewmodel.score2.value = 0
        }

    }
}