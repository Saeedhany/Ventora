
package com.grad.ventora.repository;

import com.grad.ventora.entity.ManagementMemberType;
import com.grad.ventora.entity.ManagementTeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagementTeamMemberRepository
        extends JpaRepository<ManagementTeamMember, Long> {

    List<ManagementTeamMember> findByCompanyId(Long companyId);

    List<ManagementTeamMember> findByCompanyIdAndMemberType(
            Long companyId,
            ManagementMemberType memberType
    );
}
