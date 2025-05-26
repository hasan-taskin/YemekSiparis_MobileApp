package com.example.yemeksiparisuygulamasi.retrofit

import com.example.yemeksiparisuygulamasi.data.entity.CRUDCevap
import com.example.yemeksiparisuygulamasi.data.entity.SepetYemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SepetYemeklerDao {

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepetYemekleriYukle(@Field("kullanici_adi") kullanici_adi:String) : SepetYemeklerCevap

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sil(@Field("sepet_yemek_id")sepet_yemek_id:Int,
                    @Field("kullanici_adi")kullanici_adi: String) :CRUDCevap


}