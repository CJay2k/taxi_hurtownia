package mapping;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Samochody {
    private int samochodId;
    private String model;
    private int liczbaMiejsc;
    private int rokProdukcji;
    private Date dataWaznosciUbezpieczenia;
    private Collection<Kierowcy> kierowciesBySamochodId;

    @Id
    @Column(name = "samochod_id", nullable = false)
    public int getSamochodId() {
        return samochodId;
    }

    public void setSamochodId(int samochodId) {
        this.samochodId = samochodId;
    }

    @Basic
    @Column(name = "model", nullable = false, length = 255)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "liczba_miejsc", nullable = false)
    public int getLiczbaMiejsc() {
        return liczbaMiejsc;
    }

    public void setLiczbaMiejsc(int liczbaMiejsc) {
        this.liczbaMiejsc = liczbaMiejsc;
    }

    @Basic
    @Column(name = "rok_produkcji", nullable = false)
    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    @Basic
    @Column(name = "data_waznosci_ubezpieczenia", nullable = false)
    public Date getDataWaznosciUbezpieczenia() {
        return dataWaznosciUbezpieczenia;
    }

    public void setDataWaznosciUbezpieczenia(Date dataWaznosciUbezpieczenia) {
        this.dataWaznosciUbezpieczenia = dataWaznosciUbezpieczenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Samochody samochody = (Samochody) o;
        return samochodId == samochody.samochodId &&
                liczbaMiejsc == samochody.liczbaMiejsc &&
                rokProdukcji == samochody.rokProdukcji &&
                Objects.equals(model, samochody.model) &&
                Objects.equals(dataWaznosciUbezpieczenia, samochody.dataWaznosciUbezpieczenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(samochodId, model, liczbaMiejsc, rokProdukcji, dataWaznosciUbezpieczenia);
    }

    @OneToMany(mappedBy = "samochodyBySamochodId")
    public Collection<Kierowcy> getKierowciesBySamochodId() {
        return kierowciesBySamochodId;
    }

    public void setKierowciesBySamochodId(Collection<Kierowcy> kierowciesBySamochodId) {
        this.kierowciesBySamochodId = kierowciesBySamochodId;
    }
}
