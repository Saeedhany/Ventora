package com.grad.ventora.homepage.service.impl;
import com.grad.ventora.homepage.dto.*;
import com.grad.ventora.homepage.entity.*;
import com.grad.ventora.homepage.repository.*;
import com.grad.ventora.homepage.service.IHomePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service @RequiredArgsConstructor
@Transactional
public class HomePageServiceImpl implements IHomePageService {
 private final HomePageContentRepository contentRepo; private final HomePageSectionRepository sectionRepo; private final HomePageVersionHistoryRepository historyRepo;
 private final HomePageHeroRepository heroRepo; private final HomePageWhyVentoraRepository whyRepo;
 private final HomePageBuiltForTeamsRepository teamsRepo; private final HomePageTestimonialsRepository testimonialsRepo;
 private final HomePageGetStartedRepository getStartedRepo; private final HomePageFooterRepository footerRepo; private final HomePageStatisticsSectionRepository statisticsSectionRepo;
 @Override @Transactional(readOnly=true) public HomePageContentResponse getPublished(HomePageLocale locale){return response(contentRepo.findFirstByLocaleAndStatusOrderByVersionDesc(locale,HomePageContentStatus.PUBLISHED).orElseThrow(()->new IllegalArgumentException("No published Home Page for locale: "+locale)));}
 @Override public HomePageContentResponse createDraft(HomePageDraftRequest request){
  HomePageLocale locale=request.locale();
  if(contentRepo.existsByLocaleAndStatus(locale,HomePageContentStatus.DRAFT)) throw new IllegalStateException("A draft already exists for locale: "+locale);
  HomePageContent source=contentRepo.findFirstByLocaleAndStatusOrderByVersionDesc(locale,HomePageContentStatus.PUBLISHED).orElse(null);
  HomePageContent draft=new HomePageContent(); draft.setLocale(locale); draft.setStatus(HomePageContentStatus.DRAFT); draft.setVersion(contentRepo.findMaxVersionByLocale(locale)+1);
  List<HomePageSection> sourceSections=source==null?List.of():sectionRepo.findByHomePageContentOrderByDisplayOrderAsc(source);
  if(source!=null){draft.setSeoTitle(source.getSeoTitle());draft.setSeoDescription(source.getSeoDescription()); for(HomePageSection old:sourceSections){HomePageSection s=new HomePageSection();s.setSectionType(old.getSectionType());s.setDisplayOrder(old.getDisplayOrder());s.setVisible(old.isVisible());draft.addSection(s);}}
  HomePageContent saved=contentRepo.save(draft); if(source!=null) copySectionContent(sourceSections,sectionRepo.findByHomePageContentOrderByDisplayOrderAsc(saved)); return response(saved);
 }
 @Override @Transactional(readOnly=true) public HomePageContentResponse getDraft(Long draftId){return response(requireDraft(draftId));}
 @Override public HomePageSectionResponse addSection(Long draftId,HomePageSectionRequest request){HomePageContent draft=requireDraft(draftId);if(sectionRepo.existsByHomePageContentIdAndSectionType(draftId,request.sectionType()))throw new IllegalArgumentException("Section type already exists in this draft");HomePageSection s=new HomePageSection();s.setSectionType(request.sectionType());s.setDisplayOrder(request.displayOrder()==null?draft.getSections().size():request.displayOrder());s.setVisible(request.visible()==null||request.visible());draft.addSection(s); normalize(draft);contentRepo.save(draft);return sectionResponse(s);}

 @Override @Transactional(readOnly=true) public HomePageSectionContentResponse getSectionContent(Long draftId, Long sectionId) {
  HomePageContent draft=requireDraft(draftId); HomePageSection section=requireSection(draft,sectionId);
  return new HomePageSectionContentResponse(section.getId(),section.getSectionType(),readSectionContent(section));
 }
 @Override public HomePageSectionContentResponse updateSectionContent(Long draftId, Long sectionId, HomePageSectionContentRequest r) {
  HomePageContent draft=requireDraft(draftId); HomePageSection section=requireSection(draft,sectionId);
  switch(section.getSectionType()) {
   case HERO -> { HomePageHero e=heroRepo.findBySectionId(sectionId).orElseGet(HomePageHero::new); e.setSection(section); e.setTitle(required(r.title(),"title")); e.setBody(r.body()); e.setPrimaryCtaLabel(r.primaryCtaLabel()); e.setPrimaryCtaUrl(r.primaryCtaUrl()); e.setSecondaryCtaLabel(r.secondaryCtaLabel()); e.setSecondaryCtaUrl(r.secondaryCtaUrl()); e.setImageUrl(r.imageUrl()); e.setImageAltText(r.imageAltText()); heroRepo.save(e); }
   case WHY_VENTORA -> { HomePageWhyVentora e=whyRepo.findBySectionId(sectionId).orElseGet(HomePageWhyVentora::new); e.setSection(section); e.setTitle(r.title()); e.setDescription(r.body()); e.getFeatures().clear(); e.getStatistics().clear(); if(r.features()!=null) for(var x:r.features()){HomePageFeature c=new HomePageFeature();c.setWhyVentora(e);c.setTitle(required(x.title(),"feature title"));c.setDescription(x.description());c.setIcon(x.icon());c.setDisplayOrder(order(x.displayOrder(),e.getFeatures().size()));e.getFeatures().add(c);} if(r.statistics()!=null) for(var x:r.statistics()){HomePageStatistic c=new HomePageStatistic();c.setWhyVentora(e);c.setValue(required(x.value(),"statistic value"));c.setLabel(required(x.label(),"statistic label"));c.setDisplayOrder(order(x.displayOrder(),e.getStatistics().size()));e.getStatistics().add(c);} whyRepo.save(e); }
   case STATISTICS -> {
    HomePageStatisticsSection e = statisticsSectionRepo.findBySectionId(sectionId)
        .orElseGet(HomePageStatisticsSection::new);
    e.setSection(section);
    e.setTitle(required(r.title(), "title"));
    e.setDescription(r.body());
    e.getStatistics().clear();
    if (r.statistics() != null) {
     for (var x : r.statistics()) {
      HomePageStandaloneStatistic statistic = new HomePageStandaloneStatistic();
      statistic.setStatisticsSection(e);
      statistic.setValue(required(x.value(), "statistic value"));
      statistic.setLabel(required(x.label(), "statistic label"));
      statistic.setDisplayOrder(order(x.displayOrder(), e.getStatistics().size()));
      e.getStatistics().add(statistic);
     }
    }
    statisticsSectionRepo.save(e);
   }
   case BUILT_FOR_TEAMS -> { HomePageBuiltForTeams e=teamsRepo.findBySectionId(sectionId).orElseGet(HomePageBuiltForTeams::new); e.setSection(section);e.setTitle(r.title());e.setDescription(r.body());e.getBenefits().clear();if(r.benefits()!=null)for(var x:r.benefits()){HomePageTeamBenefit c=new HomePageTeamBenefit();c.setBuiltForTeams(e);c.setTitle(required(x.title(),"benefit title"));c.setDescription(x.description());c.setIcon(x.icon());c.setDisplayOrder(order(x.displayOrder(),e.getBenefits().size()));e.getBenefits().add(c);}teamsRepo.save(e); }
   case TESTIMONIALS -> { HomePageTestimonials e=testimonialsRepo.findBySectionId(sectionId).orElseGet(HomePageTestimonials::new);e.setSection(section);e.setTitle(r.title());e.getItems().clear();if(r.testimonials()!=null)for(var x:r.testimonials()){HomePageTestimonial c=new HomePageTestimonial();c.setTestimonials(e);c.setPersonName(required(x.personName(),"testimonial personName"));c.setRoleOrCompany(x.roleOrCompany());c.setQuote(required(x.quote(),"testimonial quote"));c.setPhotoUrl(x.photoUrl());c.setDisplayOrder(order(x.displayOrder(),e.getItems().size()));e.getItems().add(c);}testimonialsRepo.save(e); }
   case GET_STARTED -> { HomePageGetStarted e=getStartedRepo.findBySectionId(sectionId).orElseGet(HomePageGetStarted::new);e.setSection(section);e.setTitle(r.title());e.setDescription(r.body());e.setPrimaryCtaLabel(r.primaryCtaLabel());e.setPrimaryCtaUrl(r.primaryCtaUrl());e.setSecondaryCtaLabel(r.secondaryCtaLabel());e.setSecondaryCtaUrl(r.secondaryCtaUrl());getStartedRepo.save(e); }
   case FOOTER -> { HomePageFooter e=footerRepo.findBySectionId(sectionId).orElseGet(HomePageFooter::new);e.setSection(section);e.setTagline(r.tagline());e.setNewsletterTitle(r.newsletterTitle());e.setNewsletterDescription(r.newsletterDescription());e.setNewsletterButtonLabel(r.newsletterButtonLabel());e.setCopyrightText(r.copyrightText());e.getLinks().clear();if(r.links()!=null)for(var x:r.links()){HomePageFooterLink c=new HomePageFooterLink();c.setFooter(e);c.setLabel(required(x.label(),"footer link label"));c.setUrl(required(x.url(),"footer link url"));c.setGroupName(x.groupName());c.setDisplayOrder(order(x.displayOrder(),e.getLinks().size()));e.getLinks().add(c);}footerRepo.save(e); }
  }
  return new HomePageSectionContentResponse(section.getId(),section.getSectionType(),readSectionContent(section));
 }
 private HomePageSectionContentRequest readSectionContent(HomePageSection s) {
  Long id=s.getId();
  return switch(s.getSectionType()) {
   case HERO -> heroRepo.findBySectionId(id).map(e->new HomePageSectionContentRequest(e.getTitle(),e.getBody(),e.getPrimaryCtaLabel(),e.getPrimaryCtaUrl(),e.getSecondaryCtaLabel(),e.getSecondaryCtaUrl(),e.getImageUrl(),e.getImageAltText(),null,null,null,null,null,null,null,null,null,null)).orElseGet(this::emptyContent);
   case WHY_VENTORA -> whyRepo.findBySectionId(id).map(e->new HomePageSectionContentRequest(e.getTitle(),e.getDescription(),null,null,null,null,null,null,e.getFeatures().stream().map(x->new HomePageSectionContentRequest.FeatureItem(x.getTitle(),x.getDescription(),x.getIcon(),x.getDisplayOrder())).toList(),e.getStatistics().stream().map(x->new HomePageSectionContentRequest.StatisticItem(x.getValue(),x.getLabel(),x.getDisplayOrder())).toList(),null,null,null,null,null,null,null,null)).orElseGet(this::emptyContent);
   case STATISTICS -> statisticsSectionRepo.findBySectionId(id)
       .map(e -> new HomePageSectionContentRequest(
           e.getTitle(), e.getDescription(),
           null, null, null, null, null, null,
           null,
           e.getStatistics().stream()
               .map(x -> new HomePageSectionContentRequest.StatisticItem(
                   x.getValue(), x.getLabel(), x.getDisplayOrder()
               ))
               .toList(),
           null, null, null, null, null, null, null, null
       ))
       .orElseGet(this::emptyContent);
   case BUILT_FOR_TEAMS -> teamsRepo.findBySectionId(id).map(e->new HomePageSectionContentRequest(e.getTitle(),e.getDescription(),null,null,null,null,null,null,null,null,e.getBenefits().stream().map(x->new HomePageSectionContentRequest.BenefitItem(x.getTitle(),x.getDescription(),x.getIcon(),x.getDisplayOrder())).toList(),null,null,null,null,null,null,null)).orElseGet(this::emptyContent);
   case TESTIMONIALS -> testimonialsRepo.findBySectionId(id).map(e->new HomePageSectionContentRequest(e.getTitle(),null,null,null,null,null,null,null,null,null,null,e.getItems().stream().map(x->new HomePageSectionContentRequest.TestimonialItem(x.getPersonName(),x.getRoleOrCompany(),x.getQuote(),x.getPhotoUrl(),x.getDisplayOrder())).toList(),null,null,null,null,null,null)).orElseGet(this::emptyContent);
   case GET_STARTED -> getStartedRepo.findBySectionId(id).map(e->new HomePageSectionContentRequest(e.getTitle(),e.getDescription(),e.getPrimaryCtaLabel(),e.getPrimaryCtaUrl(),e.getSecondaryCtaLabel(),e.getSecondaryCtaUrl(),null,null,null,null,null,null,null,null,null,null,null,null)).orElseGet(this::emptyContent);
   case FOOTER -> footerRepo.findBySectionId(id).map(e->new HomePageSectionContentRequest(null,null,null,null,null,null,null,null,null,null,null,null,e.getTagline(),e.getNewsletterTitle(),e.getNewsletterDescription(),e.getNewsletterButtonLabel(),e.getCopyrightText(),e.getLinks().stream().map(x->new HomePageSectionContentRequest.FooterLinkItem(x.getLabel(),x.getUrl(),x.getGroupName(),x.getDisplayOrder())).toList())).orElseGet(this::emptyContent);
  };
 }
 private HomePageSectionContentRequest emptyContent(){return new HomePageSectionContentRequest(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);}
 private String required(String value,String field){if(value==null||value.isBlank())throw new IllegalArgumentException(field+" is required for this section");return value;}
 private int order(Integer value,int fallback){if(value!=null&&value<0)throw new IllegalArgumentException("displayOrder cannot be negative");return value==null?fallback:value;}

 private void deleteStatisticsContent(HomePageSection section) {
  if (section.getSectionType() == HomePageSectionType.STATISTICS) {
   statisticsSectionRepo.findBySectionId(section.getId())
       .ifPresent(statisticsSectionRepo::delete);
  }
 }
 @Override
 public HomePageSectionResponse updateSection(
         Long draftId, Long sectionId, HomePageSectionRequest request) {
  HomePageContent draft = requireDraft(draftId);
  HomePageSection section = requireSection(draft, sectionId);

  if (request.sectionType() != null
          && !request.sectionType().equals(section.getSectionType())
          && sectionRepo.existsByHomePageContentIdAndSectionType(
                  draftId, request.sectionType())) {
   throw new IllegalArgumentException("Section type already exists in this draft");
  }

  if (request.sectionType() != null
          && !request.sectionType().equals(section.getSectionType())) {
   deleteStatisticsContent(section);
   section.setSectionType(request.sectionType());
  }

  if (request.displayOrder() != null) {
   section.setDisplayOrder(request.displayOrder());
  }
  if (request.visible() != null) {
   section.setVisible(request.visible());
  }

  normalize(draft);
  return sectionResponse(section);
 }

 @Override
 public void deleteSection(Long draftId, Long sectionId) {
  HomePageContent draft = requireDraft(draftId);
  HomePageSection section = requireSection(draft, sectionId);
  deleteStatisticsContent(section);
  draft.removeSection(section);
  sectionRepo.delete(section);
  normalize(draft);
 }
 @Override public void reorderSections(Long draftId,HomePageReorderRequest request){HomePageContent d=requireDraft(draftId);List<HomePageSection> sections=sectionRepo.findByHomePageContentOrderByDisplayOrderAsc(d);Map<Long,HomePageSection> byId=sections.stream().collect(Collectors.toMap(HomePageSection::getId,Function.identity()));if(request.sectionIds().size()!=sections.size()||new HashSet<>(request.sectionIds()).size()!=sections.size()||!byId.keySet().equals(new HashSet<>(request.sectionIds())))throw new IllegalArgumentException("sectionIds must contain every section in this draft exactly once");for(int i=0;i<request.sectionIds().size();i++)byId.get(request.sectionIds().get(i)).setDisplayOrder(i);sectionRepo.saveAll(sections);}
 @Override public HomePageContentResponse updateSeo(Long draftId,String title,String description){HomePageContent d=requireDraft(draftId);if(title!=null)d.setSeoTitle(title);if(description!=null)d.setSeoDescription(description);return response(contentRepo.save(d));}
 @Override public HomePageContentResponse publish(Long draftId,HomePagePublishRequest request){HomePageContent d=requireDraft(draftId);if(d.getSections().isEmpty())throw new IllegalStateException("Cannot publish a Home Page without sections");for(HomePageContent old:contentRepo.findByLocaleOrderByVersionDesc(d.getLocale()))if(old.getStatus()==HomePageContentStatus.PUBLISHED){old.setStatus(HomePageContentStatus.ARCHIVED);contentRepo.save(old);}d.setStatus(HomePageContentStatus.PUBLISHED);d.setPublishedAt(java.time.LocalDateTime.now());HomePageContent saved=contentRepo.save(d);record(saved,HomePageHistoryAction.PUBLISHED,request);return response(saved);}
 @Override @Transactional(readOnly=true) public List<HomePageContentResponse> getVersions(HomePageLocale locale){return contentRepo.findByLocaleOrderByVersionDesc(locale).stream().map(this::response).toList();}
 @Override @Transactional(readOnly=true) public List<HomePageHistoryResponse> getHistory(HomePageLocale locale){return historyRepo.findByHomePageContent_LocaleOrderByCreatedAtDesc(locale).stream().map(h->new HomePageHistoryResponse(h.getId(),h.getHomePageContent().getId(),h.getHomePageContent().getVersion(),h.getHomePageContent().getLocale(),h.getAction(),h.getReleaseNote(),h.getActor(),h.getCreatedAt())).toList();}
 @Override public HomePageContentResponse restore(Long versionId,HomePagePublishRequest request){HomePageContent source=contentRepo.findById(versionId).orElseThrow(()->new IllegalArgumentException("Home Page version not found: "+versionId));if(source.getStatus()==HomePageContentStatus.DRAFT)throw new IllegalArgumentException("Only published or archived versions can be restored");HomePageLocale locale=source.getLocale();if(contentRepo.existsByLocaleAndStatus(locale,HomePageContentStatus.DRAFT))throw new IllegalStateException("A draft already exists for locale: "+locale);HomePageContent d=new HomePageContent();d.setLocale(locale);d.setStatus(HomePageContentStatus.DRAFT);d.setVersion(contentRepo.findMaxVersionByLocale(locale)+1);d.setSeoTitle(source.getSeoTitle());d.setSeoDescription(source.getSeoDescription());for(HomePageSection old:sectionRepo.findByHomePageContentOrderByDisplayOrderAsc(source)){HomePageSection s=new HomePageSection();s.setSectionType(old.getSectionType());s.setDisplayOrder(old.getDisplayOrder());s.setVisible(old.isVisible());d.addSection(s);}HomePageContent saved=contentRepo.save(d);copySectionContent(sectionRepo.findByHomePageContentOrderByDisplayOrderAsc(source),sectionRepo.findByHomePageContentOrderByDisplayOrderAsc(saved));record(saved,HomePageHistoryAction.RESTORED,request);return response(saved);}
 private void copySectionContent(List<HomePageSection> originals,List<HomePageSection> copies){
  Map<Integer,HomePageSection> byOrder=copies.stream().collect(Collectors.toMap(HomePageSection::getDisplayOrder,Function.identity(),(a,b)->a));
  for(HomePageSection old:originals){boolean exists=switch(old.getSectionType()){
   case HERO -> heroRepo.findBySectionId(old.getId()).isPresent(); case WHY_VENTORA -> whyRepo.findBySectionId(old.getId()).isPresent();
   case STATISTICS -> statisticsSectionRepo.findBySectionId(old.getId()).isPresent();    case BUILT_FOR_TEAMS -> teamsRepo.findBySectionId(old.getId()).isPresent(); case TESTIMONIALS -> testimonialsRepo.findBySectionId(old.getId()).isPresent();
   case GET_STARTED -> getStartedRepo.findBySectionId(old.getId()).isPresent(); case FOOTER -> footerRepo.findBySectionId(old.getId()).isPresent();};
   if(exists){HomePageSection copy=byOrder.get(old.getDisplayOrder());if(copy!=null)updateSectionContent(copy.getHomePageContent().getId(),copy.getId(),readSectionContent(old));}
  }
 }
 private void record(HomePageContent c,HomePageHistoryAction action,HomePagePublishRequest r){HomePageVersionHistory h=new HomePageVersionHistory();h.setHomePageContent(c);h.setAction(action);if(r!=null){h.setReleaseNote(r.releaseNote());h.setActor(r.actor());}historyRepo.save(h);}
 private HomePageContent requireDraft(Long id){HomePageContent c=contentRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Home Page content not found: "+id));if(c.getStatus()!=HomePageContentStatus.DRAFT)throw new IllegalStateException("Only a DRAFT can be modified");return c;}
 private HomePageSection requireSection(HomePageContent d,Long id){return sectionRepo.findById(id).filter(s->s.getHomePageContent().getId().equals(d.getId())).orElseThrow(()->new IllegalArgumentException("Section not found in this draft: "+id));}
 private void normalize(HomePageContent d){List<HomePageSection> ss=sectionRepo.findByHomePageContentOrderByDisplayOrderAsc(d);ss.sort(Comparator.comparing(HomePageSection::getDisplayOrder,Comparator.nullsLast(Integer::compareTo)));for(int i=0;i<ss.size();i++)ss.get(i).setDisplayOrder(i);sectionRepo.saveAll(ss);}
 private HomePageSectionResponse sectionResponse(HomePageSection s){return new HomePageSectionResponse(s.getId(),s.getSectionType(),s.getDisplayOrder(),s.isVisible(),readSectionContent(s));}
 private HomePageContentResponse response(HomePageContent c){List<HomePageSectionResponse> sections=sectionRepo.findByHomePageContentOrderByDisplayOrderAsc(c).stream().map(this::sectionResponse).toList();return new HomePageContentResponse(c.getId(),c.getVersion(),c.getLocale(),c.getStatus(),c.getSeoTitle(),c.getSeoDescription(),c.getCreatedAt(),c.getPublishedAt(),sections);}
}
