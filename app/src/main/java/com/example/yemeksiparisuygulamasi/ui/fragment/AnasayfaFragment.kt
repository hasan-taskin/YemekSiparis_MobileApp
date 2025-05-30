package com.example.yemeksiparisuygulamasi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.yemeksiparisuygulamasi.R
import com.example.yemeksiparisuygulamasi.data.entity.Yemekler
import com.example.yemeksiparisuygulamasi.databinding.FragmentAnasayfaBinding
import com.example.yemeksiparisuygulamasi.ui.adapter.YemeklerAdapter
import com.example.yemeksiparisuygulamasi.ui.viewmodel.AnasayfaViewModel
import com.example.yemeksiparisuygulamasi.ui.viewmodel.YemekSepetViewModel
import com.example.yemeksiparisuygulamasi.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private var _binding: FragmentAnasayfaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnasayfaViewModel by viewModels()
    private lateinit var yemeklerAdapter: YemeklerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAnasayfaBinding.inflate(inflater, container, false)

        setupAdapter()
        setupObservers()
        setupUI()

        return binding.root
    }

    private fun setupAdapter() {

        yemeklerAdapter = YemeklerAdapter(requireContext(), mutableListOf(), viewModel)
        binding.yemekRv.adapter = yemeklerAdapter
        binding.yemekRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun setupObservers() {
        viewModel.yemeklerListesi.observe(viewLifecycleOwner) { yemeklerListesi ->

            yemeklerAdapter.updateData(yemeklerListesi)
            binding.yemekRv.scrollToPosition(0)
        }
    }

    private fun setupUI() {
        binding.fabAnasayfa.setOnClickListener {
            Navigation.gecisYap(it, R.id.yemekSepetGecis)
        }

        binding.toolbarAnasayfa.title = "Yemekler"

        binding.svAnasayfa.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    viewModel.yemekleriYukle()
                } else {
                    viewModel.ara(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.ara(query)
                return true
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.yemekleriYukle()
        binding.yemekRv.scrollToPosition(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

