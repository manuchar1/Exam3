package com.example.exam3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exam3.databinding.LayoutListItemBinding


class RecyclerAdapter(
    private val countries: List<Users>,
    private var listener: OnItemModelClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == NORMAL_USER) {
            UserProfileViewHolder(
                LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            UserWithoutPhotoViewHolder(
                LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserWithoutPhotoViewHolder -> holder.bind()
            is UserProfileViewHolder -> holder.bind()

        }
    }

    override fun getItemCount() = countries.size


    inner class UserWithoutPhotoViewHolder(private val binding: LayoutListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {
        private lateinit var users: Users
        fun bind() {
            users = this@RecyclerAdapter.countries[adapterPosition]
            binding.tvName.text = users.name
            binding.tvSurname.text = users.name
            binding.tvEmail.text = users.email
        }

        init {
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
            return true

        }

    }

    inner class UserProfileViewHolder(private val binding: LayoutListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {
        private lateinit var users: Users
        fun bind() {
            users = this@RecyclerAdapter.countries[adapterPosition]
            binding.tvName.text = users.name
            binding.tvSurname.text = users.lastName
            binding.tvEmail.text = users.email
        }

        init {
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
            return true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val model = countries[position]
        return if (model.email == null)
            USER_WITH_NO_PHOTO
        else
            NORMAL_USER
    }

    companion object {
        const val NORMAL_USER = 1
        const val USER_WITH_NO_PHOTO = 2
    }

    interface OnItemModelClickListener {
        fun onItemClick(position: Int)
    }
}

