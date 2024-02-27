package org.jetbrains.assignment;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoveController {

  private final MoveService moveService;

  public MoveController(MoveService moveService) {
    this.moveService = moveService;
  }

  @PostMapping("/locations")
  List<Location> locations(@RequestBody List<Movement> movements) {
    return moveService.saveMovements(movements);
  }

  @PostMapping("/moves")
  List<Movement> movements(@RequestBody List<Location> locations) {
    return moveService.saveLocations(locations);
  }

}
