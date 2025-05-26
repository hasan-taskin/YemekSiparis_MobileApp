package com.example.yemeksiparisuygulamasi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yemeksiparisuygulamasi.data.entity.Yemekler
import com.example.yemeksiparisuygulamasi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(private val yrepo: YemeklerRepository) : ViewModel() {
    private val _yemeklerListesi = MutableLiveData<List<Yemekler>>()
    val yemeklerListesi: LiveData<List<Yemekler>> = _yemeklerListesi


    fun kaydet(yemek_adi: String,
               yemek_resim_adi: String,
               yemek_fiyat: Int,
               yemek_siparis_adet: Int,
               kullanici_adi: String) {
        viewModelScope.launch {
            yrepo.kaydet(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        }
    }

    fun yemekleriYukle() {
        viewModelScope.launch {
            try {
                val yemekler = yrepo.yemekleriYukle()
                _yemeklerListesi.postValue(yemekler)
            } catch (e: Exception) {
                Log.e("AnasayfaViewModel", "Yemekler yüklenirken hata: ${e.message}")
            }
        }
    }

    fun ara(aramaKelimesi: String) {
        viewModelScope.launch {
            try {
                val sonuclar = yrepo.ara(aramaKelimesi)
                _yemeklerListesi.postValue(sonuclar)
            } catch (e: Exception) {
                Log.e("AnasayfaViewModel", "Arama yapılırken hata: ${e.message}")
            }
        }
    }
}