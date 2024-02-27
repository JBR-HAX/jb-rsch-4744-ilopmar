package org.jetbrains.assignment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jetbrains.assignment.Movement.Direction.EAST;
import static org.jetbrains.assignment.Movement.Direction.NORTH;
import static org.jetbrains.assignment.Movement.Direction.SOUTH;
import static org.jetbrains.assignment.Movement.Direction.WEST;

import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
class MoveControllerTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(
      DockerImageName.parse("postgres:16.1")
  );

  @LocalServerPort
  private int port;

  private RestClient restClient;

  @BeforeEach
  void init() {
    restClient = RestClient.create("http://localhost:%s".formatted(port));
  }

  @Test
  void testLocations() {
    ResponseEntity<List<Location>> response = restClient.post()
        .uri("/locations")
        .body(movements())
        .contentType(MediaType.APPLICATION_JSON)
        .retrieve()
        .toEntity(new ParameterizedTypeReference<>() {
        });

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody())
        .hasSize(6)
        .extracting(Location::x, Location::y)
        .containsExactly(
            Tuple.tuple(0, 0),
            Tuple.tuple(1, 0),
            Tuple.tuple(1, 3),
            Tuple.tuple(4, 3),
            Tuple.tuple(4, -2),
            Tuple.tuple(2, -2)
        );
  }

  @Test
  void testMoves() {
    ResponseEntity<List<Movement>> response = restClient.post()
        .uri("/moves")
        .body(locations())
        .contentType(MediaType.APPLICATION_JSON)
        .retrieve()
        .toEntity(new ParameterizedTypeReference<>() {
        });

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody())
        .hasSize(4)
        .extracting(Movement::direction, Movement::steps)
        .containsExactly(
            Tuple.tuple(EAST, 1),
            Tuple.tuple(NORTH, 3),
            Tuple.tuple(WEST, 1),
            Tuple.tuple(SOUTH, 3)
        );
  }

  private List<Movement> movements() {
    return List.of(
        new Movement(EAST, 1),
        new Movement(NORTH, 3),
        new Movement(EAST, 3),
        new Movement(SOUTH, 5),
        new Movement(WEST, 2)
    );
  }

  private List<Location> locations() {
    return List.of(
        new Location(0, 0),
        new Location(1, 0),
        new Location(1, 3),
        new Location(0, 3),
        new Location(0, 0)
    );
  }

}