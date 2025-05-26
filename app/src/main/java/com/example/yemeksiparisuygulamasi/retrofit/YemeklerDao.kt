package com.example.yemeksiparisuygulamasi.retrofit

import com.example.yemeksiparisuygulamasi.data.entity.CRUDCevap
import com.example.yemeksiparisuygulamasi.data.entity.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun yemekleriYukle() : YemeklerCevap

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun kaydet(@Field("yemek_adi")yemek_adi:String,
                       @Field("yemek_resim_adi")yemek_resim_adi:String,
                       @Field("yemek_fiyat")yemek_fiyat:Int,
                       @Field("yemek_siparis_adet")yemek_siparis_adet:Int,
                       @Field("kullanici_adi")kullanici_adi:String) : CRUDCevap

}