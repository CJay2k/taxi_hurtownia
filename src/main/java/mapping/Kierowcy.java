package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Kierowcy {
    private int kierowcaId;
    private String imie;
    private String nazwisko;
    private Collection<Kursy> kursiesByKierowcaId;

    @Id
    @Column(name = "kierowca_id", nullable = false)
    public int getKierowcaId() {
        return kierowcaId;
    }

    public void setKierowcaId(int kierowcaId) {
        this.kierowcaId = kierowcaId;
    }

    @Basic
    @Column(name = "imie", nullable = true, length = -1)
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "nazwisko", nullable = true, length = -1)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kierowcy kierowcy = (Kierowcy) o;
        return kierowcaId == kierowcy.kierowcaId &&
                Objects.equals(imie, kierowcy.imie) &&
                Objects.equals(nazwisko, kierowcy.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kierowcaId, imie, nazwisko);
    }

    @OneToMany(mappedBy = "kierowcyByKierowcaId")
    public Collection<Kursy> getKursiesByKierowcaId() {
        return kursiesByKierowcaId;
    }

    public void setKursiesByKierowcaId(Collection<Kursy> kursiesByKierowcaId) {
        this.kursiesByKierowcaId = kursiesByKierowcaId;
    }

    @Override
    public String toString() {
        return this.imie + " " + this.nazwisko;
    }
}
