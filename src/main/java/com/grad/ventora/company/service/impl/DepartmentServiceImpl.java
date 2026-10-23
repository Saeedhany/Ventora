
package com.grad.ventora.company.service.impl;

import com.grad.ventora.company.service.IDepartmentService;
import com.grad.ventora.dto.DepartmentRequest;
import com.grad.ventora.dto.DepartmentResponse;
import com.grad.ventora.entity.BusinessUnit;
import com.grad.ventora.entity.Department;
import com.grad.ventora.exception.exceptions.ResourceNotFoundException;
import com.grad.ventora.repository.BusinessUnitRepository;
import com.grad.ventora.repository.CompanyRepository;
import com.grad.ventora.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements IDepartmentService {

    private final DepartmentRepository departmentRepository;
    private final BusinessUnitRepository businessUnitRepository;
    private final CompanyRepository companyRepository;

    @Override
    public DepartmentResponse createDepartment(
            Long companyId,
            DepartmentRequest request
    ) {
        validateCompany(companyId);
        validateBusinessUnit(companyId, request.businessUnitId());

        Department department = new Department();
        department.setCompanyId(companyId);

        applyRequest(department, request);

        return toResponse(departmentRepository.save(department));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getDepartments(Long companyId) {
        validateCompany(companyId);

        return departmentRepository.findByCompanyId(companyId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public DepartmentResponse updateDepartment(
            Long companyId,
            Long departmentId,
            DepartmentRequest request
    ) {
        validateCompany(companyId);

        Department department = departmentRepository.findById(departmentId)
                .filter(existing ->
                        existing.getCompanyId().equals(companyId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + departmentId
                ));

        validateBusinessUnit(companyId, request.businessUnitId());

        applyRequest(department, request);

        return toResponse(departmentRepository.save(department));
    }

    @Override
    public void deleteDepartment(Long companyId, Long departmentId) {
        validateCompany(companyId);

        Department department = departmentRepository.findById(departmentId)
                .filter(existing ->
                        existing.getCompanyId().equals(companyId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + departmentId
                ));

        departmentRepository.delete(department);
    }

    private void validateCompany(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException(
                    "Company not found with id: " + companyId
            );
        }
    }

    private void validateBusinessUnit(
            Long companyId,
            Long businessUnitId
    ) {
        BusinessUnit unit = businessUnitRepository.findById(businessUnitId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Business unit not found with id: " + businessUnitId
                ));

        if (!unit.getCompanyId().equals(companyId)) {
            throw new IllegalArgumentException(
                    "Business unit must belong to the specified company."
            );
        }
    }

    private void applyRequest(
            Department department,
            DepartmentRequest request
    ) {
        department.setName(request.name());
        department.setBusinessUnitId(request.businessUnitId());
        department.setManager(request.manager());
        department.setTeamCount(request.teamCount());

        if (request.active() != null) {
            department.setActive(request.active());
        }
    }

    private DepartmentResponse toResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getCompanyId(),
                department.getBusinessUnitId(),
                department.getName(),
                department.getManager(),
                department.getTeamCount(),
                department.isActive(),
                department.getCreatedAt(),
                department.getUpdatedAt()
        );
    }
}
