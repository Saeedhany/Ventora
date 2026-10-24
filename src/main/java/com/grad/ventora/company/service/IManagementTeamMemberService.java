
package com.grad.ventora.company.service;

import com.grad.ventora.dto.ManagementTeamMemberRequest;
import com.grad.ventora.dto.ManagementTeamMemberResponse;

import java.util.List;

public interface IManagementTeamMemberService {

    ManagementTeamMemberResponse createMember(
            Long companyId,
            ManagementTeamMemberRequest request
    );

    List<ManagementTeamMemberResponse> getMembers(
            Long companyId,
            String memberType
    );

    ManagementTeamMemberResponse updateMember(
            Long companyId,
            Long memberId,
            ManagementTeamMemberRequest request
    );

    void deleteMember(Long companyId, Long memberId);
}
