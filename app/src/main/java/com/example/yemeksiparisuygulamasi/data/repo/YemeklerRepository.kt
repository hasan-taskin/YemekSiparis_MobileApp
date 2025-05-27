package com.example.yemeksiparisuygulamasi.data.repo

import com.example.yemeksiparisuygulamasi.data.datasource.SepetYemeklerDataSource
import com.example.yemeksiparisuygulamasi.data.datasource.YemeklerDataSource
import com.example.yemeksiparisuygulamasi.data.entity.SepetYemekler
import com.example.yemeksiparisuygulamasi.data.entity.Yemekler

class YemeklerRepository(
    private val yds: YemeklerDataSource,
    private val syds: SepetYemeklerDataSource
) {

    suspend fun kaydet(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        try {
            yds.kaydet(
                yemek_adi,
                yemek_resim_adi,
                yemek_fiyat,
                yemek_siparis_adet,
                kullanici_adi
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    suspend fun sil(sepet_yemek_id: Int, kullanici_adi: String) {
        try {
            syds.sil(sepet_yemek_id, kullanici_adi)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    suspend fun yemekleriYukle(): List<Yemekler> {
        return try {
            yds.yemekleriYukle()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun sepetYemekleriYukle(): List<SepetYemekler> {
        return try {
            syds.sepetYemekleriYukle()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun ara(aramaKelimesi: String): List<Yemekler> {
        return try {
            yds.ara(aramaKelimesi)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
