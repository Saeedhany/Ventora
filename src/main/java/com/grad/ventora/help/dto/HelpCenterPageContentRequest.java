package com.grad.ventora.help.dto;
import jakarta.validation.constraints.Size;
import java.util.*;
public record HelpCenterPageContentRequest(
    @Size(max=250) String heroTitle,
    String heroDescription,
    @Size(max=500) String searchPlaceholder,
    @Size(max=250) String quickAccessTitle,
    String quickAccessDescription,
    List<Map<String,Object>> quickAccessCards,
    @Size(max=250) String faqTitle,
    String faqDescription,
    @Size(max=250) String stillNeedHelpTitle,
    String stillNeedHelpDescription,
    @Size(max=250) String contactSupportLabel,
    List<Map<String,Object>> otherSupportWays,
    @Size(max=250) String moreResourcesTitle,
    String moreResourcesDescription,
    @Size(max=250) String browseResourcesLabel
) {}
