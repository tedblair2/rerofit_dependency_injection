package com.example.retrofit_safe

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.retrofit_safe.Network.ApiService
import com.example.retrofit_safe.Repository.AppRepository
import com.example.retrofit_safe.Util.Status
import com.example.retrofit_safe.ViewModel.AppFactory
import com.example.retrofit_safe.ViewModel.AppViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_login.view.username
import org.w3c.dom.Text

@AndroidEntryPoint
class LoginFragment : Fragment() {
    //lateinit var viewModel: AppViewModel

    private val viewModel:AppViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_login, container, false)


        view.btn_login.setOnClickListener {click->
            val username=view.username.editText?.text.toString()
            val password=view.password.editText?.text.toString()

            if (TextUtils.isEmpty(username)){
                view.username.error="Field cannot be empty"
            }else if (TextUtils.isEmpty(password)){
                view.password.error="Field cannot be empty"
            }else{
                view.progress_bar.visibility=View.VISIBLE
                viewModel.login(username,password)
                viewModel.liveResponse.observe(viewLifecycleOwner, Observer {
                    when(it.status){
                        Status.SUCCESS->{
                            view.progress_bar.visibility=View.GONE
                            val bundle=Bundle()
                            bundle.putString("token",it.data!!.access_token)
                            bundle.putString("token_type",it.data.token_type)
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment,bundle)
                            Toast.makeText(requireContext(),"Login successful",Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING->{
                            view.progress_bar.visibility=View.VISIBLE
                        }
                        Status.FAILURE->{
                            view.progress_bar.visibility=View.GONE
                            Snackbar.make(view.parentLayout, it.message.toString(),Snackbar.LENGTH_LONG).show()
                        }
                    }
                })
            }
        }
        return view
    }
}