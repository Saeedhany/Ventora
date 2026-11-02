package com.grad.ventora.homepage.repository;
import com.grad.ventora.homepage.entity.HomePageTestimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface HomePageTestimonialRepository extends JpaRepository<HomePageTestimonial, Long> {
    List<HomePageTestimonial> findByTestimonialsIdOrderByDisplayOrderAsc(Long testimonialsId);
}
