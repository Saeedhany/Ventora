package com.grad.ventora.help.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@Entity
@Table(name="help_categories")
@Getter @Setter @NoArgsConstructor
public class HelpCategory {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true, length=100)
    private String name;
    @Column(nullable=false, unique=true, length=120)
    private String slug;
    @Column(length=500)
    private String description;
    @Column(nullable=false)
    private Integer displayOrder=0;
    @Column(nullable=false)
    private Boolean active=true;
    @Column(nullable=false, updatable=false)
    private LocalDateTime createdAt;
    @Column(nullable=false)
    private LocalDateTime updatedAt;
    @PrePersist
    void prePersist(){ LocalDateTime now=LocalDateTime.now(); createdAt=now; updatedAt=now; }
    @PreUpdate
    void preUpdate(){ updatedAt=LocalDateTime.now(); }
}
