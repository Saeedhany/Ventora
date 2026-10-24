
package com.grad.ventora.repository;

import com.grad.ventora.entity.CompanyContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyContactInfoRepository
        extends JpaRepository<CompanyContactInfo, Long> {

    Optional<CompanyContactInfo> findByCompanyId(Long companyId);

    boolean existsByCompanyId(Long companyId);
}
