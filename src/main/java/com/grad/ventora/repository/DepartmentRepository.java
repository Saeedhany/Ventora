
package com.grad.ventora.repository;

import com.grad.ventora.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {

    List<Department> findByCompanyId(Long companyId);

    List<Department> findByCompanyIdAndBusinessUnitId(
            Long companyId,
            Long businessUnitId
    );
}
