package com.example.maktabhw16_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    var position=-1
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
        taskAdapter = TaskAdapter(::onItemClicked)
        doneViewModel.taskLiveData.observe(viewLifecycleOwner){task->

            if (position!=-1){
                doneList[position] = task
            }else{
                doneList.add(task)
            }
            Toast.makeText(activity,"$position", Toast.LENGTH_SHORT).show()


            taskAdapter.differ.submitList(doneList.toList())

        }
        doneViewModel.positionLiveData.observe(viewLifecycleOwner){pos->
            position=pos
        }

        binding.rvDone.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = taskAdapter
        }


    }
    private fun onItemClicked(task: Task){
        val bundle=Bundle()
        position=doneList.indexOf(task)
        bundle.putString("title",task.title)
        bundle.putString("description",task.description)
        bundle.putString("date",task.date)
        bundle.putString("time",task.time)
        bundle.putInt("position",position)
        findNavController().navigate(R.id.action_mainTaskFragment_to_todoTaskDialog,bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}