
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@SpringBootApplication
@AutoConfigureWireMock(port = 7080 )
@PropertySource("src/main/resources/application.properties")
public class MockAPIApplication {
    @Value("${server.port}")
    private int port;
    @Value("${server.host}")
    private String host;
    public static void main(String[] args) {
        SpringApplication.run(MockAPIApplication.class, args);
    }


    @Autowired
    public WireMockConfiguration wireMockOptions() throws IOException {

        final WireMockConfiguration options = WireMockSpring.options();
        options.port(port);
        options.bindAddress(host);
        options.usingFilesUnderDirectory("");
        return options;
    }
}
