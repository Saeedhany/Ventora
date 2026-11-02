package com.grad.ventora.homepage.repository;
import com.grad.ventora.homepage.entity.HomePageFooterLink;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface HomePageFooterLinkRepository extends JpaRepository<HomePageFooterLink, Long> {
    List<HomePageFooterLink> findByFooterIdOrderByDisplayOrderAsc(Long footerId);
}
