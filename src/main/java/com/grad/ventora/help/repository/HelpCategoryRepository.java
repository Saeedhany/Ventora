package com.grad.ventora.help.repository;
import com.grad.ventora.help.entity.HelpCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface HelpCategoryRepository extends JpaRepository<HelpCategory,Long> {
    List<HelpCategory> findAllByActiveTrueOrderByDisplayOrderAsc();
    List<HelpCategory> findAllByOrderByDisplayOrderAsc();
    boolean existsBySlug(String slug);
    boolean existsByNameIgnoreCase(String name);
}
