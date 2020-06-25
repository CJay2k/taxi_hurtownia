package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Daty {
    private int dataId;
    private Integer dzien;
    private Integer miesiac;
    private Integer rok;
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
    @Column(name = "dzien", nullable = true)
    public Integer getDzien() {
        return dzien;
    }

    public void setDzien(Integer dzien) {
        this.dzien = dzien;
    }

    @Basic
    @Column(name = "miesiac", nullable = true)
    public Integer getMiesiac() {
        return miesiac;
    }

    public void setMiesiac(Integer miesiac) {
        this.miesiac = miesiac;
    }

    @Basic
    @Column(name = "rok", nullable = true)
    public Integer getRok() {
        return rok;
    }

    public void setRok(Integer rok) {
        this.rok = rok;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Daty daty = (Daty) o;
        return dataId == daty.dataId &&
                Objects.equals(dzien, daty.dzien) &&
                Objects.equals(miesiac, daty.miesiac) &&
                Objects.equals(rok, daty.rok);
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
