package com.example.exam3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.exam3.databinding.FragmentUserDetailsBinding
import kotlinx.android.synthetic.main.layout_list_item.*


class UserDetailsFragment : Fragment() {

    private var binding: FragmentUserDetailsBinding? = null

    private var inputText: String? = ""



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        getData()
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnBack?.setOnClickListener {
            findNavController().navigate(R.id.action_userDetailsFragment_to_usersListFragment)
        }


    }

    private fun getData() {

        inputText = arguments?.getString("input_txt")
        binding?.tvName?.text = inputText
        binding?.tvLastName?.text = inputText

    }

}