package com.kishore.issue_management.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.kishore.issue_management.entity.Issue;
import com.kishore.issue_management.entity.User;
import com.kishore.issue_management.entity.enums.IssueStatus;
import com.kishore.issue_management.repository.IssueRepository;
import com.kishore.issue_management.repository.UserRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service

public class IssueService {
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    public Issue adminUpdateIssueStatus(Long issueId,IssueStatus status){
        Issue issue=issueRepository.findById(issueId).orElseThrow(()->new
                RuntimeException("Issue not found"));
        issue.setStatus(status);
        return issueRepository.save(issue);
    }

    public Page<Issue> getAllIssues(IssueStatus status, Pageable pageable) {

        if (status != null) {
            return issueRepository.findByStatus(status, pageable);
        }

        return issueRepository.findAll(pageable);
    }
    public Issue createIssue(Issue issue) {

        issue.setStatus(IssueStatus.OPEN); // default status

        return issueRepository.save(issue);
    }
    public Page<Issue> getIssuesByStatus(IssueStatus status,Pageable pageable){
        return
                issueRepository.findByStatus(status,pageable);
    }
    public Issue updateIssueStatus(Long issueId,IssueStatus status){
        Issue issue=issueRepository.findById(issueId).orElseThrow(()->new RuntimeException("Issue not found"));
        issue.setStatus(status);
        return issueRepository.save(issue);
    }
    public void deleteIssue(Long id) {

        if (!issueRepository.existsById(id)) {
            throw new RuntimeException("Issue not found");
        }

        issueRepository.deleteById(id);
    }
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
