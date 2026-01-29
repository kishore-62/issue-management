package com.kishore.issue_management.entity;
import com.kishore.issue_management.entity.enums.IssueStatus;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "issues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueStatus status;
    @ManyToOne
    @JoinColumn(name ="created_by")
    private User createdBy;
}
