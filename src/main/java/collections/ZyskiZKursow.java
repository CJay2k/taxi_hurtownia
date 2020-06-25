package collections;

import java.math.BigDecimal;

public class ZyskiZKursow {
    private Integer rok;
    private Integer miesiac;
    private BigDecimal zysk;

    public ZyskiZKursow() {}

    public ZyskiZKursow(BigDecimal zysk) {
        this.rok=0;
        this.miesiac=0;
        this.zysk=zysk;
    }

    public ZyskiZKursow(Integer rok, Integer miesiac, BigDecimal zysk) {
        this.rok=rok;
        this.miesiac=miesiac;
        this.zysk=zysk;
    }

    public Integer getRok() {
        return rok;
    }

    public void setRok(Integer rok) {
        this.rok = rok;
    }

    public Integer getMiesiac() {
        return miesiac;
    }

    public void setMiesiac(Integer miesiac) {
        this.miesiac = miesiac;
    }

    public BigDecimal getZysk() {
        return zysk;
    }

    public void setZysk(BigDecimal zysk) {
        this.zysk = zysk;
    }

}