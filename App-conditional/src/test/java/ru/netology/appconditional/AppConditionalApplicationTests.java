package ru.netology.appconditional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Container
	public static final GenericContainer<?> devapp = new GenericContainer<>("devapp")
			.withExposedPorts(8080);

	@Container
	public static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
			.withExposedPorts(8081);

	@Autowired
	TestRestTemplate restTemplate;

	final String ENDPOINT = "/profile";

	@Test
	void contextLoadsDevApp() {
		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080) + ENDPOINT, String.class);

		assertEquals(forEntity.getBody(), "Current profile is dev");
	}

	@Test
	void contextLoadsProdApp() {
		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8081) + ENDPOINT, String.class);

		assertEquals(forEntity.getBody(), "Current profile is production");
	}
}