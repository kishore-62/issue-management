package com.kishore.issue_management.controller;

import com.kishore.issue_management.entity.Comment;
import com.kishore.issue_management.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{issueId}")
    public Comment addComment(
            @PathVariable Long issueId,
            @RequestParam Long userId,
            @RequestBody String content) {

        return commentService.addComment(issueId, userId, content);
    }

    @GetMapping("/{issueId}")
    public List<Comment> getComments(
            @PathVariable Long issueId) {

        return commentService.getCommentsByIssue(issueId);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(
            @PathVariable Long id) {

        commentService.deleteComment(id);
    }
}