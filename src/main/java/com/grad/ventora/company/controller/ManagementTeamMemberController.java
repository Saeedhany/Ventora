
package com.grad.ventora.company.controller;

import com.grad.ventora.company.service.IManagementTeamMemberService;
import com.grad.ventora.dto.ManagementTeamMemberRequest;
import com.grad.ventora.dto.ManagementTeamMemberResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        "/api/v1/companies/{companyId}/management-team"
)
@RequiredArgsConstructor
public class ManagementTeamMemberController {

    private final IManagementTeamMemberService managementTeamMemberService;

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public ManagementTeamMemberResponse create(
            @PathVariable Long companyId,
            @Valid @RequestBody ManagementTeamMemberRequest request
    ) {
        return managementTeamMemberService.createMember(
                companyId, request
        );
    }

    @GetMapping("/admin")
    public List<ManagementTeamMemberResponse> getAll(
            @PathVariable Long companyId,
            @RequestParam(required = false) String memberType
    ) {
        return managementTeamMemberService.getMembers(
                companyId, memberType
        );
    }

    @PutMapping("/{memberId}/admin")
    public ManagementTeamMemberResponse update(
            @PathVariable Long companyId,
            @PathVariable Long memberId,
            @Valid @RequestBody ManagementTeamMemberRequest request
    ) {
        return managementTeamMemberService.updateMember(
                companyId, memberId, request
        );
    }

    @DeleteMapping("/{memberId}/admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long companyId,
            @PathVariable Long memberId
    ) {
        managementTeamMemberService.deleteMember(
                companyId, memberId
        );
    }
}
