package com.grad.ventora.about.dto;
import java.time.LocalDateTime; import java.util.List;
public record AboutPageResponse(Long id,String heroTitle,String heroDescription,String heroValueOne,String heroValueTwo,String heroValueThree,String heroValueFour,
 String storyTitle,String storyDescription,String storyVideoUrl,String storyVideoDuration,String missionTitle,String missionDescription,String visionTitle,String visionDescription,boolean published,
 List<StatisticResponse> statistics,List<ValueResponse> values,List<TeamMemberResponse> teamMembers,LocalDateTime createdAt,LocalDateTime updatedAt){
 public record StatisticResponse(Long id,String value,String label,int displayOrder){}
 public record ValueResponse(Long id,String title,String description,int displayOrder){}
 public record TeamMemberResponse(Long id,String name,String role,String photoUrl,String linkedInUrl,int displayOrder){}
}
