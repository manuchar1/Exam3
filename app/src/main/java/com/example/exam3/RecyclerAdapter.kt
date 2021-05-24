package com.example.exam3

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.exam3.databinding.LayoutListItemBinding


class RecyclerAdapter(
    val context: Context,
    private val userList: List<Users>,
    private var listener: OnItemModelClickListener
) : RecyclerView.Adapter<RecyclerAdapter.UserProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfileViewHolder {


        val binding: LayoutListItemBinding =
            LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserProfileViewHolder, position: Int) {
        holder.bind()


    }

    override fun getItemCount() = userList.size



    inner class UserProfileViewHolder(private val binding: LayoutListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener, View.OnClickListener {
        private lateinit var users: Users


        var mMenus:ImageView = binding.edit

        init {
            mMenus.setOnClickListener { editUser() }
        }


        fun bind() {
            users = this@RecyclerAdapter.userList[adapterPosition]
            binding.tvName.text = users.name
            binding.tvSurname.text = users.lastName
            binding.tvEmail.text = users.email
        }

        private fun editUser() {

            val position = userList[adapterPosition]

            val v = LayoutInflater.from(context).inflate(R.layout.add_item, null)

            val name = v.findViewById<EditText>(R.id.userName)
            val number = v.findViewById<EditText>(R.id.userNo)
            AlertDialog.Builder(context)
                .setView(v)
                .setPositiveButton("Ok") { dialog, _ ->
                    position.name = name.text.toString()
                    position.lastName = number.text.toString()
                    position.email = number.text.toString()
                    notifyDataSetChanged()
                    Toast.makeText(context, "User Information is Edited", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()

                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()

                }
                .create()
                .show()

        }

        init {
            itemView.setOnLongClickListener(this)
            itemView.setOnClickListener(this)
            mMenus.setOnClickListener { editUser() }

        }

        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemLongClick(position)
            }
            return true
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(item = users, position)
            }

        }
    }




    interface OnItemModelClickListener {
        fun onItemLongClick(position: Int)
        fun onItemClick(item: Users, position: Int)
    }
}

