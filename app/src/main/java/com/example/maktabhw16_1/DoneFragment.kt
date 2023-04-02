package com.example.maktabhw16_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.maktabhw16_1.databinding.FragmentDoneBinding

class DoneFragment : Fragment() {
    private var _binding:FragmentDoneBinding?=null
    private val binding get() = _binding!!
    private val args:DoneFragmentArgs by navArgs()
    private val doneViewModel:DoneViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentDoneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var task:Task
        super.onViewCreated(view, savedInstanceState)
        doneViewModel.taskLiveData.observe(viewLifecycleOwner){task->
            binding.textView.text=task.title.toString()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}