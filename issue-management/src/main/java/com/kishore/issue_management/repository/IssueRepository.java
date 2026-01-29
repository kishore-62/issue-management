package com.kishore.issue_management.repository;
import com.kishore.issue_management.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IssueRepository extends JpaRepository<Issue,Long>{
}
