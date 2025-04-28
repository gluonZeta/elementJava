package gluon.projects;

import gluon.projects.exceptions.ElementProjectException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static String sendRequestWithCompleteUrl(String completeUrl) {
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(completeUrl))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> httpResponse = null;

        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new ElementProjectException(e);
        } catch (InterruptedException e) {
            throw new ElementProjectException(e);
        }
        return httpResponse.body();
    }

    public static Properties getPropertiesByFileName(String fileName) {
        Properties properties = new Properties();
        try(InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ElementProjectException(e);
        }
        return properties;
    }

}
