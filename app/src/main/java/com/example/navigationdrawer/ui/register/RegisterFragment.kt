package com.example.navigationdrawer.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.navigationdrawer.R
import com.example.navigationdrawer.UserViewModel
import com.example.navigationdrawer.UsersListAdapter
import com.example.navigationdrawer.db.User
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var vm: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        vm = ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inf = inflater.inflate(R.layout.fragment_register, container, false)

        vm.getAllUsers().observe(requireActivity(), Observer {
            print(it)

        })

        val usernametxt: EditText = inf.findViewById(R.id.username)
        val nametxt: EditText = inf.findViewById(R.id.name)
        val emailtxt = inf.findViewById<EditText>(R.id.emailAddress)
        val passwordtxt = inf.findViewById<EditText>(R.id.password)
        val phonetxt = inf.findViewById<EditText>(R.id.phone)

        val signup = inf.findViewById<Button>(R.id.signup)
        signup.setOnClickListener {
            val username = usernametxt.text.toString()
            val name = nametxt.text.toString()
            val email = emailtxt.text.toString()
            val password = passwordtxt.text.toString()
            val phone = phonetxt.text.toString()

            if(username.isEmpty()) {
                Toast.makeText(requireActivity(), "Username is required", Toast.LENGTH_LONG).show()
            }
            else if(name.isEmpty()) {
                Toast.makeText(requireActivity(), "Name is required", Toast.LENGTH_LONG).show()
            }
            else if(email.isEmpty()) {
                Toast.makeText(requireActivity(), "Email address is required", Toast.LENGTH_LONG).show()
            }
            else if(password.isEmpty()) {
                Toast.makeText(requireActivity(), "Password is required", Toast.LENGTH_LONG).show()
            }
            else if (phone.isEmpty()) {
                Toast.makeText(requireActivity(), "Phone is required", Toast.LENGTH_LONG).show()
            }
            else {
                vm.insert(User(username, name, email, password, phone))
                requireActivity().onBackPressed()
            }

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
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}