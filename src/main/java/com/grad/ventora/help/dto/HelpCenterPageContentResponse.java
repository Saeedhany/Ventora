package com.grad.ventora.help.dto;
import java.time.LocalDateTime;
import java.util.*;
public record HelpCenterPageContentResponse(Long id,String heroTitle,String heroDescription,
    String searchPlaceholder,String quickAccessTitle,String quickAccessDescription,
    List<Map<String,Object>> quickAccessCards,String faqTitle,String faqDescription,
    String stillNeedHelpTitle,String stillNeedHelpDescription,String contactSupportLabel,
    List<Map<String,Object>> otherSupportWays,String moreResourcesTitle,
    String moreResourcesDescription,String browseResourcesLabel,
    LocalDateTime createdAt,LocalDateTime updatedAt) {}
