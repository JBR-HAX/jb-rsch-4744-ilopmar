package org.jetbrains.assignment;

import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends ListCrudRepository<MoveEntity, UUID> {

}
