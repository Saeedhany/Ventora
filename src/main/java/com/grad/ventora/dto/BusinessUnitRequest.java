
package com.grad.ventora.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BusinessUnitRequest(

        @NotBlank
        @Size(max = 150)
        String name,

        Long parentUnitId,

        @Size(max = 150)
        String location,

        @Size(max = 150)
        String headManager,

        @Size(max = 1000)
        String scope,

        Boolean active
) {}
