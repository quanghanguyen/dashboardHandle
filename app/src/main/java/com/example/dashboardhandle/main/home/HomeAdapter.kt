package com.example.dashboardhandle.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dashboardhandle.`interface`.OnItemClickListerner
import com.example.dashboardhandle.databinding.ItemsRequestBinding
import com.example.dashboardhandle.model.RequestModel
import javax.inject.Inject

class HomeAdapter @Inject constructor(private var requestList : ArrayList<RequestModel>)
    : RecyclerView.Adapter<HomeAdapter.MyViewHolder>(){

    private lateinit var listerner: OnItemClickListerner

    fun setOnItemClickListerner(listerner: OnItemClickListerner) {
        this.listerner = listerner
    }

    fun addNewData(list: ArrayList<RequestModel>) {
        requestList = list
        notifyDataSetChanged()
    }

    class MyViewHolder(
        private val itemsRequestBinding : ItemsRequestBinding,
        private val listerner: OnItemClickListerner
    ) : RecyclerView.ViewHolder(itemsRequestBinding.root) {
        fun bind(list : RequestModel) {
            with (itemsRequestBinding) {
                name.text = list.name
                pitch.text = list.pitch
                datetime.text = list.datetime
                note.text = list.note

                items.setOnClickListener {
                    listerner.onItemClick(list)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val items = ItemsRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(items, listerner)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(requestList[position])
    }

    override fun getItemCount(): Int {
        return requestList.size
    }
}