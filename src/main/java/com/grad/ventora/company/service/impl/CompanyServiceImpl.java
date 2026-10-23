
package com.grad.ventora.company.service.impl;

import com.grad.ventora.company.service.ICompanyService;
import com.grad.ventora.dto.CompanyProfileRequest;
import com.grad.ventora.dto.CompanyProfileResponse;
import com.grad.ventora.entity.Company;
import com.grad.ventora.entity.CompanyProfileStatus;
import com.grad.ventora.exception.exceptions.ResourceNotFoundException;
import com.grad.ventora.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyProfileResponse createDraft(CompanyProfileRequest request) {
        Company company = new Company();

        applyRequest(company, request);

        company.setProfileStatus(CompanyProfileStatus.DRAFT);

        Company savedCompany = companyRepository.save(company);

        return toResponse(savedCompany);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyProfileResponse getCompanyById(Long companyId) {
        Company company = findCompanyOrThrow(companyId);

        return toResponse(company);
    }

    @Override
    public CompanyProfileResponse updateDraft(
            Long companyId,
            CompanyProfileRequest request
    ) {
        Company company = findCompanyOrThrow(companyId);

        if (company.getProfileStatus() != CompanyProfileStatus.DRAFT) {
            throw new IllegalStateException(
                    "Only draft companies can be updated."
            );
        }

        applyRequest(company, request);

        Company updatedCompany = companyRepository.save(company);

        return toResponse(updatedCompany);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyProfileResponse> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private Company findCompanyOrThrow(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Company not found with id: " + companyId
                ));
    }

    private void applyRequest(
            Company company,
            CompanyProfileRequest request
    ) {
        if (request.companyName() != null) {
            company.setCompanyName(request.companyName());
        }

        if (request.legalName() != null) {
            company.setLegalName(request.legalName());
        }

        if (request.industry() != null) {
            company.setIndustry(request.industry());
        }

        if (request.companySize() != null) {
            company.setCompanySize(request.companySize());
        }

        if (request.foundedYear() != null) {
            company.setFoundedYear(request.foundedYear());
        }

        if (request.website() != null) {
            company.setWebsite(request.website());
        }

        if (request.headquartersCountry() != null) {
            company.setHeadquartersCountry(
                    request.headquartersCountry()
            );
        }

        if (request.headquartersCity() != null) {
            company.setHeadquartersCity(
                    request.headquartersCity()
            );
        }

        if (request.businessDescription() != null) {
            company.setBusinessDescription(
                    request.businessDescription()
            );
        }

        if (request.companyLogoUrl() != null) {
            company.setCompanyLogoUrl(request.companyLogoUrl());
        }

        if (request.legalEntityType() != null) {
            company.setLegalEntityType(request.legalEntityType());
        }

        if (request.countryOfIncorporation() != null) {
            company.setCountryOfIncorporation(
                    request.countryOfIncorporation()
            );
        }

        if (request.mainBusinessDomain() != null) {
            company.setMainBusinessDomain(
                    request.mainBusinessDomain()
            );
        }

        if (request.primaryBusinessModel() != null) {
            company.setPrimaryBusinessModel(
                    request.primaryBusinessModel()
            );

        }

        if (request.organizationModel() != null) {
            company.setOrganizationModel(request.organizationModel());
        }
    }

    private CompanyProfileResponse toResponse(Company company) {
        return new CompanyProfileResponse(
                company.getId(),
                company.getCompanyName(),
                company.getLegalName(),
                company.getIndustry(),
                company.getCompanySize(),
                company.getFoundedYear(),
                company.getWebsite(),
                company.getHeadquartersCountry(),
                company.getHeadquartersCity(),
                company.getBusinessDescription(),
                company.getCompanyLogoUrl(),
                company.getLegalEntityType(),
                company.getCountryOfIncorporation(),
                company.getMainBusinessDomain(),
                company.getPrimaryBusinessModel(),
                company.getProfileStatus(),
                company.getCreatedAt(),
                company.getUpdatedAt(),
                company.getOrganizationModel()
        );
    }
}
