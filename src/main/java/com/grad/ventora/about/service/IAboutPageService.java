package com.grad.ventora.about.service;
import com.grad.ventora.about.dto.AboutPageRequest; import com.grad.ventora.about.dto.AboutPageResponse;
public interface IAboutPageService { AboutPageResponse getPublished(); AboutPageResponse getDraft(); AboutPageResponse save(AboutPageRequest request); }
