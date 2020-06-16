package mapping;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ZyskiZKursow {
    private int rok;
    private int miesiac;
    private BigDecimal zysk;

    public ZyskiZKursow() {}

    public ZyskiZKursow(int rok, int miesiac, BigDecimal zysk) {
        this.rok=rok;
        this.miesiac=miesiac;
        this.zysk=zysk;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public int getMiesiac() {
        return miesiac;
    }

    public void setMiesiac(int miesiac) {
        this.miesiac = miesiac;
    }

    public BigDecimal getZysk() {
        return zysk;
    }

    public void setZysk(BigDecimal zysk) {
        this.zysk = zysk;
    }

}
