import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.github.cdimascio.dotenv.Dotenv;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ampolApiTest1 {

    @Test
    public void GetSydneyCurrentWeatherDetailsAndWriteToXMLFile() {
        Dotenv dotenv = Dotenv.configure().load();
        final String BASE_URL = dotenv.get("BASE_URL");
        String API_KEY = null;

        // Load API key from secrets.properties
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("secrets.properties")) {
            Properties prop = new Properties();
            if (input != null) {
                prop.load(input);
                API_KEY = prop.getProperty("WEATHER_API_KEY");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (API_KEY == null) {
            throw new RuntimeException("API key not found");
        }

        String responseBody = given()
                .queryParam("key", API_KEY)
                .queryParam("q", "Sydney")
                .queryParam("aqi", "no")
                .when()
                .get(BASE_URL)
                .getBody().asString();

        System.out.println("API Response: " + responseBody);
        // Parse the JSON response using Gson and map it to the WeatherResponse class
        Gson gson = new Gson();
        WeatherResponse weatherResponse = gson.fromJson(responseBody, WeatherResponse.class);

        // Extract the values of temp_c and temp_f
        double tempC = weatherResponse.getCurrent().getTempC();
        double tempF = weatherResponse.getCurrent().getTempF();

        System.out.println("Temperature in Celsius: " + tempC);
        System.out.println("Temperature in Fahrenheit: " + tempF);
        // Write values to XML file
        weatherResponse.writeValuesToXML(tempC, tempF);
    }
}