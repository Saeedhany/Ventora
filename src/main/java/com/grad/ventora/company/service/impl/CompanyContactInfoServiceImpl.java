
package com.grad.ventora.company.service.impl;

import com.grad.ventora.company.service.ICompanyContactInfoService;
import com.grad.ventora.dto.CompanyContactInfoRequest;
import com.grad.ventora.dto.CompanyContactInfoResponse;
import com.grad.ventora.entity.Company;
import com.grad.ventora.entity.CompanyContactInfo;
import com.grad.ventora.exception.exceptions.ResourceNotFoundException;
import com.grad.ventora.repository.CompanyContactInfoRepository;
import com.grad.ventora.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyContactInfoServiceImpl
        implements ICompanyContactInfoService {

    private final CompanyContactInfoRepository contactInfoRepository;
    private final CompanyRepository companyRepository;

    @Override
    @Transactional(readOnly = true)
    public CompanyContactInfoResponse getContactInfo(Long companyId) {
        Company company = getCompany(companyId);

        CompanyContactInfo contactInfo = contactInfoRepository
                .findByCompanyId(companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contact information not found for company: " + companyId
                ));

        return toResponse(contactInfo, company);
    }

    @Override
    public CompanyContactInfoResponse saveContactInfo(
            Long companyId,
            CompanyContactInfoRequest request
    ) {
        Company company = getCompany(companyId);

        if (contactInfoRepository.existsByCompanyId(companyId)) {
            throw new IllegalStateException(
                    "Contact information already exists for this company"
            );
        }

        CompanyContactInfo contactInfo = new CompanyContactInfo();
        applyRequest(contactInfo, request);

        contactInfo.setCompanyId(companyId);

        CompanyContactInfo saved = contactInfoRepository.save(contactInfo);

        return toResponse(saved, company);
    }

    @Override
    public CompanyContactInfoResponse updateContactInfo(
            Long companyId,
            CompanyContactInfoRequest request
    ) {
        Company company = getCompany(companyId);

        CompanyContactInfo contactInfo = contactInfoRepository
                .findByCompanyId(companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contact information not found for company: " + companyId
                ));

        applyRequest(contactInfo, request);

        CompanyContactInfo updated = contactInfoRepository.save(contactInfo);

        return toResponse(updated, company);
    }

    private Company getCompany(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Company not found with id: " + companyId
                ));
    }

    private void applyRequest(
            CompanyContactInfo contactInfo,
            CompanyContactInfoRequest request
    ) {
        contactInfo.setOfficialEmail(request.officialEmail());
        contactInfo.setSecondaryEmail(request.secondaryEmail());
        contactInfo.setContactPerson(request.contactPerson());
        contactInfo.setContactTitle(request.contactTitle());
        contactInfo.setPreferredChannel(request.preferredChannel());
        contactInfo.setPhoneNumber(request.phoneNumber());
        contactInfo.setFaxNumber(request.faxNumber());
    }

    private CompanyContactInfoResponse toResponse(
            CompanyContactInfo contactInfo,
            Company company
    ) {
        return new CompanyContactInfoResponse(
                contactInfo.getId(),
                contactInfo.getCompanyId(),
                contactInfo.getOfficialEmail(),
                contactInfo.getSecondaryEmail(),
                contactInfo.getContactPerson(),
                contactInfo.getContactTitle(),
                contactInfo.getPreferredChannel(),
                contactInfo.getPhoneNumber(),
                contactInfo.getFaxNumber(),
                company.getWebsite(),
                contactInfo.getCreatedAt(),
                contactInfo.getUpdatedAt()
        );
    }
}
