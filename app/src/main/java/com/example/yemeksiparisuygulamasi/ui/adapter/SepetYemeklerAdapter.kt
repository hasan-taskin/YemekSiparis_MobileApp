package com.example.yemeksiparisuygulamasi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparisuygulamasi.data.entity.SepetYemekler
import com.example.yemeksiparisuygulamasi.databinding.FragmentAnasayfaBinding
import com.example.yemeksiparisuygulamasi.databinding.SepetCardTasarimBinding
import com.example.yemeksiparisuygulamasi.ui.viewmodel.YemekSepetViewModel
import com.google.android.material.snackbar.Snackbar

class SepetYemeklerAdapter(var mContext: Context,var sepetYemeklerListesi:List<SepetYemekler>,var viewModel:YemekSepetViewModel)
    : RecyclerView.Adapter<SepetYemeklerAdapter.SepetCardTasarimTutucu>(){

    inner class SepetCardTasarimTutucu(var tasarim:SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardTasarimTutucu {
        val binding = SepetCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return SepetCardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: SepetCardTasarimTutucu, position: Int) {
        val sepetYemek = sepetYemeklerListesi[position]
        val t = holder.tasarim

        t.tvSepetYemekAdi.text = sepetYemek.yemek_adi
        t.tvSepetYemekFiyat.text = "${sepetYemek.yemek_fiyat} ₺"
        t.tvSepetYemekAdet.text = "${sepetYemek.yemek_siparis_adet}"

        val toplamFiyat = sepetYemek.yemek_fiyat * sepetYemek.yemek_siparis_adet
        t.tvSepetYemekToplamFiyat.text = "$toplamFiyat ₺"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(250, 375).into(t.ivSepetResim)

        t.ivSepetSil.setOnClickListener {
            Snackbar.make(it, "${sepetYemek.yemek_adi} silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("EVET") {
                    viewModel.sil(sepetYemek.sepet_yemek_id, "hasan-taskin")
                }
                .show()
        }
    }

    override fun getItemCount(): Int {
        return sepetYemeklerListesi.size
    }

}