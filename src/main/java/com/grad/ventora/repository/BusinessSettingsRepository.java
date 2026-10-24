
package com.grad.ventora.repository;

import com.grad.ventora.entity.BusinessSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessSettingsRepository
        extends JpaRepository<BusinessSettings, Long> {

    Optional<BusinessSettings> findByCompanyId(Long companyId);

    boolean existsByCompanyId(Long companyId);
}
