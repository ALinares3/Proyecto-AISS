package aiss.videominer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aiss.videominer.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, String>{
    
}
