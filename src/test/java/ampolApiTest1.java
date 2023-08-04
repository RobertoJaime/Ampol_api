import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;

public class ampolApiTest1 {

    @Test
    public void getResponseStatus() {
        final String BASE_URL = "https://api.weatherapi.com/v1/current.json";
        final String API_KEY = "d752d6efd45a4c458b611524231407";

        String responseBody = given()
                .queryParam("key", API_KEY)
                .queryParam("q", "Sydney")
                .queryParam("aqi", "no")
                .when()
                .get(BASE_URL)
                .getBody().asString();

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
        int statusCode = given()
                .queryParam("key", API_KEY)
                .queryParam("q", "Sydney")
                .queryParam("aqi", "no")
                .when()
                .get(BASE_URL)
                .getStatusCode();

        System.out.println("The response status is " + statusCode);

        assertEquals(200, statusCode, "Status code should be 200");
    }
}