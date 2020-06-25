package collections;

import java.math.BigDecimal;

public class ZestawienieKierowcow {
    private String nazwisko;
    private String imie;
    private BigDecimal zysk;

    public ZestawienieKierowcow() {}

    public ZestawienieKierowcow(String nazwisko, String imie, BigDecimal zysk) {
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.zysk = zysk;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public BigDecimal getZysk() {
        return zysk;
    }

    public void setZysk(BigDecimal zysk) {
        this.zysk = zysk;
    }

}