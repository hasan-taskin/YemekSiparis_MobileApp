package com.example.yemeksiparisuygulamasi.data.datasource

import android.util.Log
import com.example.yemeksiparisuygulamasi.data.entity.Yemekler
import com.example.yemeksiparisuygulamasi.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var ydao:YemeklerDao) {

    suspend fun yemekleriYukle() : List<Yemekler> =
        withContext(Dispatchers.IO){
            return@withContext ydao.yemekleriYukle().yemekler
        }

    suspend fun kaydet(yemek_adi:String,
                       yemek_resim_adi:String,
                       yemek_fiyat:Int,
                       yemek_siparis_adet:Int,
                       kullanici_adi:String) = ydao.kaydet(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    suspend fun ara(aramaKelimesi:String): List<Yemekler> =//arama cubugu
        withContext(Dispatchers.IO){
            val yemeklerListesi = ArrayList<Yemekler>()
            val y1 = Yemekler(1,"Django","django",24)
            yemeklerListesi.add(y1)
            return@withContext yemeklerListesi
        }
}