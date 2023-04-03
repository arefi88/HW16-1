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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabhw16_1.databinding.FragmentDoneBinding

class DoneFragment : Fragment() {
    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!
    private val doneViewModel: DoneViewModel by activityViewModels()
    private lateinit var taskAdapter: TaskAdapter
    private val doneList = mutableListOf<Task>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskAdapter = TaskAdapter()
        doneViewModel.taskLiveData.observe(viewLifecycleOwner) { task ->
            doneList.add(task)
            taskAdapter.differ.submitList(doneList.toList())
        }

        binding.rvDone.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = taskAdapter
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}