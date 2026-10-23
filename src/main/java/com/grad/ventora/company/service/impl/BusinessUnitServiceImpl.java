
package com.grad.ventora.company.service.impl;

import com.grad.ventora.company.service.IBusinessUnitService;
import com.grad.ventora.dto.BusinessUnitRequest;
import com.grad.ventora.dto.BusinessUnitResponse;
import com.grad.ventora.entity.BusinessUnit;
import com.grad.ventora.exception.exceptions.ResourceNotFoundException;
import com.grad.ventora.repository.BusinessUnitRepository;
import com.grad.ventora.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BusinessUnitServiceImpl implements IBusinessUnitService {

    private final BusinessUnitRepository businessUnitRepository;
    private final CompanyRepository companyRepository;

    @Override
    public BusinessUnitResponse createBusinessUnit(
            Long companyId,
            BusinessUnitRequest request
    ) {
        validateCompany(companyId);
        validateParentUnit(companyId, request.parentUnitId());

        BusinessUnit unit = new BusinessUnit();
        unit.setCompanyId(companyId);

        applyRequest(unit, request);

        return toResponse(businessUnitRepository.save(unit));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusinessUnitResponse> getBusinessUnits(Long companyId) {
        validateCompany(companyId);

        return businessUnitRepository.findByCompanyId(companyId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public BusinessUnitResponse updateBusinessUnit(
            Long companyId,
            Long unitId,
            BusinessUnitRequest request
    ) {
        validateCompany(companyId);

        BusinessUnit unit = businessUnitRepository.findById(unitId)
                .filter(existing -> existing.getCompanyId().equals(companyId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Business unit not found with id: " + unitId
                ));

        validateParentUnit(companyId, request.parentUnitId());

        if (unitId.equals(request.parentUnitId())) {
            throw new IllegalArgumentException(
                    "A business unit cannot be its own parent."
            );
        }

        applyRequest(unit, request);

        return toResponse(businessUnitRepository.save(unit));
    }

    @Override
    public void deleteBusinessUnit(Long companyId, Long unitId) {
        validateCompany(companyId);

        BusinessUnit unit = businessUnitRepository.findById(unitId)
                .filter(existing -> existing.getCompanyId().equals(companyId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Business unit not found with id: " + unitId
                ));

        businessUnitRepository.delete(unit);
    }

    private void validateCompany(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException(
                    "Company not found with id: " + companyId
            );
        }
    }

    private void validateParentUnit(Long companyId, Long parentUnitId) {
        if (parentUnitId == null) {
            return;
        }

        BusinessUnit parent = businessUnitRepository.findById(parentUnitId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Parent business unit not found with id: " + parentUnitId
                ));

        if (!parent.getCompanyId().equals(companyId)) {
            throw new IllegalArgumentException(
                    "Parent business unit must belong to the same company."
            );
        }
    }

    private void applyRequest(
            BusinessUnit unit,
            BusinessUnitRequest request
    ) {
        unit.setName(request.name());
        unit.setParentUnitId(request.parentUnitId());
        unit.setLocation(request.location());
        unit.setHeadManager(request.headManager());
        unit.setScope(request.scope());

        if (request.active() != null) {
            unit.setActive(request.active());
        }
    }

    private BusinessUnitResponse toResponse(BusinessUnit unit) {
        return new BusinessUnitResponse(
                unit.getId(),
                unit.getCompanyId(),
                unit.getName(),
                unit.getParentUnitId(),
                unit.getLocation(),
                unit.getHeadManager(),
                unit.getScope(),
                unit.isActive(),
                unit.getCreatedAt(),
                unit.getUpdatedAt()
        );
    }
}
