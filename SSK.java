import java.util.*;

interface Adres {
    // Adres ile ilgili metotlar
}

class EvAdresi implements Adres {
    // EvAdresi sınıfı, Adres interface'ini uygular
}

class IsAdresi implements Adres {
    // IsAdresi sınıfı, Adres interface'ini uygular
}

class Kullanici {
    String isim, soyisim, email, sifre, meslek;
    int yas;
    ArrayList<Adres> adresListesi = new ArrayList<>();
    Date sonGirisTarihi;

    // Kullanici ile ilgili metotlar
}

abstract class Hesap {
    enum DogrulamaDurumu { BASARILI, BASARISIZ }
    DogrulamaDurumu kullaniciDurumu;
    Kullanici kullanici;
    ArrayList<Sigorta> sigortaListesi = new ArrayList<>();

    abstract void sigortaEkle(Sigorta sigorta);

    // Hesap ile ilgili diğer metotlar
}

class BireyselHesap extends Hesap {
    // BireyselHesap ile ilgili metotlar
}

class KurumsalHesap extends Hesap {
    // KurumsalHesap ile ilgili metotlar
}

abstract class Sigorta {
    String isim;
    double ucret;
    Date baslangicTarihi, bitisTarihi;

    abstract double primHesapla();

    // Sigorta ile ilgili diğer metotlar
}

class SaglikSigortasi extends Sigorta {
    // SaglikSigortasi ile ilgili metotlar
}

class KonutSigortasi extends Sigorta {
    // KonutSigortasi ile ilgili metotlar
}

class HesapYoneticisi {
    TreeSet<Hesap> hesaplar = new TreeSet<>();

    void hesapEkle(Hesap hesap) {
        hesaplar.add(hesap);
    }

    Hesap dogrula(String email, String sifre) throws InvalidAuthenticationException {
        for (Hesap hesap : hesaplar) {
            if (hesap.kullanici.email.equals(email) && hesap.kullanici.sifre.equals(sifre)) {
                hesap.kullaniciDurumu = Hesap.DogrulamaDurumu.BASARILI;
                return hesap;
            }
        }
        throw new InvalidAuthenticationException("Geçersiz kimlik bilgileri!");
    }

    // Diğer hesap yöneticisi metotları
}

class InvalidAuthenticationException extends Exception {
    InvalidAuthenticationException(String message) {
        super(message);
    }
}

public class SigortaYonetimSistemi {
    public static void main(String[] args) {
        HesapYoneticisi hesapYoneticisi = new HesapYoneticisi();

        // Test verileri ve işlemleri burada gerçekleştirilir
        BireyselHesap bireyselHesap = new BireyselHesap();
        bireyselHesap.kullanici = new Kullanici();
        bireyselHesap.kullanici.email = "ornek@bireysel.com";
        bireyselHesap.kullanici.sifre = "bireysel123";
        hesapYoneticisi.hesapEkle(bireyselHesap);

        try {
            Hesap girisYapanHesap = hesapYoneticisi.dogrula("ornek@bireysel.com", "bireysel123");
            System.out.println("Giriş yapılan hesap türü: " + girisYapanHesap.getClass().getSimpleName());
        } catch (InvalidAuthenticationException e) {
            System.out.println("Giriş yapılamadı: " + e.getMessage());
        }
    }
}
