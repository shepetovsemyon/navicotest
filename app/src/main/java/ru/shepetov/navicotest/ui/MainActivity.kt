package ru.shepetov.navicotest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.shepetov.navicotest.R
import ru.shepetov.navicotest.data.Attraction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class SharedViewModel: ViewModel() {
    val attractionsList = MutableLiveData<List<Attraction>>()
}


