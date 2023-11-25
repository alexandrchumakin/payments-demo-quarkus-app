package org.achumakin.data;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.achumakin.entities.PaymentRecord;

import java.util.UUID;

@ApplicationScoped
public class PaymentRepositoryImpl implements PanacheRepositoryBase<PaymentRecord, UUID> {
}
