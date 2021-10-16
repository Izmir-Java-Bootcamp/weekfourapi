package com.kodluyoruz.weekfourapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateProductRequest {
    @NotBlank(message = "name boş olamaz")
    private String name;
    @NotNull(message = "description null olamaz")
    private String description;
    @Min(value = 1,message = "price 1 den küçük olamaz")
    private double price;
}
