
package com.grad.ventora.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepartmentRequest(

        @NotBlank
        @Size(max = 150)
        String name,

        @NotNull
        Long businessUnitId,

        @Size(max = 150)
        String manager,

        @NotNull
        @Min(0)
        Integer teamCount,

        Boolean active
) {}
