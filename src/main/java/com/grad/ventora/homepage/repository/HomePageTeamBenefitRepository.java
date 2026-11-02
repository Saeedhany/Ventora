package com.grad.ventora.homepage.repository;
import com.grad.ventora.homepage.entity.HomePageTeamBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface HomePageTeamBenefitRepository extends JpaRepository<HomePageTeamBenefit, Long> {
    List<HomePageTeamBenefit> findByBuiltForTeamsIdOrderByDisplayOrderAsc(Long builtForTeamsId);
}
