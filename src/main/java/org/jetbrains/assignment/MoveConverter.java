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

  public static List<Movement> convertToMovement(List<Location> locations) {
    List<Movement> result = new ArrayList<>();
    for (int i = 0; i < locations.size() - 1; i++) {
      Location locationStart = locations.get(i);
      Location locationEnd = locations.get(i + 1);

      Direction direction = null;

      int x2 = locationEnd.x();
      int x1 = locationStart.x();
      int y2 = locationEnd.y();
      int y1 = locationStart.y();
      int steps = 0;

      if (x2 > x1) {
        direction = Direction.EAST;
        steps = x2 - x1;
      } else if (y2 > y1) {
        direction = Direction.NORTH;
        steps = y2 - y1;
      } else if (y2 < y1) {
        direction = Direction.SOUTH;
        steps = y1 - y2;
      } else if (x2 < x1) {
        direction = Direction.WEST;
        steps = x1 - x2;
      }

      result.add(new Movement(direction, steps));
    }

    return result;
  }

}
