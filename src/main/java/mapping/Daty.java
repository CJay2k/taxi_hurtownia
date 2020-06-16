package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Daty {
    private int dataId;
    private int dzien;
    private int miesiac;
    private int rok;
    private Collection<Kursy> kursiesByDataId;

    @Id
    @Column(name = "data_id", nullable = false)
    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    @Basic
    @Column(name = "dzien", nullable = false)
    public int getDzien() {
        return dzien;
    }

    public void setDzien(int dzien) {
        this.dzien = dzien;
    }

    @Basic
    @Column(name = "miesiac", nullable = false)
    public int getMiesiac() {
        return miesiac;
    }

    public void setMiesiac(int miesiac) {
        this.miesiac = miesiac;
    }

    @Basic
    @Column(name = "rok", nullable = false)
    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Daty daty = (Daty) o;
        return dataId == daty.dataId &&
                dzien == daty.dzien &&
                miesiac == daty.miesiac &&
                rok == daty.rok;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataId, dzien, miesiac, rok);
    }

    @OneToMany(mappedBy = "datyByDataId")
    public Collection<Kursy> getKursiesByDataId() {
        return kursiesByDataId;
    }

    public void setKursiesByDataId(Collection<Kursy> kursiesByDataId) {
        this.kursiesByDataId = kursiesByDataId;
    }
}
