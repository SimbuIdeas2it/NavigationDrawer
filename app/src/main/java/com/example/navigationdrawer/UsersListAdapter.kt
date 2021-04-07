package com.example.navigationdrawer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.db.User

internal class UsersListAdapter(private var user: List<User>): RecyclerView.Adapter<UsersListAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var email: TextView = view.findViewById(R.id.email)
        var mobile: TextView = view.findViewById(R.id.mobile)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = user[position]
        holder.name.text = user.name
        holder.email.text = user.email
        holder.mobile.text = user.mobile
    }

    override fun getItemCount(): Int {
        return user.count()
    }


}