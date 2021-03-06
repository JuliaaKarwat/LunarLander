import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
/**
 * Klasa odpowiedzialna za wczytywanie plikow konfiguracyjnych
 */
public class FileParser {
    static int port;
    /**
     * Odczytuje z pliku konfiguracyjnego numer portu
     */
    static void loadPort() throws IOException {
        InputStream propertiesFile = new FileInputStream("doc/server/Config.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        port = Integer.parseInt(properties.getProperty("port"));
    }
    /**
     * Odczytuje z pliku konfiguracyjnego min. wymiary okna, ilosc poziomow i pakuje je do jednej lini tekstu. Wszystkie parametry
     * odseparowane są myslnikiem
     * @return linia tekstu która zawiera wszystkie dane konfiguracyjne
     */
    static String loadConfig() throws IOException {
        InputStream propertiesFile = new FileInputStream("doc/server/Config.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        String response = "";
        for(int i=0; i<9 ;i++){
            if (i!=8) response += (properties.getProperty("param" + (i+1)) + "-");
            else response += (properties.getProperty("param" + (i+1)));
        }
        propertiesFile.close();
        return response;
    }

    /**
     * Na zadanie klienta wysyła dane konfiguracyjne wybranego poziomu
     * @param levelIndex numer poziomu który chcemy uzyskać
     * @return linia tekstu na podstawie której klient jest w stanie odczytać wygląd planszy
     */
    static String loadLevelConfigs(int levelIndex) throws IOException {
        InputStream propertiesFile2 = new FileInputStream("doc/server/Maps.txt");
        Properties mapProperties = new Properties();
        mapProperties.load(propertiesFile2);
        int[] xPoints = Arrays.stream(mapProperties.getProperty("xpoints" + levelIndex).split("-")).mapToInt(Integer::parseInt).toArray();
        int[] yPoints = Arrays.stream(mapProperties.getProperty("ypoints" + levelIndex).split("-")).mapToInt(Integer::parseInt).toArray();
        int[] xLandingField = Arrays.stream(mapProperties.getProperty("xlanding" + levelIndex).split("-")).mapToInt(Integer::parseInt).toArray();
        int[] yLandingField = Arrays.stream(mapProperties.getProperty("ylanding" + levelIndex).split("-")).mapToInt(Integer::parseInt).toArray();
        int startPoint = Integer.parseInt(mapProperties.getProperty("startPoint" + levelIndex));
        return (Arrays.toString(xPoints) + "-" + Arrays.toString(yPoints) + "-" + Arrays.toString(xLandingField) + "-" + Arrays.toString(yLandingField) + "-" + startPoint)
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
    }
}
