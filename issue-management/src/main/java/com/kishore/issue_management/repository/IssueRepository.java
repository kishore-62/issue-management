package com.kishore.issue_management.repository;
import com.kishore.issue_management.entity.Issue;
import com.kishore.issue_management.entity.enums.IssueStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IssueRepository extends JpaRepository<Issue,Long>{

    Page<Issue> findByStatus(IssueStatus status,Pageable pageable);
}
