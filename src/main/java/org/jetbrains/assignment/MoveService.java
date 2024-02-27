package org.jetbrains.assignment;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class MoveService {

  private final MoveRepository moveRepository;

  public MoveService(MoveRepository moveRepository) {
    this.moveRepository = moveRepository;
  }

  public List<Location> saveMovements(List<Movement> movements) {
    List<Location> locations = MoveConverter.convertToLocation(movements);

    var moveEntity = new MoveEntity(UUID.randomUUID(), movements, locations);
    moveRepository.save(moveEntity);

    return locations;
  }

}
