package org.jetbrains.assignment;

public record Movement(Direction direction, int steps) {

  public enum Direction {
    NORTH, SOUTH, EAST, WEST
  }

}
