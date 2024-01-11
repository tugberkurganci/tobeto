package com.tobeto.pair3.services.abstracts;

import com.tobeto.pair3.services.dtos.requests.CreateRentalRequest;
import com.tobeto.pair3.services.dtos.requests.UpdateRentalRequest;
import com.tobeto.pair3.services.dtos.responses.GetInvoiceResponse;
import com.tobeto.pair3.services.dtos.responses.GetRentalResponse;
import com.tobeto.pair3.services.dtos.responses.RentalResponse;

import java.util.List;

public interface RentalService {
    List<GetRentalResponse> getAll();

    GetRentalResponse getById(int id);

    GetInvoiceResponse add(CreateRentalRequest createRentalRequest);

    void update(UpdateRentalRequest updateRentalRequest);

    void delete(int id);

    List<RentalResponse> getRentalsByUserId(int id);

    void updateStatusToOverdue();

    void cancelRentalIfBeforeRentalIsOverDueAndChangeCar();
}
