
package com.grad.ventora.repository;

import com.grad.ventora.entity.CompanyBranch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyBranchRepository
        extends JpaRepository<CompanyBranch, Long> {

    List<CompanyBranch> findByCompanyId(Long companyId);

    Optional<CompanyBranch> findByIdAndCompanyId(
            Long id,
            Long companyId
    );

    boolean existsByCompanyIdAndHeadquartersTrue(Long companyId);
}
