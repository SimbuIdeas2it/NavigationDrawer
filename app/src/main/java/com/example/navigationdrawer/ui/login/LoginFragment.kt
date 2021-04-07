package com.example.navigationdrawer.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import androidx.navigation.fragment.findNavController
import com.example.navigationdrawer.*
import com.example.navigationdrawer.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {
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
        val inf = inflater.inflate(R.layout.fragment_login, container, false)

        val binding = FragmentLoginBinding.inflate(inflater, container, false)
//        val usernametxt = inf.findViewById<EditText>(R.id.username)
//        val passwordtxt = inf.findViewById<EditText>(R.id.password)
//        val btn = inf.findViewById<Button>(R.id.signin)
//        val signupbtn = inf.findViewById<Button>(R.id.signup)
        val username = binding.username.text.toString()
//        binding.signin.setOnClickListener {
////        btn.setOnClickListener {
//            val username = binding.username.text.toString()
//            val password = binding.password.text.toString()
//
//            if(username.isEmpty()) {
//                Toast.makeText(requireActivity(), "Username is required", Toast.LENGTH_LONG).show()
//            }
//            else if(password.isEmpty()) {
//                Toast.makeText(requireActivity(), "Password is required", Toast.LENGTH_LONG).show()
//            }
//            else {
//                vm.checkCredentail(binding.username.text.toString(), binding.password.text.toString()).observe(requireActivity(),
//                Observer {
//                    print(it.count())
//                    if(it.count() > 0) {
//                        Toast.makeText(requireActivity(), "Success", Toast.LENGTH_LONG).show()
////                        activity?.finish()
//                        val sharedPreference =  requireActivity().getSharedPreferences("demo",Context.MODE_PRIVATE)
//                        var editor = sharedPreference.edit()
//                        editor.putString("username",username)
//                        editor.commit()
//
//                        activity?.let{
//                            val intent = Intent (it, MainActivity::class.java)
//                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
/////                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
//                            it.startActivity(intent)
//                            requireActivity().finish()
//                        }
//
//                    }
//                    else {
//                        Toast.makeText(requireActivity(), "Invalid user name and password", Toast.LENGTH_LONG).show()
//                    }
//                })
//            }
//        }
//
//        binding.signup.setOnClickListener {
//            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
//        }
        return inf
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var binding = FragmentLoginBinding.bind(view)
        binding.signin.setOnClickListener {
            Toast.makeText(requireContext(),"Sign in clicked",Toast.LENGTH_LONG).show()

            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if(username.isEmpty()) {
                Toast.makeText(requireActivity(), "Username is required", Toast.LENGTH_LONG).show()
            }
            else if(password.isEmpty()) {
                Toast.makeText(requireActivity(), "Password is required", Toast.LENGTH_LONG).show()
            }
            else {
//                val apiService = RestApiService()
//                val userI = LoginRequestInfo(username, password, "")
//                apiService.login(userI) {
//                    if(it?.status == "1") {
//                        print(it)
//                        Toast.makeText(requireActivity(), it?.message, Toast.LENGTH_LONG).show()
//                    }
//                    else {
//                        Toast.makeText(requireActivity(), "Failure", Toast.LENGTH_LONG).show()
//                    }
//                }

                vm.checkCredentail(binding.username.text.toString(), binding.password.text.toString()).observe(requireActivity(),
                Observer {
                    print(it.count())
                    if(it.count() > 0) {
                        Toast.makeText(requireActivity(), "Success", Toast.LENGTH_LONG).show()
//                        activity?.finish()
                        val sharedPreference =  requireActivity().getSharedPreferences("demo",Context.MODE_PRIVATE)
                        var editor = sharedPreference.edit()
                        editor.putString("username",username)
                        editor.commit()

                        activity?.let{
                            val intent = Intent (it, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
///                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                            it.startActivity(intent)
                            requireActivity().finish()
                        }

                    }
                    else {
                        Toast.makeText(requireActivity(), "Invalid user name and password", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        binding.signup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}