package com.grad.ventora.homepage.dto;
import jakarta.validation.constraints.Size;
public record HomePagePublishRequest(@Size(max=500) String releaseNote, @Size(max=150) String actor) {}
