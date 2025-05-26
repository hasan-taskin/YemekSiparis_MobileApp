package com.example.yemeksiparisuygulamasi.data.repo

import com.example.yemeksiparisuygulamasi.data.datasource.SepetYemeklerDataSource
import com.example.yemeksiparisuygulamasi.data.datasource.YemeklerDataSource
import com.example.yemeksiparisuygulamasi.data.entity.SepetYemekler
import com.example.yemeksiparisuygulamasi.data.entity.Yemekler

class YemeklerRepository(var yds:YemeklerDataSource,var syds: SepetYemeklerDataSource) {

    suspend fun kaydet(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String) = yds.kaydet(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)

    suspend fun sil(sepet_yemek_id: Int,kullanici_adi: String) =syds.sil(sepet_yemek_id,kullanici_adi)

    suspend fun yemekleriYukle() : List<Yemekler> = yds.yemekleriYukle()

    suspend fun sepetYemekleriYukle() : List<SepetYemekler> = syds.sepetYemekleriYukle()

    suspend fun ara(aramaKelimesi: String): List<Yemekler> = yds.ara(aramaKelimesi)
}