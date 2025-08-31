import com.fasterxml.jackson.databind.JsonNode;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import java.io.File;
import java.io.IOException;

public class CreateWireMockStub {
    public void createStubFromFiles(File requestFile, File responseFile) throws IOException {

    LoadJsonFile loadJsonFile = new LoadJsonFile();
    JsonNode requestJson = loadJsonFile.loadJson(requestFile);
    JsonNode responseJson = loadJsonFile.loadJson(responseFile);

    // Extract request body from JSON
    String requestBody = requestJson.toString();  // Customize this if needed
    String responseBody = responseJson.toString();  // Customize this if needed

    // Create the WireMock stub
    StubMapping stubMapping = WireMock.stubFor(
            WireMock.post(WireMock.urlEqualTo(requestJson.get("url").asText()))  // Match the URL from request JSON
                    .withRequestBody(WireMock.equalToJson(requestBody))  // Match the body
                    .willReturn(
                            WireMock.aResponse()
                                    .withStatus(responseJson.get("status").asInt())  // Response status from JSON
                                    .withBody(responseBody)  // Response body from JSON
                    )
    );

    // Register the stub mapping
    WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.addStubMapping(stubMapping);

    // Print the stub to confirm
        System.out.println("Stub created: " + stubMapping);
}
}

