package controllers;

import collections.ZestawienieKierowcow;
import collections.ZestawienieMiast;
import collections.ZyskiZKursow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mapping.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

import java.io.IOException;
import java.math.BigDecimal;

public class HurtowniaController {

    public TabPane tabPane;

    //================================================================================
    // Zyski z kursów
    //================================================================================

    public Tab tabZyskiZKursow;
    public ChoiceBox<Kierowcy> zyskiZKursowChoiceBoxKierowcy;
    public TableView<ZyskiZKursow> zyskiZKursowTableView;
    public TableColumn<ZyskiZKursow, Integer> zyskiZKursowColumnRok;
    public TableColumn<ZyskiZKursow, Integer> zyskiZKursowColumnMiesiac;
    public TableColumn<ZyskiZKursow, BigDecimal> zyskiZKursowColumnZysk;

    //================================================================================
    // Zestawienie kierowców
    //================================================================================

    public Tab tabZestawienieKierowcow;
    public ChoiceBox<Integer> zestawienieKierowcowChoiceBoxRok;
    public ChoiceBox<Integer> zestawienieKierowcowChoiceBoxMiesiacOd;
    public ChoiceBox<Integer> zestawienieKierowcowChoiceBoxMiesiacDo;
    public TableView<ZestawienieKierowcow> zestawienieKierowcowTableView;
    public TableColumn<ZestawienieKierowcow, String> zestawienieKierowcowColumnNazwisko;
    public TableColumn<ZestawienieKierowcow, String> zestawienieKierowcowColumnImie;
    public TableColumn<ZestawienieKierowcow, BigDecimal> zestawienieKierowcowColumnZysk;

    //================================================================================
    // Zestawienie miast
    //================================================================================

    public Tab tabZestawienieMiast;
    public ChoiceBox<Integer> zestawienieMiastChoiceBoxRok;
    public ChoiceBox<Integer> zestawienieMiastChoiceBoxMiesiacOd;
    public ChoiceBox<Integer> zestawienieMiastChoiceBoxMiesiacDo;
    public TableView<ZestawienieMiast> zestawienieMiastTableView;
    public TableColumn<ZestawienieMiast, String> zestawienieMiastColumnMiasto;
    public TableColumn<ZestawienieMiast, Long> zestawienieMiastColumnIloscKlientow;

    //================================================================================
    // Potencjalni oszuści
    //================================================================================

    public Tab tabPotencjalniOszusci;
    public TableView<Kierowcy> potencjalniOszusciTableView;
    public TableColumn<Kierowcy, String> potencjalniOszusciColumnNazwisko;
    public TableColumn<Kierowcy, String> potencjalniOszusciColumnImie;
    public TextArea textAreaPotencjalniOszusci;

    //================================================================================
    // Private
    //================================================================================

    private Tab currentTab;

    public void initialize() throws Exception {
        currentTab = tabPane.getSelectionModel().getSelectedItem();
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> currentTab = newTab);

        zyskiZKursowColumnRok.setCellValueFactory(new PropertyValueFactory<>("rok"));
        zyskiZKursowColumnMiesiac.setCellValueFactory(new PropertyValueFactory<>("miesiac"));
        zyskiZKursowColumnZysk.setCellValueFactory(new PropertyValueFactory<>("zysk"));
        zestawienieKierowcowColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        zestawienieKierowcowColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        zestawienieKierowcowColumnZysk.setCellValueFactory(new PropertyValueFactory<>("zysk"));
        zestawienieMiastColumnMiasto.setCellValueFactory(new PropertyValueFactory<>("nazwaMiasta"));
        zestawienieMiastColumnIloscKlientow.setCellValueFactory(new PropertyValueFactory<>("iloscKlientow"));
        potencjalniOszusciColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        potencjalniOszusciColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));

        loadDataZyskiZKursow(true);
        loadDataZestawienieKierowcow(true, true);
        loadDataZestawienieMiast(true, true);
        loadAllKierowcyToPotencjalniOszusci();

    }

    private void loadDataZyskiZKursow(boolean reloadChoiceBoxKierowcy) {
        try (Session session = SessionController.getSession()) {

            if (reloadChoiceBoxKierowcy) fillComboBoxKierowcy(zyskiZKursowChoiceBoxKierowcy);

            if (zyskiZKursowChoiceBoxKierowcy.getSelectionModel().isEmpty()) return;

            Query<ZyskiZKursow> query = session.createQuery("SELECT new collections.ZyskiZKursow(k.datyByDataId.rok, k.datyByDataId.miesiac, sum(k.cena)) FROM Kursy k WHERE k.kierowcyByKierowcaId.id=" + zyskiZKursowChoiceBoxKierowcy.getValue().getKierowcaId() + " GROUP BY ROLLUP(k.datyByDataId.miesiac), k.datyByDataId.rok ORDER BY k.datyByDataId.rok DESC, k.datyByDataId.miesiac", ZyskiZKursow.class);

            zyskiZKursowTableView.setItems(FXCollections.observableArrayList(query.list()));
        }
    }

    private void loadDataZestawienieKierowcow(boolean reloadChoiceBoxRok, boolean reloadChoiceBoxMiesiac) {
        try (Session session = SessionController.getSession()) {

            if (reloadChoiceBoxRok) fillComboBoxRok(zestawienieKierowcowChoiceBoxRok);

            if (zestawienieKierowcowChoiceBoxRok.getSelectionModel().isEmpty()) return;

            if (reloadChoiceBoxMiesiac) fillComboBoxMiesiac(zestawienieKierowcowChoiceBoxRok, zestawienieKierowcowChoiceBoxMiesiacOd, zestawienieKierowcowChoiceBoxMiesiacDo);

            if (zestawienieKierowcowChoiceBoxMiesiacOd.getSelectionModel().isEmpty() || zestawienieKierowcowChoiceBoxMiesiacDo.getSelectionModel().isEmpty()){
                zestawienieKierowcowTableView.setItems(null);
                return;
            }

            Query<ZestawienieKierowcow> query = session.createQuery("SELECT new collections.ZestawienieKierowcow(k.kierowcyByKierowcaId.nazwisko, k.kierowcyByKierowcaId.imie, sum(k.cena) AS SumaZyskow) FROM Kursy k WHERE k.datyByDataId.rok=" + zestawienieKierowcowChoiceBoxRok.getValue() + " AND k.datyByDataId.miesiac BETWEEN " + zestawienieKierowcowChoiceBoxMiesiacOd.getValue() + " AND " + zestawienieKierowcowChoiceBoxMiesiacDo.getValue() + " GROUP BY ROLLUP ((k.kierowcyByKierowcaId.nazwisko, k.kierowcyByKierowcaId.imie)) ORDER BY k.kierowcyByKierowcaId.nazwisko, k.kierowcyByKierowcaId.imie", ZestawienieKierowcow.class);

            zestawienieKierowcowTableView.setItems(FXCollections.observableArrayList(query.list()));
        }
    }

    private void loadDataZestawienieMiast(boolean reloadChoiceBoxRok, boolean reloadChoiceBoxMiesiac) {
        try (Session session = SessionController.getSession()) {

            if (reloadChoiceBoxRok) fillComboBoxRok(zestawienieMiastChoiceBoxRok);

            if (zestawienieMiastChoiceBoxRok.getSelectionModel().isEmpty()) return;

            if (reloadChoiceBoxMiesiac) fillComboBoxMiesiac(zestawienieMiastChoiceBoxRok, zestawienieMiastChoiceBoxMiesiacOd, zestawienieMiastChoiceBoxMiesiacDo);

            if (zestawienieMiastChoiceBoxMiesiacOd.getSelectionModel().isEmpty() || zestawienieMiastChoiceBoxMiesiacDo.getSelectionModel().isEmpty()){
                zestawienieMiastTableView.setItems(null);
                return;
            }

            Query<ZestawienieMiast> query = session.createQuery("SELECT new collections.ZestawienieMiast(k.klienciByKlientId.miasto, count(k) AS SumaKlientow) FROM Kursy k WHERE k.datyByDataId.rok=" + zestawienieMiastChoiceBoxRok.getValue() + " AND k.datyByDataId.miesiac BETWEEN " + zestawienieMiastChoiceBoxMiesiacOd.getValue() + " AND " + zestawienieMiastChoiceBoxMiesiacDo.getValue() + " GROUP BY k.klienciByKlientId.miasto ORDER BY SumaKlientow DESC", ZestawienieMiast.class);

            zestawienieMiastTableView.setItems(FXCollections.observableArrayList(query.list()));
        }
    }

    @FXML
    private void loadAllKierowcyToPotencjalniOszusci() {
        try (Session session = SessionController.getSession()) {
            Query<Kierowcy> query = session.createQuery("FROM Kierowcy k ORDER BY k.nazwisko", Kierowcy.class);
            potencjalniOszusciTableView.setItems(FXCollections.observableArrayList(query.list()));
        }

        textAreaPotencjalniOszusci.setText("");
    }

    @FXML
    private void loadDataPotencjalniOszusci() throws Exception {
        BasicTools.processData();
        klasyfikacjaNowychPrzypadkow();

        try (Session session = SessionController.getSession()) {

            Instances instances = BasicTools.loadData("./src/main/java/data/kursy_bez_decyzji_wypelnione.arff");

            String queryString = "";
            StringBuilder stringBuilderQueryString = new StringBuilder(queryString);

            String explainString = "";
            StringBuilder stringBuilderexplainString = new StringBuilder(explainString);

            stringBuilderQueryString.append("SELECT DISTINCT k FROM Kierowcy k JOIN FETCH k.kursiesByKierowcaId ku WHERE 1=0 OR ");
            for (Instance instance : instances) {
                if(instance.toString().contains("t")){
                    stringBuilderQueryString.append("(ku.odleglosc=").append(instance.value(0)).append(" AND ku.cena=").append(instance.value(1)).append(") OR ");
                    stringBuilderexplainString.append("Za kurs o długości: ").append(instance.value(0)).append("km została naliczona opłata w wysokości: ").append(instance.value(1)).append("zł \n");
                }
            }
            stringBuilderQueryString.delete(stringBuilderQueryString.length()-3, stringBuilderQueryString.length()-1);

            queryString = stringBuilderQueryString.toString();
            explainString = stringBuilderexplainString.toString();

            Query<Kierowcy> query = session.createQuery(queryString, Kierowcy.class);
            potencjalniOszusciTableView.setItems(FXCollections.observableArrayList(query.list()));

            if(explainString.isEmpty()){
                textAreaPotencjalniOszusci.setText("Nie znaleziono żadnych potencjalnych oszustów :)");
            }else {
                textAreaPotencjalniOszusci.setText(explainString + "\nWyżej wyświetleni kierowcy są odpowiedzialni za niepoprawne naliczanie opłat za kurs!");
            }

        }
    }

    private void fillComboBoxKierowcy(ChoiceBox<Kierowcy> choiceBoxKierowcy) {
        try (Session session = SessionController.getSession()) {
            Query<Kierowcy> query1 = session.createQuery("SELECT k FROM Kierowcy k ORDER BY k.nazwisko, k.imie", Kierowcy.class);
            ObservableList<Kierowcy> listaKierowcow = FXCollections.observableArrayList(query1.list());

            choiceBoxKierowcy.setItems(listaKierowcow);
        }
    }

    private void fillComboBoxRok(ChoiceBox<Integer> choiceBoxRok) {
        try (Session session = SessionController.getSession()) {
            Query<Integer> query1 = session.createQuery("SELECT DISTINCT d.rok FROM Daty d ORDER BY d.rok", Integer.class);
            ObservableList<Integer> listaLat = FXCollections.observableArrayList(query1.list());

            choiceBoxRok.setItems(listaLat);
        }
    }

    private void fillComboBoxMiesiac(ChoiceBox<Integer> choiceBoxRok, ChoiceBox<Integer> choiceBoxMiesiacOd, ChoiceBox<Integer> choiceBoxMiesiacDo) {
        try (Session session = SessionController.getSession()) {
            Query<Integer> query1 = session.createQuery("SELECT DISTINCT d.miesiac FROM Daty d WHERE d.rok=" + choiceBoxRok.getValue() + " ORDER BY d.miesiac", Integer.class);
            ObservableList<Integer> listaMiesiecy = FXCollections.observableArrayList(query1.list());

            choiceBoxMiesiacOd.setItems(listaMiesiecy);
            choiceBoxMiesiacDo.setItems(listaMiesiecy);

        }
    }
    @FXML
    private void kierowcyChoiceBoxActionHandle() {
        loadDataZyskiZKursow(false);
    }

    @FXML
    private void rokChoiceBoxActionHandle() {
        if (currentTab.equals(tabZestawienieKierowcow)) loadDataZestawienieKierowcow(false, true);
        else if (currentTab.equals(tabZestawienieMiast)) loadDataZestawienieMiast(false, true);
    }

    @FXML
    private void miesiacChoiceBoxActionHandle() {
        if (currentTab.equals(tabZestawienieKierowcow)) loadDataZestawienieKierowcow(false, false);
        else if (currentTab.equals(tabZestawienieMiast)) loadDataZestawienieMiast(false, false);
    }

    public static void klasyfikacjaNowychPrzypadkow() throws Exception{
        Instances trainData = BasicTools.loadData("./src/main/java/data/kursy.arff");
        trainData.setClassIndex(trainData.numAttributes() - 1);
        String[] options = Utils.splitOptions("-U -A -M 2");
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(trainData);

        //Wczytanie tablicy z pustym atrybutem decyzyjnym
        Instances unlabeledData = BasicTools.loadData("./src/main/java/data/kursy_bez_decyzji.arff");
        unlabeledData.setClassIndex(unlabeledData.numAttributes() - 1);

        //Kopia tablicy nieetykietowanej (do wypenienia)
        Instances labeled = new Instances(unlabeledData);

        for (int i = 0; i < unlabeledData.numInstances(); i++)
        {
            //Klasyfikacja obiektu
            double decision = tree.classifyInstance(unlabeledData.instance(i));

            //Ustawienie wartosci decyzji w obiekcie
            labeled.instance(i).setClassValue(decision);

            //Wypisanie wstawionej decyzji
//            System.out.println("Decyzja=" + decision + "  DEC=" + labeled.instance(i).toString(labeled.numAttributes() - 1));
        }

        //Zapis tablicy wypelnionej
        BasicTools.saveData(labeled, "./src/main/java/data/kursy_bez_decyzji_wypelnione.arff");

    }


}
