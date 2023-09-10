package org.achumakin.data;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.achumakin.entities.PaymentRecord;
import org.achumakin.interfaces.PaymentRepository;

@ApplicationScoped
public class PaymentRepositoryImpl implements PaymentRepository, PanacheRepositoryBase<PaymentRecord, Long> {
}
