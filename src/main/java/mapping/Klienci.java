package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Klienci {
    private int klientId;
    private String imie;
    private String nazwisko;
    private String miasto;
    private String nrTelefonu;
    private Collection<Kursy> kursiesByKlientId;

    @Id
    @Column(name = "klient_id", nullable = false)
    public int getKlientId() {
        return klientId;
    }

    public void setKlientId(int klientId) {
        this.klientId = klientId;
    }

    @Basic
    @Column(name = "imie", nullable = false, length = 255)
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "nazwisko", nullable = false, length = 255)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Basic
    @Column(name = "miasto", nullable = false, length = 255)
    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    @Basic
    @Column(name = "nr_telefonu", nullable = true, length = 9)
    public String getNrTelefonu() {
        return nrTelefonu;
    }

    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klienci klienci = (Klienci) o;
        return klientId == klienci.klientId &&
                Objects.equals(imie, klienci.imie) &&
                Objects.equals(nazwisko, klienci.nazwisko) &&
                Objects.equals(miasto, klienci.miasto) &&
                Objects.equals(nrTelefonu, klienci.nrTelefonu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(klientId, imie, nazwisko, miasto, nrTelefonu);
    }

    @OneToMany(mappedBy = "klienciByKlientId")
    public Collection<Kursy> getKursiesByKlientId() {
        return kursiesByKlientId;
    }

    public void setKursiesByKlientId(Collection<Kursy> kursiesByKlientId) {
        this.kursiesByKlientId = kursiesByKlientId;
    }
}
