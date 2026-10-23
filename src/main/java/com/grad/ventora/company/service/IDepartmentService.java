
package com.grad.ventora.company.service;

import com.grad.ventora.dto.DepartmentRequest;
import com.grad.ventora.dto.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {

    DepartmentResponse createDepartment(
            Long companyId,
            DepartmentRequest request
    );

    List<DepartmentResponse> getDepartments(Long companyId);

    DepartmentResponse updateDepartment(
            Long companyId,
            Long departmentId,
            DepartmentRequest request
    );

    void deleteDepartment(Long companyId, Long departmentId);
}
