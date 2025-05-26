package com.example.yemeksiparisuygulamasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeksiparisuygulamasi.R
import com.example.yemeksiparisuygulamasi.data.entity.SepetYemekler
import com.example.yemeksiparisuygulamasi.data.entity.Yemekler
import com.example.yemeksiparisuygulamasi.databinding.FragmentYemekSepetBinding
import com.example.yemeksiparisuygulamasi.ui.adapter.SepetYemeklerAdapter
import com.example.yemeksiparisuygulamasi.ui.adapter.YemeklerAdapter
import com.example.yemeksiparisuygulamasi.ui.viewmodel.YemekDetayViewModel
import com.example.yemeksiparisuygulamasi.ui.viewmodel.YemekSepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemekSepetFragment : Fragment() {
    private lateinit var binding: FragmentYemekSepetBinding
    private lateinit var viewModel: YemekSepetViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentYemekSepetBinding.inflate(inflater, container, false)

        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner){//sepetyemek verilerini cekiyoruz
            val sepetYemeklerAdapter = SepetYemeklerAdapter(requireContext(),it,viewModel)
            binding.sepetRv.adapter = sepetYemeklerAdapter//adaptoru aktariyoruz
        }

        binding.toolbarYemekSepet.title = "Sepetim"//toolbar yazisi

        binding.sepetRv.layoutManager = LinearLayoutManager(requireContext())//siralama rv erisim

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: YemekSepetViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetYemekleriYukle()
    }


}