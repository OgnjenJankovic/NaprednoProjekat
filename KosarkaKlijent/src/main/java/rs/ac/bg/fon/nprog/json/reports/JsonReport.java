package rs.ac.bg.fon.nprog.json.reports;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.ac.bg.fon.nprog.domain.Administrator;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Termin;

public class JsonReport {

	private static Map<Administrator, LocalDateTime> prijaveAdministratora;
    private static Map<Administrator, LocalDateTime> odjaveAdministratora;

    final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void generateReport() {
        JsonObject jsonReport = new JsonObject();

        addLoginLogoutInfo(jsonReport);

        writeToAFile(jsonReport);
    }

    private static void addLoginLogoutInfo(JsonObject jsonReport) {
        JsonArray prijaveJsonArray = new JsonArray();
        JsonArray odjaveJsonArray = new JsonArray();

        for (Map.Entry<Administrator, LocalDateTime> entry : prijaveAdministratora.entrySet()) {
            Administrator administrator = entry.getKey();
            LocalDateTime prijava = entry.getValue();
            LocalDateTime odjava = odjaveAdministratora.get(administrator);

            JsonObject prijavaJson = new JsonObject();
            prijavaJson.addProperty("administrator", administrator.toString());
            prijavaJson.addProperty("datumVreme", prijava.format(FORMAT));
            prijaveJsonArray.add(prijavaJson);

            if (odjava != null) {
                JsonObject odjavaJson = new JsonObject();
                odjavaJson.addProperty("administrator", administrator.toString());
                odjavaJson.addProperty("datumVreme", odjava.format(FORMAT));
                odjaveJsonArray.add(odjavaJson);
            }
        }

        jsonReport.add("prijaveAdministratora", prijaveJsonArray);
        jsonReport.add("odjaveAdministratora", odjaveJsonArray);
    }

    private static void writeToAFile(JsonObject jsonReport) {
        try (FileWriter out = new FileWriter("izvestajAdministratora.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            out.write(gson.toJson(jsonReport));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPrijaveAdministratora(Map<Administrator, LocalDateTime> prijaveAdministratora) {
    	JsonReport.prijaveAdministratora = prijaveAdministratora;
    }

    public static void setOdjaveAdministratora(Map<Administrator, LocalDateTime> odjaveAdministratora) {
    	JsonReport.odjaveAdministratora = odjaveAdministratora;
    }
	
	
	
	
}
