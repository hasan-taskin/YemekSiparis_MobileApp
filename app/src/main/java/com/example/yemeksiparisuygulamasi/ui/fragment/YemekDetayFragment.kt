package com.example.yemeksiparisuygulamasi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yemeksiparisuygulamasi.R
import com.example.yemeksiparisuygulamasi.databinding.FragmentYemekDetayBinding
import com.example.yemeksiparisuygulamasi.ui.viewmodel.AnasayfaViewModel
import com.example.yemeksiparisuygulamasi.ui.viewmodel.YemekDetayViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemekDetayFragment : Fragment() {
    private lateinit var binding: FragmentYemekDetayBinding
    private lateinit var viewModel: YemekDetayViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentYemekDetayBinding.inflate(inflater, container, false)

        val bundle:YemekDetayFragmentArgs by navArgs()
        val yemek = bundle.yemek

        binding.toolbarYemekDetay.title = yemek.yemek_adi

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(this).load(url).override(500,750).into(binding.ivYemek)

        binding.tvYemekFiyat.text = "${yemek.yemek_fiyat} ₺"

        binding.tvYemekIsim.text = "${yemek.yemek_adi}"

        binding.tvYemekToplamFiyat.text = "${yemek.yemek_fiyat} ₺"

        binding.btnSepeteEkle.setOnClickListener {
            buttonkaydet(
                yemek_adi = yemek.yemek_adi,
                yemek_resim_adi = yemek.yemek_resim_adi,
                yemek_fiyat = yemek.yemek_fiyat.toInt()
            )
            Snackbar.make(it, "${yemek.yemek_adi} sepete eklendi", Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:YemekDetayViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun buttonkaydet(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int) {
        viewModel.kaydet(
            yemek_adi = yemek_adi,
            yemek_resim_adi = yemek_resim_adi,
            yemek_fiyat = yemek_fiyat
        )
    }

}