package com.grad.ventora.help.repository;
import com.grad.ventora.help.entity.HelpCenterPageContent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface HelpCenterPageContentRepository extends JpaRepository<HelpCenterPageContent,Long> {
    Optional<HelpCenterPageContent> findFirstByOrderByIdAsc();
}
