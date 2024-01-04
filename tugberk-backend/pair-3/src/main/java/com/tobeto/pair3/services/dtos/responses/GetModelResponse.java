package com.tobeto.pair3.services.dtos.responses;
import com.tobeto.pair3.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelResponse {
    private Integer id;
    private String name;
    private Integer brandId;
    public GetModelResponse(Model model) {
        this.id= model.getId();
        this.name= model.getName();
        this.brandId= model.getBrand().getId();

    }


}
