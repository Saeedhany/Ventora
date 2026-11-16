package com.grad.ventora.about.dto;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import java.util.List;
public record AboutPageRequest(
 @NotBlank @Size(max=200) String heroTitle, @Size(max=3000) String heroDescription,
 @Size(max=100) String heroValueOne, @Size(max=100) String heroValueTwo, @Size(max=100) String heroValueThree, @Size(max=100) String heroValueFour,
 @Size(max=200) String storyTitle, @Size(max=4000) String storyDescription, @Size(max=500) String storyVideoUrl, @Size(max=20) String storyVideoDuration,
 @Size(max=200) String missionTitle, @Size(max=3000) String missionDescription,
 @Size(max=200) String visionTitle, @Size(max=3000) String visionDescription,
 Boolean published, @Valid List<StatisticRequest> statistics, @Valid List<ValueRequest> values, @Valid List<TeamMemberRequest> teamMembers
) {
 public record StatisticRequest(@NotBlank @Size(max=40) String value,@NotBlank @Size(max=100) String label,Integer displayOrder){}
 public record ValueRequest(@NotBlank @Size(max=120) String title,@Size(max=1000) String description,Integer displayOrder){}
 public record TeamMemberRequest(@NotBlank @Size(max=150) String name,@NotBlank @Size(max=120) String role,@Size(max=500) String photoUrl,@Size(max=500) String linkedInUrl,Integer displayOrder){}
}
