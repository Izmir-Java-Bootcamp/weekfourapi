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
    @NotBlank
    private String name;
    @NotNull
    private String description;
    @Min(1)
    private double price;
}
