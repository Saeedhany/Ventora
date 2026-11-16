package com.grad.ventora.about.repository;
import com.grad.ventora.about.entity.AboutPage; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface AboutPageRepository extends JpaRepository<AboutPage,Long>{ Optional<AboutPage> findFirstByPublishedTrueOrderByIdAsc(); Optional<AboutPage> findFirstByOrderByIdAsc(); }
