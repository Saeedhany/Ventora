package com.grad.ventora.homepage.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.util.List;

/** Content payload for one Home Page section. Only fields used by the selected section type are applied. */
public record HomePageSectionContentRequest(
        @Size(max = 250) String title,
        String body,
        @Size(max = 100) String primaryCtaLabel,
        @Size(max = 500) String primaryCtaUrl,
        @Size(max = 100) String secondaryCtaLabel,
        @Size(max = 500) String secondaryCtaUrl,
        @Size(max = 1000) String imageUrl,
        @Size(max = 250) String imageAltText,
        @Valid List<FeatureItem> features,
        @Valid List<StatisticItem> statistics,
        @Valid List<BenefitItem> benefits,
        @Valid List<TestimonialItem> testimonials,
        @Size(max = 250) String tagline,
        @Size(max = 250) String newsletterTitle,
        @Size(max = 250) String newsletterDescription,
        @Size(max = 150) String newsletterButtonLabel,
        @Size(max = 500) String copyrightText,
        @Valid List<FooterLinkItem> links
) {
    public record FeatureItem(@Size(max = 150) String title, String description,
                              @Size(max = 100) String icon, Integer displayOrder) {}
    public record StatisticItem(@Size(max = 80) String value, @Size(max = 150) String label,
                                Integer displayOrder) {}
    public record BenefitItem(@Size(max = 150) String title, String description,
                              @Size(max = 100) String icon, Integer displayOrder) {}
    public record TestimonialItem(@Size(max = 150) String personName,
                                  @Size(max = 150) String roleOrCompany, String quote,
                                  @Size(max = 1000) String photoUrl, Integer displayOrder) {}
    public record FooterLinkItem(@Size(max = 120) String label, @Size(max = 500) String url,
                                 @Size(max = 100) String groupName, Integer displayOrder) {}
}
