
package com.grad.ventora.company.controller;

import com.grad.ventora.company.service.IBusinessUnitService;
import com.grad.ventora.dto.BusinessUnitRequest;
import com.grad.ventora.dto.BusinessUnitResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/business-units")
@RequiredArgsConstructor
public class BusinessUnitController {

    private final IBusinessUnitService businessUnitService;

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessUnitResponse create(
            @PathVariable Long companyId,
            @Valid @RequestBody BusinessUnitRequest request
    ) {
        return businessUnitService.createBusinessUnit(companyId, request);
    }

    @GetMapping("/admin")
    public List<BusinessUnitResponse> getAll(
            @PathVariable Long companyId
    ) {
        return businessUnitService.getBusinessUnits(companyId);
    }

    @PutMapping("/{unitId}/admin")
    public BusinessUnitResponse update(
            @PathVariable Long companyId,
            @PathVariable Long unitId,
            @Valid @RequestBody BusinessUnitRequest request
    ) {
        return businessUnitService.updateBusinessUnit(
                companyId, unitId, request
        );
    }

    @DeleteMapping("/{unitId}/admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long companyId,
            @PathVariable Long unitId
    ) {
        businessUnitService.deleteBusinessUnit(companyId, unitId);
    }
}
