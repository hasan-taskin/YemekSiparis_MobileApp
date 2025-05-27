![Image](https://github.com/user-attachments/assets/68b7c22a-67d1-4430-be85-a0d4634c8250)

Kullanılan Teknolojiler ve Mimariler

 Geliştirme Dili & Ortamı
Kotlin – Modern, güvenli ve Android için önerilen programlama dili.

Android Studio – Geliştirme ortamı (IDE) olarak kullanılmıştır.

 Mimari Yapı
MVVM (Model-View-ViewModel) – UI ve iş mantığını ayırarak modüler, okunabilir ve sürdürülebilir bir yapı sağlanmıştır.

Repository Pattern – Veri kaynaklarına erişimi soyutlayarak ViewModel ile veri kaynağı arasındaki bağı zayıflatır.

LiveData & MutableLiveData – UI’ın veriyle reaktif şekilde senkronize olması sağlanmıştır.

ViewModel – UI’a yönelik verilerin yaşam döngüsüne uygun yönetimini sağlar.

 Bağımlılık Enjeksiyonu
Dagger Hilt – Dependency injection işlemleri için kullanılmıştır. ViewModel’lara, repository’lere ve veri kaynaklarına bağımlılıklar otomatik olarak enjekte edilmiştir.

 Ağ (Network) İşlemleri
Retrofit – REST API isteklerini gerçekleştirmek için kullanılmıştır.

Gson Converter – JSON verilerinin Kotlin veri sınıflarına (data class) dönüştürülmesini sağlar.

 Veri Yapıları & Veri Akışı
Data Classes (Yemekler, SepetYemekler) – API'den alınan verilerin modellenmesi için kullanılmıştır.

Coroutines & viewModelScope – Asenkron işlemlerde UI thread’i bloklamamak için Kotlin Coroutines kullanılmıştır.

 Görsel Yükleme
Glide – İnternetten alınan yemek resimlerinin optimize ve hızlı bir şekilde yüklenmesini sağlar.

 UI Bileşenleri
RecyclerView – Listeleme işlemleri için kullanılmıştır (yemek listesi, sepet içeriği vb.).

Material Design – Android’in önerdiği kullanıcı arayüzü standartlarına uygun tasarım elemanları tercih edilmiştir.

Bu proje; modern Android geliştirme pratiklerini, sağlam mimari yapıları ve güncel teknolojileri bir arada kullanarak hem teknik yetkinlikleri sergilemek hem de gerçek bir uygulama deneyimi sunmak amacıyla geliştirilmiştir. 
Eğitmenim Sayın Kasım Adalan Ve Pupilicaya teşekkürler.

