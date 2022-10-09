package com.example.retrofit_safe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.retrofit_safe.Network.ApiService
import com.example.retrofit_safe.Repository.AppRepository
import com.example.retrofit_safe.Util.Status
import com.example.retrofit_safe.ViewModel.AppFactory
import com.example.retrofit_safe.ViewModel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel:AppViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_home, container, false)

        val token=arguments?.getString("token")
        val token_type=arguments?.getString("token_type")

        viewModel.getUser("$token_type $token")
        viewModel.liveUser.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    view.relative.visibility=View.VISIBLE
                    val user=it.data!!
                    view.username.text=user.username
                    view.fullname.text=user.full_name
                    view.email.text=user.email
                }
                Status.FAILURE->{
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
                }
                Status.LOADING->{
                    view.relative.visibility=View.GONE
                }
            }
        })

        return view
    }

}