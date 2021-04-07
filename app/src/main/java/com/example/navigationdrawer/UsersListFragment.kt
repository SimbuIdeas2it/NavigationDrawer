package com.example.navigationdrawer

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.db.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UsersListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class UsersListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var users = mutableListOf<User>()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var usersListAdapter: UsersListAdapter

    private lateinit var vm: UserViewModel
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        vm = ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val inf = inflater.inflate(R.layout.fragment_users_list, container, false)
        usersListAdapter = UsersListAdapter(users)

        recyclerView = inf.findViewById<RecyclerView>(R.id.recycleView)
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = usersListAdapter
        getUsersDatas()

        val btn = inf.findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            vm.insert(User("Dummy username", "Dummy name", "Dummy email", "12345", "23456789"))
        }
        return inf
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UsersListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                UsersListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    fun getUsersDatas() {
        vm.getAllUsers().observe(requireActivity(), Observer {


            val sharedPreference =  requireActivity().getSharedPreferences("demo", Context.MODE_PRIVATE)
            val username = sharedPreference.getString("username", "")

            val its = it.filter { s -> s.username != username }
            users = its as MutableList<User>
            usersListAdapter = UsersListAdapter(users)
            recyclerView.adapter = usersListAdapter
            usersListAdapter.notifyDataSetChanged()

        })

    }
}