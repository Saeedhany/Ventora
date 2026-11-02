package com.grad.ventora.homepage.repository;
import com.grad.ventora.homepage.entity.HomePageStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface HomePageStatisticRepository extends JpaRepository<HomePageStatistic, Long> {
    List<HomePageStatistic> findByWhyVentoraIdOrderByDisplayOrderAsc(Long whyVentoraId);
}
