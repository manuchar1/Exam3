package com.example.exam3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exam3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),Communicator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun passData(textView1: String) {
        val bundle = Bundle()
        bundle.putString("input_txt", textView1)

        val frag2 = UserDetailsFragment()
        frag2.arguments = bundle

    }
}