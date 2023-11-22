package com.tobeto.a.spring.intro.services.models.requests;

import lombok.Data;

@Data
public class UpdateCarSupplierRequest {
    private int id;
    private String name;
    private String location;

}
