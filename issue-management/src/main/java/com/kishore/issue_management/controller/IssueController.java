package com.kishore.issue_management.controller;
import com.kishore.issue_management.entity.Issue;
import com.kishore.issue_management.entity.enums.IssueStatus;
import com.kishore.issue_management.service.IssueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
@RestController
@RequestMapping("/issues")
public class IssueController {
private final IssueService issueService;
public IssueController(IssueService issueService){
    this.issueService=issueService;
}
    @GetMapping("/issues")
    public Page<Issue> getAllIssues(
            @RequestParam(required = false) IssueStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,asc") String sort) {

        String[] sortParts = sort.split(",");
        Sort.Direction direction = Sort.Direction.fromString(sortParts[1]);
        Sort sorting = Sort.by(direction, sortParts[0]);

        Pageable pageable = PageRequest.of(page, size, sorting);

        return issueService.getAllIssues(status, pageable);
    }
    @PutMapping("/admin/issue/{id}/status")
    public Issue updateStatus(@PathVariable Long id, @RequestParam IssueStatus status){
    return
            issueService.updateIssueStatus(id,status);
    }
    @DeleteMapping("/admin/issues/{id}")
    public ResponseEntity<String> deleteIssue(@PathVariable Long id) {
        issueService.deleteIssue(id);
        return ResponseEntity.ok("Issue deleted successfully");
    }
    @PostMapping("/issues")
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {

        Issue savedIssue = issueService.createIssue(issue);
        return ResponseEntity.ok(savedIssue);
    }
@PostMapping("/user/{userId}")
    public ResponseEntity<Issue>
    createIssue(
            @PathVariable Long userId,
            @RequestBody Issue issue){
    Issue createdIssue=issueService.createIssue(userId,issue);
    return new
            ResponseEntity<>(createdIssue,HttpStatus.CREATED);
}




}
