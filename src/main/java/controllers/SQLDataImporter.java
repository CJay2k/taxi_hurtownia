package controllers;

import weka.core.Instances;
import weka.experiment.InstanceQuery;

import java.io.File;

/**
 * Klasa dostarcza metod importujących tablice do WEKA z relacyjnej bazy danych
 * <p>
 * Podaje się nazwe uzytkonika i haslo do bazy danych
 * <p>
 * Parametr queryText powinien zawierac poprawne zapytanie SQL (BEZ ŚREDNIKA NA KOŃCU!)
 * <p>
 * Parametr limit ogranicza liczbe wierszy kopiowanych do tablicy z wyniku zapytania
 * Wiersze sa brane od poczatku wyniku zapytania.
 * Jeśli parametr limit jest mniejszy od 1, to ograniczenie nie jest uwzgledniane
 */

public class SQLDataImporter {

    public static Instances getDataSetFromPostgreSQL(String userName, String password, String queryText, int limit) throws Exception {

        InstanceQuery query = new InstanceQuery();
        query.setUsername(userName);
        query.setPassword(password);

        // plik ustawien polaczenia z baza danych
        query.setCustomPropsFile(new File("./src/main/java/settings/DatabaseUtils.props"));

        if (limit > 0) query.setQuery(queryText + " limit " + limit + ";");
        else query.setQuery(queryText + ";");

        return query.retrieveInstances();
    }
}
