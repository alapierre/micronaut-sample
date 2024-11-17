package dev.itrust.sample;

import dev.itrust.sample.fk.api.InvoiceApi;
import dev.itrust.sample.fk.api.model.*;
import io.micronaut.http.annotation.Controller;
import lombok.val;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 27.09.2024
 */
@Controller
public class InvoiceResource implements InvoiceApi {

//    @Override
//    public HttpResponse<@Valid InvoiceIdDto> createInvoice(CreateInvoiceRequest createInvoiceRequest) {
//        return HttpResponse.created(new InvoiceIdDto().docNumber("123").id(UUID.randomUUID()));
//    }

    @Override
    public InvoiceIdDto createInvoice(CreateInvoiceRequest createInvoiceRequest) {
        return new InvoiceIdDto().docNumber("123").id(UUID.randomUUID());
    }

    @Override
    public LoadInvoice200Response loadInvoice(UUID id) {

        val invoice = new CurrencyInvoiceDto("12345678", "EUR", BigDecimal.valueOf(4.75), LocalDate.now());
        invoice.setDocType(DocType.FW);

        return invoice;
    }

}
