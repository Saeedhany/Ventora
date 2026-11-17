package com.grad.ventora.help.repository;
import com.grad.ventora.help.entity.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface HelpFaqRepository extends JpaRepository<HelpFaq,Long> {
    Page<HelpFaq> findByStatus(HelpFaqStatus status, Pageable pageable);
    Page<HelpFaq> findByStatusAndCategory_Slug(HelpFaqStatus status,String slug,Pageable pageable);
    Page<HelpFaq> findByStatusAndQuestionContainingIgnoreCaseOrStatusAndAnswerContainingIgnoreCase(
        HelpFaqStatus s1,String question,HelpFaqStatus s2,String answer,Pageable pageable);
    Page<HelpFaq> findByStatusAndCategory_SlugAndQuestionContainingIgnoreCase(
        HelpFaqStatus status,String slug,String question,Pageable pageable);
    List<HelpFaq> findTop6ByStatusAndFeaturedTrueOrderByDisplayOrderAsc(HelpFaqStatus status);
    long countByCategory_IdAndStatus(Long categoryId,HelpFaqStatus status);
    boolean existsByCategory_Id(Long categoryId);
}
