package dev.itrust.sample.repository;

import dev.itrust.sample.model.InvoiceItem;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 9.09.2024
 */
@Repository
public interface InvoiceItemRepository extends CrudRepository<InvoiceItem, Long> {
}
