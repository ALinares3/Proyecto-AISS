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
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import aiss.videominer.model.Comment;

@RestController
@RequestMapping("/videominer/v1")
public class CommentController {
    
    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository){
        this.commentRepository=commentRepository;
    }
    //TODO:Get all y por id, post, put y delete
    @GetMapping("/comments")
	public List<Comment> findAll(){
		return commentRepository.findAll();
	}

	@GetMapping("/comments/{id}")
	public Comment findByOneId(@PathVariable String id){
		Optional<Comment> comment = commentRepository.findById(id);
        return comment.get();
	}

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/comments")
    public Comment createComment(@Valid @RequestBody Comment comment){
        Comment createdComment = commentRepository.save(comment);
        return createdComment;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @PutMapping("/comments/{id}")
    public void updateComment(@Valid @RequestBody Comment updateComment, @PathVariable String id){
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()){
            Comment _comment = comment.get();
            _comment.setText(updateComment.getText());
            _comment.setCreatedOn(updateComment.getCreatedOn());
            commentRepository.save(_comment);
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable String id){
        if(commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        }
    }
}