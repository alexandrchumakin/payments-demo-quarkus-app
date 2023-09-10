package org.achumakin.interfaces;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.achumakin.entities.PaymentRecord;

public interface PaymentRepository extends PanacheRepository<PaymentRecord> {
}
