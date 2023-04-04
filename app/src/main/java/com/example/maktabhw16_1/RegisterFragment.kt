package com.example.maktabhw16_1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.maktabhw16_1.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            val sharedPreference= activity?.getSharedPreferences(MY_PRE, Context.MODE_PRIVATE)
            val editor= sharedPreference?.edit()
            editor?.putString("USERNAME",binding.edtUsernameRegister.text.toString())
            editor?.putString("EMAIL",binding.edtEmailRegister.text.toString())
            editor?.putString("PASSWORD",binding.edtPasswordRegister.text.toString())
            editor?.apply()
            view.findNavController().navigate(R.id.action_registerFragment_to_mainTaskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}