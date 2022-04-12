package pl.sda.refactorapp.service;

import pl.sda.refactorapp.annotation.Autowired;

class Main {
    void podliczDzien() {
        //podliczamy utarg z dnia
    }
}

class Pain {
    void podliczDzienAlboDwa(boolean isJeden) {
        //podliczamy utarg z dnia
    }
}

class NaszePodliczanie extends Main {
    @Override
    void podliczDzien() {
        // najpierw sprawdz czy jest dobra godzina
        // czy kasa jest sprawna
        super.podliczDzien();

        // wygeneruj raport
    }
}

class ProxyDoPodliczania extends Pain {
    public void podliczDzien() {
        boolean isJeden = true;
        super.podliczDzienAlboDwa(isJeden);
    }
}

class PodliczanieService {
    @Autowired
    private ProxyDoPodliczania proxyDoPodliczania;

    void podliczDzienPoNaszemu() {
        // najpierw sprawdz czy jest dobra godzina
        // czy kasa jest sprawna
        proxyDoPodliczania.podliczDzien();
        // wygeneruj raport
    }
}

