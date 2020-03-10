package atdd.path.web;

import atdd.path.application.dto.LineResponseView;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

public class LineHttpTest {
    public WebTestClient webTestClient;

    public LineHttpTest(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    public LineResponseView create(String inputJson) throws Exception {
        return webTestClient.post().uri("/lines")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(inputJson), String.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists("Location")
                .expectBody(LineResponseView.class)
                .returnResult()
                .getResponseBody();
    }
}