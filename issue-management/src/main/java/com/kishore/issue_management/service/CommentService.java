package com.kishore.issue_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kishore.issue_management.entity.Comment;
import com.kishore.issue_management.entity.Issue;
import com.kishore.issue_management.entity.User;

import com.kishore.issue_management.repository.CommentRepository;
import com.kishore.issue_management.repository.IssueRepository;
import com.kishore.issue_management.repository.UserRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          IssueRepository issueRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
    }

    public Comment addComment(Long issueId,
                              Long userId,
                              String content) {

        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() ->
                        new RuntimeException("Issue not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Comment comment = new Comment();

        comment.setContent(content);
        comment.setIssue(issue);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByIssue(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }

    public void deleteComment(Long id) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Comment not found"));

        commentRepository.delete(comment);
    }
}