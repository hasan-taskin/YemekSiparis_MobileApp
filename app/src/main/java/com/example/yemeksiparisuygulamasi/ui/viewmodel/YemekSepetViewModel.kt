package com.example.yemeksiparisuygulamasi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparisuygulamasi.data.entity.SepetYemekler
import com.example.yemeksiparisuygulamasi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemekSepetViewModel @Inject constructor (var yrepo:YemeklerRepository) :ViewModel(){
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()

    init {
        sepetYemekleriYukle()
    }

    fun sil(sepet_yemek_id: Int,kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.sil(sepet_yemek_id,kullanici_adi)
            sepetYemekleriYukle()
        }
    }

    fun sepetYemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                sepetYemeklerListesi.value = yrepo.sepetYemekleriYukle()
            }catch (e:Exception){ }

        }
    }
}