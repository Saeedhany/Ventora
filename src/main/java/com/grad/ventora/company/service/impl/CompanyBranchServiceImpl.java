
package com.grad.ventora.company.service.impl;

import com.grad.ventora.company.service.ICompanyBranchService;
import com.grad.ventora.dto.CompanyBranchRequest;
import com.grad.ventora.dto.CompanyBranchResponse;
import com.grad.ventora.entity.Company;
import com.grad.ventora.entity.CompanyBranch;
import com.grad.ventora.exception.exceptions.ResourceNotFoundException;
import com.grad.ventora.repository.CompanyBranchRepository;
import com.grad.ventora.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyBranchServiceImpl implements ICompanyBranchService {

    private final CompanyBranchRepository branchRepository;
    private final CompanyRepository companyRepository;

    @Override
    public CompanyBranchResponse createBranch(
            Long companyId,
            CompanyBranchRequest request) {

        Company company = getCompany(companyId);

        validateHeadquarters(companyId, request.headquarters(), null);

        CompanyBranch branch = new CompanyBranch();
        branch.setCompanyId(companyId);

        applyRequest(branch, request);

        branchRepository.save(branch);

        return toResponse(branch, company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyBranchResponse> getBranches(Long companyId) {

        Company company = getCompany(companyId);

        return branchRepository.findByCompanyId(companyId)
                .stream()
                .map(branch -> toResponse(branch, company))
                .toList();
    }

    @Override
    public CompanyBranchResponse updateBranch(
            Long companyId,
            Long branchId,
            CompanyBranchRequest request) {

        Company company = getCompany(companyId);

        CompanyBranch branch = branchRepository
                .findByIdAndCompanyId(branchId, companyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Branch not found with id: " + branchId));

        validateHeadquarters(
                companyId,
                request.headquarters(),
                branchId);

        applyRequest(branch, request);

        branchRepository.save(branch);

        return toResponse(branch, company);
    }

    @Override
    public void deleteBranch(Long companyId, Long branchId) {

        getCompany(companyId);

        CompanyBranch branch = branchRepository
                .findByIdAndCompanyId(branchId, companyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Branch not found with id: " + branchId));

        if (branch.isHeadquarters()) {
            throw new IllegalStateException(
                    "The headquarters cannot be deleted.");
        }

        branchRepository.delete(branch);
    }

    private Company getCompany(Long companyId) {

        return companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Company not found with id: " + companyId));
    }

    private void validateHeadquarters(
            Long companyId,
            boolean headquarters,
            Long currentBranchId) {

        if (!headquarters) {
            return;
        }

        boolean exists = branchRepository
                .existsByCompanyIdAndHeadquartersTrue(companyId);

        if (!exists) {
            return;
        }

        if (currentBranchId != null) {
            CompanyBranch existingHeadquarters = branchRepository
                    .findByCompanyId(companyId)
                    .stream()
                    .filter(CompanyBranch::isHeadquarters)
                    .findFirst()
                    .orElse(null);

            if (existingHeadquarters != null
                    && existingHeadquarters.getId().equals(currentBranchId)) {
                return;
            }
        }

        throw new IllegalStateException(
                "This company already has a headquarters.");
    }

    private void applyRequest(
            CompanyBranch branch,
            CompanyBranchRequest request) {

        branch.setName(request.name());
        branch.setHeadquarters(request.headquarters());
        branch.setAddressLine(request.addressLine());
        branch.setCity(request.city());
        branch.setStateOrRegion(request.stateOrRegion());
        branch.setPostalCode(request.postalCode());
        branch.setCountry(request.country());
        branch.setContactName(request.contactName());
        branch.setContactTitle(request.contactTitle());
        branch.setEmail(request.email());
        branch.setPhoneNumber(request.phoneNumber());
    }

    private CompanyBranchResponse toResponse(
            CompanyBranch branch,
            Company company) {

        return new CompanyBranchResponse(
                branch.getId(),
                branch.getCompanyId(),
                branch.getName(),
                branch.isHeadquarters(),
                branch.getAddressLine(),
                branch.getCity(),
                branch.getStateOrRegion(),
                branch.getPostalCode(),
                branch.getCountry(),
                branch.getContactName(),
                branch.getContactTitle(),
                branch.getEmail(),
                branch.getPhoneNumber(),
                branch.isActive(),
                branch.getCreatedAt(),
                branch.getUpdatedAt()
        );
    }
}
