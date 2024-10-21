package com.bookslibrary.ui.fragments.favourites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bookslibrary.databinding.FragmentFavouritesBinding
import com.bookslibrary.ui.adapter.FavoriteBooksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null

    private val binding get() = _binding!!
    private val favouritesViewModel : FavouritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root: View = binding.root
       lifecycleScope.launch {
            favouritesViewModel.getFavBooks()
           favouritesViewModel.bookList.collect { favbooks ->
               val arrayList = ArrayList(favbooks)
               Log.e("favbooks",favbooks.toString())
               _binding?.dataRecylerView?.adapter= FavoriteBooksAdapter(arrayList)
           }



        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}