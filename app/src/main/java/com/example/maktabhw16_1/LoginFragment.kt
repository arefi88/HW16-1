package com.example.maktabhw16_1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.maktabhw16_1.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding:FragmentLoginBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences= activity?.getSharedPreferences(MY_PRE,Context.MODE_PRIVATE)

        binding.txtRegister.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnLogin.setOnClickListener {
            val userName= sharedPreferences?.getString("USERNAME","").toString()
            val password= sharedPreferences?.getString("PASSWORD","").toString()
            if (binding.edtUsername.text.isNotEmpty() && binding.edtPassword.text.isNotEmpty()){
                if (userName==binding.edtUsername.text.toString()
                    && password==binding.edtPassword.text.toString()){
                    view.findNavController().navigate(R.id.action_loginFragment_to_mainTaskFragment)
                }else{
                    Toast.makeText(activity,"no match",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(activity,"username and password not empty!",Toast.LENGTH_SHORT).show()
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}