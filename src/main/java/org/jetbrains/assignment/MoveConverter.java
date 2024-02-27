package org.jetbrains.assignment;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.assignment.Movement.Direction;

public class MoveConverter {

  public static List<Location> convertToLocation(List<Movement> movements) {
    int x = 0;
    int y = 0;

    List<Location> result = new ArrayList<>();
    result.add(new Location(0, 0));
    for (Movement movement : movements) {
      Direction direction = movement.direction();
      int steps = movement.steps();
      if (direction.equals(Direction.EAST)) {
        x += steps;
      } else if (direction.equals(Direction.NORTH)) {
        y += steps;
      } else if (direction.equals(Direction.SOUTH)) {
        y -= steps;
      } else if (direction.equals(Direction.WEST)) {
        x -= steps;
      }

      result.add(new Location(x, y));
    }

    return result;
  }

}
