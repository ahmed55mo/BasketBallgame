package com.example.basketball

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    var score1 = MutableLiveData(0)
    var score2 = MutableLiveData(0)

    val newscore1 : LiveData<Int>
        get() = score1

    val newscore2: LiveData<Int>
        get() = score2

    fun increment(score : MutableLiveData<Int>, addition : Int){
        score.value = score.value?.plus(addition)
    }
}