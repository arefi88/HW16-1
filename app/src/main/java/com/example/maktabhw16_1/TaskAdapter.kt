package com.example.maktabhw16_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabhw16_1.databinding.ItemRowBinding

class TaskAdapter(private val onItemClicked:(Task)->Unit) :RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private lateinit var binding:ItemRowBinding
    inner class ViewHolder:RecyclerView.ViewHolder(binding.root){
        fun setData(task: Task){
            binding.txtChar.text=task.title.substring(0,1)
            binding.txtTitle.text=task.title
            binding.txtDescription.text=task.description
            binding.root.setOnClickListener {
                onItemClicked(task)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding=ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size


    private val differCallback= object : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
           return oldItem.title==newItem.title
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,differCallback)
}