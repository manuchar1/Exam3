package com.example.exam3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemModelClickListener {

    private var users = mutableListOf<Users>()

    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnAdd.setOnClickListener {
            addUser()
        }
        enabledButton()
        init()
    }


    private fun init() {
        setData()
        adapter = RecyclerAdapter(users, this)
        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.adapter = adapter
    }


    override fun onItemClick(position: Int) {
        users.removeAt(position)
        adapter.notifyItemRemoved(position)
        Toast.makeText(this, "User Removed!", Toast.LENGTH_SHORT).show()

    }

    private fun addUser() {

        val name = etName.text.toString()
        val username = etSurname.text.toString()
        val email = etEmail.text.toString()
        val user = Users(name,username,email)
        users.add(user)
        adapter.notifyItemInserted(users.size - 1)
        Toast.makeText(this, "New User Added!", Toast.LENGTH_SHORT).show()
    }

    private fun enabledButton() {

        etName.doOnTextChanged { _, _, _, _ ->

            btnAdd.isEnabled = etName.text.toString().trim { it <= ' ' }.isNotEmpty()
        }

    }


    private fun setData() {

        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))


    }
}