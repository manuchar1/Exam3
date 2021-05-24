package com.example.exam3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exam3.databinding.FragmentUsersListBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_item.*
import kotlinx.android.synthetic.main.fragment_add_update_user.*
import kotlinx.android.synthetic.main.fragment_users_list.*
import kotlinx.android.synthetic.main.layout_list_item.*


class UsersListFragment : Fragment(), RecyclerAdapter.OnItemModelClickListener {

    private var users = mutableListOf<Users>()
    private lateinit var adapter: RecyclerAdapter
    private lateinit var communicator: Communicator

    private var binding: FragmentUsersListBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUsersListBinding.inflate(inflater,container,false)
        return binding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communicator = activity as Communicator

        btnAddUpdate.setOnClickListener {

            addInfo()
        }
        init()

    }


    private fun init() {
        setData()
        adapter = RecyclerAdapter(requireContext(),users, this@UsersListFragment)
        recycler_view.layoutManager = GridLayoutManager(requireActivity(), 2)
        recycler_view.adapter = adapter
    }


    override fun onItemLongClick(position: Int) {
        android.app.AlertDialog.Builder(context)
            .setTitle("Delete")
            .setIcon(R.drawable.ic_warning)
            .setMessage("Are you sure you want to delete this User?")
            .setPositiveButton("Yes"){
                    dialog,_->
                users.removeAt(position)
                adapter.notifyItemRemoved(position)
                Toast.makeText(context,"Deleted!",Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("No"){
                    dialog,_->
                dialog.dismiss()
            }
            .create()
            .show()

    }

    override fun onItemClick(item: Users, position: Int) {
        findNavController().navigate(R.id.action_usersListFragment_to_userDetailsFragment)
        //communicator.passData(tvName.text.toString())
        communicator = requireActivity() as Communicator

        communicator.passData(item.name)
        //communicator.passData(item.lastName)
    }



    private fun addInfo() {
        val inflter = LayoutInflater.from(requireActivity())
        val v = inflter.inflate(R.layout.add_item,null)


        val userName1 = v.findViewById<EditText>(R.id.userName)
        val email1 = v.findViewById<EditText>(R.id.userNo)
        val userNo = v.findViewById<EditText>(R.id.editLastName)

        val addDialog = AlertDialog.Builder(requireActivity())

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            val name = userName1.text.toString()
            val email = email1.text.toString()
            val lastName = userNo.text.toString()

            val user = Users(name,lastName,email)
            users.add(user)


            adapter.notifyItemInserted(users.size - 1)
            Toast.makeText(requireContext(),"New User has been created",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(requireContext(),"Cancel",Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()
    }


    private fun setData() {

        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("LOrem", "Dolor","Lorem@gmail.com"))
        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("Lorem", "Dolor","Lorem@gmail.com"))
        users.add(Users("LOrem", "Dolor","Lorem@gmail.com"))

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }



}