package com.example.yemeksiparisuygulamasi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisuygulamasi.R
import com.example.yemeksiparisuygulamasi.data.entity.Yemekler
import com.example.yemeksiparisuygulamasi.databinding.CardTasarimBinding
import com.example.yemeksiparisuygulamasi.databinding.FragmentAnasayfaBinding
import com.example.yemeksiparisuygulamasi.ui.fragment.AnasayfaFragmentDirections
import com.example.yemeksiparisuygulamasi.ui.viewmodel.AnasayfaViewModel
import com.example.yemeksiparisuygulamasi.ui.viewmodel.YemekSepetViewModel
import com.example.yemeksiparisuygulamasi.util.gecisYap
import com.google.android.material.snackbar.Snackbar

class YemeklerAdapter(var mContext: Context, private var yemeklerListesi: MutableList<Yemekler>, var viewModel: AnasayfaViewModel)
    : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(var tasarim: CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    fun updateData(newList: List<Yemekler>) {
        yemeklerListesi.clear()
        yemeklerListesi.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi[position]
        val t = holder.tasarim

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(500,750).into(t.ivCardYemek)

        t.tvFiyat.text = "${yemek.yemek_fiyat} ₺"

        t.cardViewYemek.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek = yemek)
            Navigation.gecisYap(it,gecis)
        }

        t.btnSepet.setOnClickListener {
            viewModel.kaydet(
                yemek_adi = yemek.yemek_adi,
                yemek_resim_adi = yemek.yemek_resim_adi,
                yemek_fiyat = yemek.yemek_fiyat.toInt(),
                yemek_siparis_adet = 1,
                kullanici_adi = "hasan-taskin"
            )
            Snackbar.make(it, "${yemek.yemek_adi} sepete eklendi", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }
}
