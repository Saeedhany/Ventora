
package com.grad.ventora.company.service.impl;

import com.grad.ventora.company.service.IManagementTeamMemberService;
import com.grad.ventora.dto.ManagementTeamMemberRequest;
import com.grad.ventora.dto.ManagementTeamMemberResponse;
import com.grad.ventora.entity.Company;
import com.grad.ventora.entity.ManagementMemberType;
import com.grad.ventora.entity.ManagementTeamMember;
import com.grad.ventora.exception.exceptions.ResourceNotFoundException;
import com.grad.ventora.repository.CompanyRepository;
import com.grad.ventora.repository.ManagementTeamMemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagementTeamMemberServiceImpl
        implements IManagementTeamMemberService {

    private final ManagementTeamMemberRepository memberRepository;
    private final CompanyRepository companyRepository;

    @Override
    public ManagementTeamMemberResponse createMember(
            Long companyId,
            ManagementTeamMemberRequest request
    ) {
        validateCompany(companyId);
        validateMemberData(request);

        ManagementTeamMember member = new ManagementTeamMember();
        member.setCompanyId(companyId);

        applyRequest(member, request);

        return toResponse(memberRepository.save(member));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ManagementTeamMemberResponse> getMembers(
            Long companyId,
            String memberType
    ) {
        validateCompany(companyId);

        if (memberType == null || memberType.isBlank()) {
            return memberRepository.findByCompanyId(companyId)
                    .stream()
                    .map(this::toResponse)
                    .toList();
        }

        ManagementMemberType type;

        try {
            type = ManagementMemberType.valueOf(
                    memberType.trim().toUpperCase()
            );
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Invalid memberType. Allowed values: "
                            + "EXECUTIVE_LEADERSHIP, DEPARTMENT_MANAGER, "
                            + "OWNER_SHAREHOLDER, BOARD_MEMBER."
            );
        }

        return memberRepository
                .findByCompanyIdAndMemberType(companyId, type)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ManagementTeamMemberResponse updateMember(
            Long companyId,
            Long memberId,
            ManagementTeamMemberRequest request
    ) {
        validateCompany(companyId);

        ManagementTeamMember member = memberRepository.findById(memberId)
                .filter(existing ->
                        existing.getCompanyId().equals(companyId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Management team member not found with id: "
                                + memberId
                ));

        validateMemberData(request);
        applyRequest(member, request);

        return toResponse(memberRepository.save(member));
    }

    @Override
    public void deleteMember(Long companyId, Long memberId) {
        validateCompany(companyId);

        ManagementTeamMember member = memberRepository.findById(memberId)
                .filter(existing ->
                        existing.getCompanyId().equals(companyId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Management team member not found with id: "
                                + memberId
                ));

        memberRepository.delete(member);
    }

    private void validateCompany(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException(
                    "Company not found with id: " + companyId
            );
        }
    }

    private void validateMemberData(
            ManagementTeamMemberRequest request
    ) {
        if (request.memberType() == ManagementMemberType.OWNER_SHAREHOLDER) {
            if (request.ownershipShare() != null
                    && (request.ownershipShare().signum() < 0
                    || request.ownershipShare()
                    .compareTo(new java.math.BigDecimal("100")) > 0)) {
                throw new IllegalArgumentException(
                        "Ownership share must be between 0 and 100."
                );
            }
        } else if (request.ownershipShare() != null) {
            throw new IllegalArgumentException(
                    "Ownership share is only allowed for owners/shareholders."
            );
        }

        if (request.memberType() != ManagementMemberType.OWNER_SHAREHOLDER
                && request.operationalInvolvement() != null) {
            throw new IllegalArgumentException(
                    "Operational involvement is only allowed for owners/shareholders."
            );
        }
    }

    private void applyRequest(
            ManagementTeamMember member,
            ManagementTeamMemberRequest request
    ) {
        member.setMemberType(request.memberType());
        member.setName(request.name());
        member.setDepartment(request.department());
        member.setPosition(request.position());
        member.setResponsibilities(request.responsibilities());
        member.setOwnershipShare(request.ownershipShare());
        member.setOperationalInvolvement(
                request.operationalInvolvement()
        );

        if (request.active() != null) {
            member.setActive(request.active());
        }
    }

    private ManagementTeamMemberResponse toResponse(
            ManagementTeamMember member
    ) {
        return new ManagementTeamMemberResponse(
                member.getId(),
                member.getCompanyId(),
                member.getMemberType(),
                member.getName(),
                member.getDepartment(),
                member.getPosition(),
                member.getResponsibilities(),
                member.getOwnershipShare(),
                member.getOperationalInvolvement(),
                member.isActive(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }
}
