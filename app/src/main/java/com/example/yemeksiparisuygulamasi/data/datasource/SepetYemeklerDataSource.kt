package com.example.yemeksiparisuygulamasi.data.datasource


import com.example.yemeksiparisuygulamasi.data.entity.SepetYemekler
import com.example.yemeksiparisuygulamasi.retrofit.SepetYemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SepetYemeklerDataSource(var sydao:SepetYemeklerDao) {

    private val kullanici_adi = "hasan-taskin"

    suspend fun sepetYemekleriYukle() : List<SepetYemekler> =
        withContext(Dispatchers.IO){
            return@withContext sydao.sepetYemekleriYukle(kullanici_adi).sepet_yemekler
        }

    suspend fun sil(sepet_yemek_id: Int,kullanici_adi: String) = sydao.sil(sepet_yemek_id,kullanici_adi)
}