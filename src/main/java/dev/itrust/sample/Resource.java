package dev.itrust.sample;

import dev.itrust.sample.model.CustomerData;
import dev.itrust.sample.model.VersionedId2;
import dev.itrust.sample.repository.CustomerDataRepository;
import dev.itrust.sample.repository.InvoiceItemRepository;
import dev.itrust.sample.repository.InvoiceRepository;
import dev.itrust.sample.repository.SampleEntityRepository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 9.09.2024
 */
@Controller("/sample")
@RequiredArgsConstructor
public class Resource {

    private final InvoiceItemRepository invoiceItemRepository;
    private final InvoiceRepository invoiceRepository;
    private final SampleEntityRepository sampleEntityRepository;

    private final CustomerDataRepository customerDataRepository;

    @Get
    void test() {
        invoiceRepository.findById(1L);
    }


    @Get("/customer/{id}/{version}")
    Optional<CustomerData> test2(UUID id, long version) {
        val tmp = customerDataRepository.findById(new VersionedId2(id, version));
        System.out.println(tmp);
        return tmp;
    }

    @Get("/customer")
    List<CustomerData> listCustomers() {
        return customerDataRepository.findAll();
    }

    @Get("/sampleEntity")
    void sampleEntityTest() {
        Sort.Order sortOrder = Sort.Order.desc("createdAt");
        Sort sort = Sort.of(sortOrder);

        Pageable pageableWithSort = Pageable.from(0, 100, sort);
        Pageable pageableWithoutSort = Pageable.from(0, 100);

        // without sort - working
        sampleEntityRepository.findByCreatedAtBetween(
                LocalDateTime.of(LocalDate.of(2025, 1, 1), LocalTime.MIN),
                LocalDateTime.of(LocalDate.of(2025, 12, 31), LocalTime.MAX),
                pageableWithoutSort);

        // with sort - does not working
        sampleEntityRepository.findByCreatedAtBetween(
                LocalDateTime.of(LocalDate.of(2025, 1, 1), LocalTime.MIN),
                LocalDateTime.of(LocalDate.of(2025, 12, 31), LocalTime.MAX),
                pageableWithSort);

    }

}
