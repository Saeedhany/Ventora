
package com.grad.ventora.company.controller;

import com.grad.ventora.company.service.IDepartmentService;
import com.grad.ventora.dto.DepartmentRequest;
import com.grad.ventora.dto.DepartmentResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final IDepartmentService departmentService;

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse create(
            @PathVariable Long companyId,
            @Valid @RequestBody DepartmentRequest request
    ) {
        return departmentService.createDepartment(companyId, request);
    }

    @GetMapping("/admin")
    public List<DepartmentResponse> getAll(
            @PathVariable Long companyId
    ) {
        return departmentService.getDepartments(companyId);
    }

    @PutMapping("/{departmentId}/admin")
    public DepartmentResponse update(
            @PathVariable Long companyId,
            @PathVariable Long departmentId,
            @Valid @RequestBody DepartmentRequest request
    ) {
        return departmentService.updateDepartment(
                companyId, departmentId, request
        );
    }

    @DeleteMapping("/{departmentId}/admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long companyId,
            @PathVariable Long departmentId
    ) {
        departmentService.deleteDepartment(companyId, departmentId);
    }
}
