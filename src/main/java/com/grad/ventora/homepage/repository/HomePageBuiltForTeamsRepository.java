package com.grad.ventora.homepage.repository;
import com.grad.ventora.homepage.entity.HomePageBuiltForTeams;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface HomePageBuiltForTeamsRepository extends JpaRepository<HomePageBuiltForTeams, Long> {
    Optional<HomePageBuiltForTeams> findBySectionId(Long sectionId);
}
