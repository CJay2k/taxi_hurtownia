package mapping;

import java.math.BigDecimal;

public class ZestawienieMiast {
    private String nazwaMiasta;
    private long iloscKlientow;

    public ZestawienieMiast() {}

    public ZestawienieMiast(String nazwaMiasta, long iloscKlientow) {
        this.nazwaMiasta = nazwaMiasta;
        this.iloscKlientow = iloscKlientow;
    }

    public String getNazwaMiasta() {
        return nazwaMiasta;
    }

    public void setNazwaMiasta(String nazwaMiasta) {
        this.nazwaMiasta = nazwaMiasta;
    }

    public long getIloscKlientow() {
        return iloscKlientow;
    }

    public void setIloscKlientow(long iloscKlientow) {
        this.iloscKlientow = iloscKlientow;
    }
}
