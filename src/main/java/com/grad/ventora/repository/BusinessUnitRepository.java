
package com.grad.ventora.repository;

import com.grad.ventora.entity.BusinessUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessUnitRepository
        extends JpaRepository<BusinessUnit, Long> {

    List<BusinessUnit> findByCompanyId(Long companyId);
}
