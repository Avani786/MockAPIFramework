import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import java.io.File;
import java.io.IOException;

public class MockApplication {


    public static void main(String[] args) throws IOException {
        CreateWireMockStub createWireMockStub = new CreateWireMockStub();
        File requestFile = new File("src/test/mappings/request.json");
        File responseFile = new File("src/test/mappings/response.json");

        WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080));  // Start the server on port 8080
        wireMockServer.start();

        // Configure WireMock for localhost:8080
        WireMock.configureFor("localhost", 8080);


        // Create WireMock stub mapping using loaded JSON
        createWireMockStub.createStubFromFiles(requestFile, responseFile);
        // Keep the server running for demonstration
        System.out.println("WireMock server is running...");
    }



}
