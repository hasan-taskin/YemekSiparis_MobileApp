package com.example.yemeksiparisuygulamasi.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYemekSepetBinding.inflate(inflater, container, false)

        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner) { sepetListesi ->
            val sepetYemeklerAdapter = SepetYemeklerAdapter(requireContext(), sepetListesi, viewModel)
            binding.sepetRv.adapter = sepetYemeklerAdapter


            val toplamTutar = sepetListesi.sumOf { it.yemek_fiyat * it.yemek_siparis_adet }
            binding.tvSepetToplamTutar.text = "Toplam: ₺$toplamTutar"
        }

        binding.btnSepetiOnayla.setOnClickListener {

            binding.lottieSuccess.visibility = View.VISIBLE
            binding.lottieSuccess.playAnimation()

            Handler(Looper.getMainLooper()).postDelayed({
                binding.lottieSuccess.visibility = View.GONE
                Toast.makeText(requireContext(), "Siparişiniz başarıyla onaylandı!", Toast.LENGTH_SHORT).show()
            }, 3000)
        }

        binding.toolbarYemekSepet.title = "Sepetim"
        binding.sepetRv.layoutManager = LinearLayoutManager(requireContext())

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