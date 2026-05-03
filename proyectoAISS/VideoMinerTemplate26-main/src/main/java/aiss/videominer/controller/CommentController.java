package aiss.videominer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aiss.videominer.repository.CommentRepository;
import aiss.videominer.repository.VideoRepository;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import aiss.videominer.exception.CommentNotFoundException;
import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;

@RestController
@RequestMapping("/videominer/v1")
public class CommentController {

    @Autowired
    private final VideoRepository videoRepository;
    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(VideoRepository videoRepository, CommentRepository commentRepository) {
        this.videoRepository = videoRepository;
        this.commentRepository = commentRepository;
    }

    //TODO:Get all y por id, post, put y delete
    @GetMapping("/comments")
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @GetMapping("/comments/{id}")
    public Comment findByOneId(@PathVariable String id) throws CommentNotFoundException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (!comment.isPresent()) {
            throw new CommentNotFoundException();
        }
        return comment.get();
    }

    @GetMapping("/videos/{id}/comments")
    public List<Comment> findCommentsOfVideo(@PathVariable String id) throws VideoNotFoundException {
        Optional<Video> videoOptional = videoRepository.findById(id);
        if (!videoOptional.isPresent()) {
            throw new VideoNotFoundException();
        }
        Video video = videoOptional.get();
        return video.getComments();
    }

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/comments")
    public Comment createComment(@Valid @RequestBody Comment comment) {
        Comment createdComment = commentRepository.save(comment);
        return createdComment;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @PutMapping("/comments/{id}")
    public void updateComment(@Valid @RequestBody Comment updateComment, @PathVariable String id) throws CommentNotFoundException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (!comment.isPresent()) {
            throw new CommentNotFoundException();
        }
        Comment _comment = comment.get();
        _comment.setText(updateComment.getText());
        _comment.setCreatedOn(updateComment.getCreatedOn());
        commentRepository.save(_comment);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable String id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        }
    }
}
