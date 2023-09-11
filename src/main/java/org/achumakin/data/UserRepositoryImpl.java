package org.achumakin.data;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.achumakin.entities.PaymentRecord;
import org.achumakin.entities.UserRecord;
import org.achumakin.interfaces.PaymentRepository;
import org.achumakin.interfaces.UserRepository;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserRecord, Long> {
}
