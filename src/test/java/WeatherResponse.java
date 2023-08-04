import com.google.gson.annotations.SerializedName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
public class WeatherResponse {
    private Location location;
    private CurrentWeather current;

    public Location getLocation() {
        return location;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public static class Location {
        private String name;
        private String region;
        private String country;
        private double lat;
        private double lon;
        private String tz_id;
        private long localtime_epoch;
        private String localtime;

        // Getters for location properties (if needed)
    }

    public static class CurrentWeather {
        @SerializedName("temp_c")
        private double tempC;

        @SerializedName("temp_f")
        private double tempF;

        public double getTempC() {
            return tempC; // Return the actual value of tempC
        }
        public double getTempF() {
            return tempF; // Return the actual value of tempF
        }

        // Getters for current weather properties (if needed)
    }

    public void writeValuesToXML(double tempC, double tempF) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Create the root element
            Element rootElement = doc.createElement("WeatherData");
            doc.appendChild(rootElement);

            // Create temperature elements
            Element tempCElement = doc.createElement("TemperatureCelsius");
            tempCElement.appendChild(doc.createTextNode(String.valueOf(tempC)));
            rootElement.appendChild(tempCElement);

            Element tempFElement = doc.createElement("TemperatureFahrenheit");
            tempFElement.appendChild(doc.createTextNode(String.valueOf(tempF)));
            rootElement.appendChild(tempFElement);

            // Write the XML to a file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("weather_data.xml"));
            transformer.transform(source, result);

            System.out.println("Values written to XML successfully.");

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

