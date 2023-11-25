package org.achumakin.interfaces;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.achumakin.entities.PaymentRecord;

public interface PaymentRepository extends PanacheRepositoryBase<PaymentRecord, String> {
}
