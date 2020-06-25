package controllers;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

public class BasicTools {

    public static Instances loadData(String fileName) throws IOException {
        ArffLoader loader = new ArffLoader();
        loader.setFile(new File(fileName));
        return loader.getDataSet();
    }

    public static void saveData(Instances data,String fileName) throws IOException {
        ArffSaver saver = new ArffSaver();
        saver.setFile(new File(fileName));
        saver.setInstances(data);
        saver.writeBatch();
    }

    static public void processData() throws Exception {
        Properties properties=new Properties();

        File external = new File("./app.properties");
        if (external.exists())
            properties.load(new FileInputStream(external));
        else
            properties.load(SessionController.class.getClassLoader().getResourceAsStream("config/config.properties"));

        String username = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        String query = "SELECT k.odleglosc, k.cena, '?' FROM kursy k";

        Instances kursy = SQLDataImporter.getDataSetFromPostgreSQL(username, password, query, 0);

        ArrayList<String> dataOut = new ArrayList<>();

        for (weka.core.Instance datum : kursy) {

            String s = "";
            StringBuilder sB = new StringBuilder(s);

            for (int j = 0; j < datum.numValues(); j++) {
                if (j == datum.numValues() - 1) {
                    sB.append(datum.toString(j).replace("'", ""));
                } else{
                    sB.append(datum.toString(j).replace("'", "")).append(",");
                }
            }

            s = sB.toString();
            dataOut.add(s);

        }

        String filename = "./src/main/java/data/kursy_bez_decyzji.arff";
        PrintWriter out = new PrintWriter(filename);
        out.println("@relation kursy");
        out.println();
        out.println("@attribute odleglosc numeric");
        out.println("@attribute cena numeric");
        out.println("@attribute oszust {t,n,?}");

        out.println();
        out.println("@data");
        for (String s : dataOut) {
            out.println(s);
        }
        out.close();

    }
}