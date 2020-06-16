package mapping;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Kierowcy {
    private int kierowcaId;
    private String imie;
    private String nazwisko;
    private String pesel;
    private int nrLicencji;
    private Date dataWaznosciLicencji;
    private String nrTelefonu;
    private int ocena;
    private Samochody samochodyBySamochodId;
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
    @Column(name = "imie", nullable = false, length = -1)
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "nazwisko", nullable = false, length = -1)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Basic
    @Column(name = "pesel", nullable = false, length = 11)
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Basic
    @Column(name = "nr_licencji", nullable = false)
    public int getNrLicencji() {
        return nrLicencji;
    }

    public void setNrLicencji(int nrLicencji) {
        this.nrLicencji = nrLicencji;
    }

    @Basic
    @Column(name = "data_waznosci_licencji", nullable = false)
    public Date getDataWaznosciLicencji() {
        return dataWaznosciLicencji;
    }

    public void setDataWaznosciLicencji(Date dataWaznosciLicencji) {
        this.dataWaznosciLicencji = dataWaznosciLicencji;
    }

    @Basic
    @Column(name = "nr_telefonu", nullable = false, length = 9)
    public String getNrTelefonu() {
        return nrTelefonu;
    }

    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    @Basic
    @Column(name = "ocena", nullable = false)
    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kierowcy kierowcy = (Kierowcy) o;
        return kierowcaId == kierowcy.kierowcaId &&
                nrLicencji == kierowcy.nrLicencji &&
                ocena == kierowcy.ocena &&
                Objects.equals(imie, kierowcy.imie) &&
                Objects.equals(nazwisko, kierowcy.nazwisko) &&
                Objects.equals(pesel, kierowcy.pesel) &&
                Objects.equals(dataWaznosciLicencji, kierowcy.dataWaznosciLicencji) &&
                Objects.equals(nrTelefonu, kierowcy.nrTelefonu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kierowcaId, imie, nazwisko, pesel, nrLicencji, dataWaznosciLicencji, nrTelefonu, ocena);
    }

    @ManyToOne
    @JoinColumn(name = "samochod_id", referencedColumnName = "samochod_id", nullable = false)
    public Samochody getSamochodyBySamochodId() {
        return samochodyBySamochodId;
    }

    public void setSamochodyBySamochodId(Samochody samochodyBySamochodId) {
        this.samochodyBySamochodId = samochodyBySamochodId;
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
