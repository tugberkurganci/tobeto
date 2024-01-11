package com.tobeto.pair3.services.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tobeto.pair3.entities.Invoice;
import com.tobeto.pair3.entities.Rental;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GetInvoiceResponse {

    private int rentalId;

    private LocalDate createDate;

    private BigDecimal totalPrice;


    public GetInvoiceResponse(Invoice save) {
        this.rentalId=save.getRental().getId();
        this.createDate=save.getCreateDate();
        this.totalPrice=save.getRental().getTotalPrice();
    }
}
