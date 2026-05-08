package aiss.videominer.controller;

import java.util.List;
import java.util.Optional;

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

import aiss.videominer.exception.CaptionNotFoundException;
import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.VideoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/videominer/v1")
public class CaptionController {

    @Autowired
    private final VideoRepository videoRepository;
    @Autowired
    private final CaptionRepository captionRepository;

    public CaptionController(VideoRepository videoRepository, CaptionRepository captionRepository) {
        this.videoRepository = videoRepository;
        this.captionRepository = captionRepository;
    }

    // TODO: Get all y por id, post, put y delete
    @GetMapping("/captions")
    public List<Caption> findAll() {
        return captionRepository.findAll();
    }

    @GetMapping("/captions/{id}")
    public Caption findByOneId(@PathVariable Long id) throws CaptionNotFoundException {
        Optional<Caption> caption = captionRepository.findById(id);
        if (!caption.isPresent()) {
            throw new CaptionNotFoundException();
        }
        return caption.get();
    }

    @GetMapping("/videos/{id}/captions")
    public List<Caption> findCaptionsOfVideo(@PathVariable Long id) throws VideoNotFoundException {
        Optional<Video> videoOptional = videoRepository.findById(id);
        if (!videoOptional.isPresent()) {
            throw new VideoNotFoundException();
        }
        Video video = videoOptional.get();
        return video.getCaptions();
    }

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/captions")
    public Caption createCaption(@Valid @RequestBody Caption caption) {
        Caption createdCaption = captionRepository.save(caption);
        return createdCaption;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @PutMapping("/captions/{id}")
    public void updateCaption(@Valid @RequestBody Caption updateCaption, @PathVariable Long id) throws CaptionNotFoundException {
        Optional<Caption> caption = captionRepository.findById(id);
        if (!caption.isPresent()) { //Hacer excepción
            throw new CaptionNotFoundException();
        }
        Caption _caption = caption.get();
        _caption.setName(updateCaption.getName());
        _caption.setLanguage(updateCaption.getLanguage());
        captionRepository.save(_caption);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/captions/{id}")
    public void deleteCaption(@PathVariable Long id) {
        if (captionRepository.existsById(id)) {
            captionRepository.deleteById(id);
        }
    }
}
