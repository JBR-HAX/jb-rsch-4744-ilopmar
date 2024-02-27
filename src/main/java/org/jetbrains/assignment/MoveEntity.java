package org.jetbrains.assignment;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "robot_movement")
public class MoveEntity {

  @Id
  private UUID id;

  @Column(columnDefinition = "jsonb", nullable = false)
  @Type(JsonBinaryType.class)
  private List<Movement> movements;

  @Column(columnDefinition = "jsonb", nullable = false)
  @Type(JsonBinaryType.class)
  private List<Location> locations;

  public MoveEntity() {
  }

  public MoveEntity(UUID id, List<Movement> movements, List<Location> locations) {
    this.id = id;
    this.movements = movements;
    this.locations = locations;
  }

  public UUID getId() {
    return id;
  }

  public List<Movement> getMovements() {
    return movements;
  }

  public void setMovements(List<Movement> movements) {
    this.movements = movements;
  }

  public List<Location> getLocations() {
    return locations;
  }

  public void setLocations(List<Location> locations) {
    this.locations = locations;
  }

  @Override
  public String toString() {
    return "MoveEntity{" +
           "id=" + id +
           ", movements=" + movements +
           ", locations=" + locations +
           '}';
  }
}
