package com.grad.ventora.help.service.impl;
import com.grad.ventora.help.dto.*;
import com.grad.ventora.help.entity.*;
import com.grad.ventora.help.repository.*;
import com.grad.ventora.help.service.HelpCenterService;
import com.grad.ventora.exception.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
@RequiredArgsConstructor
@Transactional
public class HelpCenterServiceImpl implements HelpCenterService {
    private final HelpCategoryRepository categories;
    private final HelpFaqRepository faqs;
    private final HelpCenterPageContentRepository pages;

    private HelpCategory category(Long id) {
        return categories.findById(id).orElseThrow(() -> new ResourceNotFoundException("Help category not found"));
    }
    private HelpFaq faq(Long id) {
        return faqs.findById(id).orElseThrow(() -> new ResourceNotFoundException("FAQ not found"));
    }
    private HelpCategoryResponse categoryResponse(HelpCategory c) {
        return new HelpCategoryResponse(c.getId(),c.getName(),c.getSlug(),c.getDescription(),
            c.getDisplayOrder(),c.getActive(),faqs.countByCategory_IdAndStatus(c.getId(),HelpFaqStatus.PUBLISHED));
    }
    private HelpFaqResponse faqResponse(HelpFaq f) {
        return new HelpFaqResponse(f.getId(),f.getQuestion(),f.getAnswer(),categoryResponse(f.getCategory()),
            f.getDisplayOrder(),f.getStatus().name(),f.getFeatured(),f.getCreatedAt(),f.getUpdatedAt());
    }
    private HelpCenterPageContentResponse pageResponse(HelpCenterPageContent p) {
        return new HelpCenterPageContentResponse(p.getId(),p.getHeroTitle(),p.getHeroDescription(),
            p.getSearchPlaceholder(),p.getQuickAccessTitle(),p.getQuickAccessDescription(),p.getQuickAccessCards(),
            p.getFaqTitle(),p.getFaqDescription(),p.getStillNeedHelpTitle(),p.getStillNeedHelpDescription(),
            p.getContactSupportLabel(),p.getOtherSupportWays(),p.getMoreResourcesTitle(),
            p.getMoreResourcesDescription(),p.getBrowseResourcesLabel(),p.getCreatedAt(),p.getUpdatedAt());
    }
    @Override @Transactional(readOnly=true)
    public List<HelpCategoryResponse> categories(boolean admin) {
        return (admin?categories.findAllByOrderByDisplayOrderAsc():categories.findAllByActiveTrueOrderByDisplayOrderAsc())
            .stream().map(this::categoryResponse).toList();
    }
    @Override
    public HelpCategoryResponse saveCategory(Long id,HelpCategoryRequest r) {
        HelpCategory c=id==null?new HelpCategory():category(id);
        if(categories.existsBySlug(r.slug()) && (c.getId()==null || !c.getSlug().equals(r.slug())))
            throw new IllegalArgumentException("Help category slug already exists");
        if(categories.existsByNameIgnoreCase(r.name()) && (c.getId()==null || !c.getName().equalsIgnoreCase(r.name())))
            throw new IllegalArgumentException("Help category name already exists");
        c.setName(r.name()); c.setSlug(r.slug()); c.setDescription(r.description());
        c.setDisplayOrder(r.displayOrder()); c.setActive(r.active()==null || r.active());
        return categoryResponse(categories.save(c));
    }
    @Override
    public void deleteCategory(Long id) {
        HelpCategory c=category(id);
        if(faqs.existsByCategory_Id(id)) throw new IllegalStateException("Cannot delete a category that has FAQs");
        categories.delete(c);
    }
    @Override @Transactional(readOnly=true)
    public Page<HelpFaqResponse> listFaqs(String search,String category,int page,int size) {
        Pageable pageable=PageRequest.of(Math.max(0,page),Math.min(Math.max(1,size),50),
            Sort.by("displayOrder").ascending().and(Sort.by("createdAt").descending()));
        Page<HelpFaq> result;
        boolean hasSearch=search!=null&&!search.isBlank();
        boolean hasCategory=category!=null&&!category.isBlank();
        if(hasCategory && hasSearch) result=faqs.findByStatusAndCategory_SlugAndQuestionContainingIgnoreCase(HelpFaqStatus.PUBLISHED,category,search,pageable);
        else if(hasCategory) result=faqs.findByStatusAndCategory_Slug(HelpFaqStatus.PUBLISHED,category,pageable);
        else if(hasSearch) result=faqs.findByStatusAndQuestionContainingIgnoreCaseOrStatusAndAnswerContainingIgnoreCase(
            HelpFaqStatus.PUBLISHED,search,HelpFaqStatus.PUBLISHED,search,pageable);
        else result=faqs.findByStatus(HelpFaqStatus.PUBLISHED,pageable);
        return result.map(this::faqResponse);
    }
    @Override @Transactional(readOnly=true)
    public Page<HelpFaqResponse> adminFaqs(String search,String category,int page,int size) {
        Pageable pageable=PageRequest.of(Math.max(0,page),Math.min(Math.max(1,size),50),
            Sort.by("displayOrder").ascending().and(Sort.by("createdAt").descending()));
        // Admin listing intentionally supports status-independent search and category filtering.
        List<HelpFaq> all=faqs.findAll(Sort.by("displayOrder").ascending().and(Sort.by("createdAt").descending()));
        String q=search==null?"":search.toLowerCase(Locale.ROOT);
        List<HelpFaq> filtered=all.stream()
            .filter(f->category==null||category.isBlank()||f.getCategory().getSlug().equals(category))
            .filter(f->q.isBlank()||f.getQuestion().toLowerCase(Locale.ROOT).contains(q)||f.getAnswer().toLowerCase(Locale.ROOT).contains(q))
            .toList();
        int from=Math.min((int)pageable.getOffset(),filtered.size());
        int to=Math.min(from+pageable.getPageSize(),filtered.size());
        return new PageImpl<>(filtered.subList(from,to).stream().map(this::faqResponse).toList(),pageable,filtered.size());
    }
    @Override @Transactional(readOnly=true)
    public HelpFaqResponse getFaq(Long id) {
        HelpFaq f=faqs.findById(id).orElseThrow(()->new ResourceNotFoundException("FAQ not found"));
        if(f.getStatus()!=HelpFaqStatus.PUBLISHED) throw new ResourceNotFoundException("Published FAQ not found");
        return faqResponse(f);
    }
    @Override
    public HelpFaqResponse createFaq(HelpFaqRequest r) {
        HelpFaq f=new HelpFaq(); apply(f,r); f.setStatus(HelpFaqStatus.DRAFT); return faqResponse(faqs.save(f));
    }
    @Override
    public HelpFaqResponse updateFaq(Long id,HelpFaqRequest r) {
        HelpFaq f=faq(id); apply(f,r); return faqResponse(faqs.save(f));
    }
    private void apply(HelpFaq f,HelpFaqRequest r) {
        f.setQuestion(r.question()); f.setAnswer(r.answer()); f.setCategory(category(r.categoryId()));
        f.setDisplayOrder(r.displayOrder()); f.setFeatured(Boolean.TRUE.equals(r.featured()));
    }
    @Override
    public HelpFaqResponse publishFaq(Long id) { HelpFaq f=faq(id); f.setStatus(HelpFaqStatus.PUBLISHED); return faqResponse(faqs.save(f)); }
    @Override
    public HelpFaqResponse unpublishFaq(Long id) { HelpFaq f=faq(id); f.setStatus(HelpFaqStatus.DRAFT); return faqResponse(faqs.save(f)); }
    @Override
    public void deleteFaq(Long id) { faqs.delete(faq(id)); }
    @Override @Transactional(readOnly=true)
    public List<HelpFaqResponse> featuredFaqs() {
        return faqs.findTop6ByStatusAndFeaturedTrueOrderByDisplayOrderAsc(HelpFaqStatus.PUBLISHED)
            .stream().map(this::faqResponse).toList();
    }
    @Override @Transactional(readOnly=true)
    public HelpCenterPageContentResponse pageContent() {
        return pages.findFirstByOrderByIdAsc().map(this::pageResponse).orElse(null);
    }
    @Override @Transactional(readOnly=true)
    public HelpCenterPageContentResponse adminPageContent() { return pageContent(); }
    @Override
    public HelpCenterPageContentResponse savePageContent(HelpCenterPageContentRequest r) {
        HelpCenterPageContent p=pages.findFirstByOrderByIdAsc().orElseGet(HelpCenterPageContent::new);
        p.setHeroTitle(r.heroTitle()); p.setHeroDescription(r.heroDescription()); p.setSearchPlaceholder(r.searchPlaceholder());
        p.setQuickAccessTitle(r.quickAccessTitle()); p.setQuickAccessDescription(r.quickAccessDescription());
        p.setQuickAccessCards(r.quickAccessCards()==null?new ArrayList<>():r.quickAccessCards());
        p.setFaqTitle(r.faqTitle()); p.setFaqDescription(r.faqDescription());
        p.setStillNeedHelpTitle(r.stillNeedHelpTitle()); p.setStillNeedHelpDescription(r.stillNeedHelpDescription());
        p.setContactSupportLabel(r.contactSupportLabel());
        p.setOtherSupportWays(r.otherSupportWays()==null?new ArrayList<>():r.otherSupportWays());
        p.setMoreResourcesTitle(r.moreResourcesTitle()); p.setMoreResourcesDescription(r.moreResourcesDescription());
        p.setBrowseResourcesLabel(r.browseResourcesLabel());
        return pageResponse(pages.save(p));
    }
}
