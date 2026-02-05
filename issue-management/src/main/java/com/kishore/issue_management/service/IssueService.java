package com.kishore.issue_management.service;
import com.kishore.issue_management.entity.Issue;
import com.kishore.issue_management.entity.User;
import com.kishore.issue_management.entity.enums.IssueStatus;
import com.kishore.issue_management.repository.IssueRepository;
import com.kishore.issue_management.repository.UserRepository;
import org.springframework.stereotype.Service;
public class IssueService {
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    public IssueService(IssueRepository issueRepository,UserRepository userRepository){
        this.issueRepository=issueRepository;
        this.userRepository=userRepository;}
    public Issue createIssue(Long userId, Issue issue){
         User user=userRepository.findById(userId).orElseThrow(() ->new
        RuntimeException("User not found"));
        issue.setCreatedBy(user);
        issue.setStatus(IssueStatus.OPEN);
        return issueRepository.save(issue);
    }

}
