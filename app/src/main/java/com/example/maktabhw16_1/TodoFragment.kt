package com.example.maktabhw16_1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabhw16_1.databinding.FragmentTodoBinding
const val TAG="TodoFragment"
class TodoFragment : Fragment() {
    private var _binding:FragmentTodoBinding?=null
    private val binding get() = _binding!!
    private val todoViewModel:TodoViewModel by activityViewModels()
    private lateinit var taskAdapter: TaskAdapter
    private val todoList= mutableListOf<Task>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentTodoBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskAdapter= TaskAdapter()
        todoViewModel.taskLiveData.observe(viewLifecycleOwner){task->

            todoList.add(task)
            taskAdapter.differ.submitList(todoList.toList())
        }

        binding.rvTodo.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=taskAdapter
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}