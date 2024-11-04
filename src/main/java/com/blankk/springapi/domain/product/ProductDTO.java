package com.blankk.springapi.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(

        @NotBlank
        String name,

        @NotNull
        Float price
) { }
