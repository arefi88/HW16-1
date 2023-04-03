package com.example.maktabhw16_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnAttachStateChangeListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.maktabhw16_1.databinding.FragmentMainTaskBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainTaskFragment : Fragment() {
    private var _binding : FragmentMainTaskBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val args:MainTaskFragmentArgs by navArgs()
    private val taskViewModel:TaskViewModel by activityViewModels()
    private val todoViewModel:TodoViewModel by activityViewModels()
    private val doingViewModel:DoingViewModel by activityViewModels()
    private val doneViewModel:DoneViewModel by activityViewModels()
    private val lableList:ArrayList<String> = arrayListOf("Todo","Doing","Done")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentMainTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var task:Task
        viewPagerAdapter= ViewPagerAdapter(this)
        binding.viewPager.adapter=viewPagerAdapter
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
           tab.text=lableList[position]


        }.attach()
        binding.fabMain.setOnClickListener {
            findNavController().navigate(MainTaskFragmentDirections.actionMainTaskFragmentToTaskDialog2())
        }
        val currentFragment=findNavController().getBackStackEntry(R.id.mainTaskFragment)
        val dialogObserver = LifecycleEventObserver{_,event->
            if (event == Lifecycle.Event.ON_RESUME && currentFragment.savedStateHandle.contains("task")){
                task= currentFragment.savedStateHandle["task"]!!
               when(binding.tabLayout.selectedTabPosition){
                   0->todoViewModel.updateTask(task)
                   1->doingViewModel.updateTask(task)
                   2->doneViewModel.updateTask(task)
               }
            }
        }
        val dialogLifecycle=currentFragment.lifecycle
        dialogLifecycle.addObserver(dialogObserver)
        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver{ _, event->
            if (event== Lifecycle.Event.ON_DESTROY){
                dialogLifecycle.removeObserver(dialogObserver)
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}