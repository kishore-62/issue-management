package com.kishore.issue_management.controller.admin;

import com.kishore.issue_management.entity.Issue;
import com.kishore.issue_management.entity.enums.IssueStatus;
import com.kishore.issue_management.service.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/issues")
public class AdminIssueController {

    private final IssueService issueService;

    public AdminIssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PutMapping("/{issueId}/status")
    public ResponseEntity<Issue> updateIssueStatus(
            @PathVariable Long issueId,
            @RequestParam IssueStatus status) {

        Issue updatedIssue = issueService.adminUpdateIssueStatus(issueId, status);
        return ResponseEntity.ok(updatedIssue);
    }
}