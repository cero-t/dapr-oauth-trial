package ninja.cero.example.dapr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    @Value("http://localhost:${dapr.http.port}/v1.0")
    private String baseUrl;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Dapr World";
    }

    @GetMapping("/metadata")
    public String metadata() {
        var res = restTemplate.getForEntity(baseUrl + "/metadata", String.class);
        System.out.println(res.getStatusCodeValue());
        return res.getBody();
    }
}
