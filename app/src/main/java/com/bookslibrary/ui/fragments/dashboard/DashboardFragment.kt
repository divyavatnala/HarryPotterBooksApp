package com.bookslibrary.ui.fragments.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bookslibrary.data.model.BookItem
import com.bookslibrary.databinding.FragmentDashboardBinding
import com.bookslibrary.ui.adapter.BooksRecyclerViewAdapter
import com.bookslibrary.ui.listeners.OnItemClickListener
import com.bookslibrary.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        lifecycleScope.launch {
            dashboardViewModel.getNetworkBooks()
            dashboardViewModel.dataState.collect { resource ->
                when (resource) {
                    is NetworkResult.Loading -> {
                        showLoading(true)
                        _binding?.dataRecylerView?.visibility = View.GONE
                        _binding?.noContentLayout?.visibility = View.GONE
                        Log.e("data : ", "Divya Loading ")
                    }

                    is NetworkResult.Success -> {
                        showLoading(false)

                        Log.e("data : ", "Divya " + resource.data?.size)

                        _binding?.dataRecylerView?.visibility = View.VISIBLE
                        _binding?.noContentLayout?.visibility = View.GONE
                        _binding?.dataRecylerView?.adapter= BooksRecyclerViewAdapter(resource.data as ArrayList,itemClickListener)
                        dashboardViewModel.getAllFavItemsInitial()
                    }

                    is NetworkResult.Error -> {
                        showLoading(false)
                        Log.e("data : ", "Divya " + resource.message)
                        _binding?.noContentLayout?.visibility = View.VISIBLE
                        _binding?.dataRecylerView?.visibility = View.GONE
                        _binding?.noContentTV?.text = resource.message
//                        dashboardViewModel.getAllBooksfromDatabase()
                    }
                }
            }
        }

        return root
    }

    fun showLoading(show:Boolean) = if(show)
        _binding?.progressBar?.visibility = View.VISIBLE
    else
        _binding?.progressBar?.visibility = View.GONE
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
   var itemClickListener= object :OnItemClickListener {

       override fun onItemClick(position: Int, item: BookItem) {
                Toast.makeText(activity, "desc : "+item.description, Toast.LENGTH_SHORT).show()
       }

       override fun onFavClick(position: Int, item: BookItem) {
           Toast.makeText(activity, "Added to favorites", Toast.LENGTH_SHORT).show()
           dashboardViewModel.insertFavItem(item )
       }
   }
   }