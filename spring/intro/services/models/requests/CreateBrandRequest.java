package com.tobeto.a.spring.intro.services.models.requests;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {

    @NotBlank
    @Length(min = 6)
    private String name;
}
