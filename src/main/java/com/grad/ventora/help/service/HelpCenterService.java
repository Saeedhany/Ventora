package com.grad.ventora.help.service;
import com.grad.ventora.help.dto.*;
import org.springframework.data.domain.Page;
import java.util.*;
public interface HelpCenterService {
    List<HelpCategoryResponse> categories(boolean admin);
    HelpCategoryResponse saveCategory(Long id,HelpCategoryRequest request);
    void deleteCategory(Long id);
    Page<HelpFaqResponse> listFaqs(String search,String category,int page,int size);
    Page<HelpFaqResponse> adminFaqs(String search,String category,int page,int size);
    HelpFaqResponse getFaq(Long id);
    HelpFaqResponse createFaq(HelpFaqRequest request);
    HelpFaqResponse updateFaq(Long id,HelpFaqRequest request);
    HelpFaqResponse publishFaq(Long id);
    HelpFaqResponse unpublishFaq(Long id);
    void deleteFaq(Long id);
    List<HelpFaqResponse> featuredFaqs();
    HelpCenterPageContentResponse pageContent();
    HelpCenterPageContentResponse adminPageContent();
    HelpCenterPageContentResponse savePageContent(HelpCenterPageContentRequest request);
}
