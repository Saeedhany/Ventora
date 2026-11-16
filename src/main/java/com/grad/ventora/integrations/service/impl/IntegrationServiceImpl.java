package com.grad.ventora.integrations.service.impl;
import com.grad.ventora.exception.exceptions.ResourceNotFoundException;
import com.grad.ventora.integrations.category.entity.IntegrationCategory;
import com.grad.ventora.integrations.category.repository.IntegrationCategoryRepository;
import com.grad.ventora.integrations.dto.*; import com.grad.ventora.integrations.entity.Integration;
import com.grad.ventora.integrations.repository.IntegrationRepository; import com.grad.ventora.integrations.service.IIntegrationService;
import lombok.RequiredArgsConstructor; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service @RequiredArgsConstructor @Transactional
public class IntegrationServiceImpl implements IIntegrationService {
 private final IntegrationRepository repository; private final IntegrationCategoryRepository categoryRepository;
 @Override @Transactional(readOnly=true) public List<IntegrationResponse> getPublic(String category) {
  List<Integration> items;
  if(category!=null && !category.isBlank()) items=repository.findDistinctByCategories_SlugAndActiveTrueOrderByDisplayOrderAscIdAsc(category);
  else items=repository.findByActiveTrueOrderByDisplayOrderAscIdAsc();
  return items.stream().map(this::toResponse).toList();
 }
 @Override @Transactional(readOnly=true) public IntegrationResponse getPublicBySlug(String slug) {
  return repository.findBySlugAndActiveTrue(slug).map(this::toResponse).orElseThrow(()->new ResourceNotFoundException("Active integration not found: "+slug));
 }
 @Override @Transactional(readOnly=true) public List<IntegrationResponse> getAdmin(){return repository.findAllByOrderByDisplayOrderAscIdAsc().stream().map(this::toResponse).toList();}
 @Override public IntegrationResponse create(IntegrationRequest r){validate(r,null); Integration x=new Integration(); apply(x,r); return toResponse(repository.save(x));}
 @Override public IntegrationResponse update(Long id,IntegrationRequest r){Integration x=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Integration not found: "+id)); validate(r,id); apply(x,r); return toResponse(repository.save(x));}
 @Override public void delete(Long id){Integration x=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Integration not found: "+id)); x.getCategories().clear(); repository.delete(x);}
 private void validate(IntegrationRequest r,Long id){
  boolean name=id==null?repository.existsByNameIgnoreCase(r.name().trim()):repository.existsByNameIgnoreCaseAndIdNot(r.name().trim(),id);
  boolean slug=id==null?repository.existsBySlug(r.slug()):repository.existsBySlugAndIdNot(r.slug(),id);
  if(name)throw new IllegalArgumentException("An integration with this name already exists"); if(slug)throw new IllegalArgumentException("An integration with this slug already exists");
  if(r.categoryIds()!=null) for(Long cid:r.categoryIds()) if(!categoryRepository.existsById(cid))throw new ResourceNotFoundException("Category not found: "+cid);
 }
 private void apply(Integration x,IntegrationRequest r){
  x.setName(r.name().trim());x.setSlug(r.slug());x.setDescription(r.description());x.setLogoUrl(r.logoUrl());x.setWebsiteUrl(r.websiteUrl());x.setDisplayOrder(r.displayOrder()==null?0:r.displayOrder());x.setActive(r.active()==null||r.active());x.setNew(r.isNew()!=null&&r.isNew());
  if(r.categoryIds()!=null){x.getCategories().clear();x.getCategories().addAll(categoryRepository.findAllById(r.categoryIds()));}
 }
 private IntegrationResponse toResponse(Integration x){
  var cats=x.getCategories().stream().sorted(Comparator.comparing(IntegrationCategory::getDisplayOrder).thenComparing(IntegrationCategory::getId)).map(c->new IntegrationResponse.CategorySummary(c.getId(),c.getName(),c.getSlug())).toList();
  return new IntegrationResponse(x.getId(),x.getName(),x.getSlug(),x.getDescription(),x.getLogoUrl(),x.getWebsiteUrl(),x.getDisplayOrder(),x.isActive(),x.isNew(),cats,x.getCreatedAt(),x.getUpdatedAt());
 }
}
