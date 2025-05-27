package com.example.yemeksiparisuygulamasi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yemeksiparisuygulamasi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemekDetayViewModel @Inject constructor(private val yrepo: YemeklerRepository) : ViewModel() {

    fun kaydet(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int = 1,
        kullanici_adi: String = "hasan-taskin"
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            val mevcutSepet = yrepo.sepetYemekleriYukle()

            val ayniYemek = mevcutSepet.find {
                it.yemek_adi == yemek_adi && it.kullanici_adi == kullanici_adi
            }

            if (ayniYemek != null) {
                val yeniAdet = ayniYemek.yemek_siparis_adet + yemek_siparis_adet

                yrepo.sil(ayniYemek.sepet_yemek_id, kullanici_adi)

                yrepo.kaydet(
                    yemek_adi = yemek_adi,
                    yemek_resim_adi = yemek_resim_adi,
                    yemek_fiyat = yemek_fiyat,
                    yemek_siparis_adet = yeniAdet,
                    kullanici_adi = kullanici_adi
                )
            } else {
                yrepo.kaydet(
                    yemek_adi = yemek_adi,
                    yemek_resim_adi = yemek_resim_adi,
                    yemek_fiyat = yemek_fiyat,
                    yemek_siparis_adet = yemek_siparis_adet,
                    kullanici_adi = kullanici_adi
                )
            }
        }
    }
}


