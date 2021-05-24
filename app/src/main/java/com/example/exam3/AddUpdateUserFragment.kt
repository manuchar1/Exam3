package com.example.exam3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.exam3.databinding.FragmentAddUpdateUserBinding
import kotlinx.android.synthetic.main.fragment_add_update_user.*


class AddUpdateUserFragment : Fragment() {



    private var binding: FragmentAddUpdateUserBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUpdateUserBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //enabledButton()
        binding?.btnAdd?.setOnClickListener {
            //addUser()
        }

    }


}