package org.achumakin.interfaces;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.achumakin.entities.UserRecord;

public interface UserRepository extends PanacheRepository<UserRecord> {
}
