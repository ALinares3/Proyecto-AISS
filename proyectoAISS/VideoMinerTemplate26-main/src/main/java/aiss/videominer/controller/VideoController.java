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

import aiss.videominer.exception.VideoNotFoundException;
import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;

@RestController
@RequestMapping("/videominer/v1")
public class VideoController {

    @Autowired
    private final VideoRepository videoRepository;

    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @GetMapping("/videos")
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @GetMapping("/videos/{id}")
    public Video findById(@PathVariable String id) throws VideoNotFoundException{
        Optional<Video> video = videoRepository.findById(id); 
        if(!video.isPresent()){
            throw new VideoNotFoundException();
        }
        return video.orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/videos")
    public Video createVideo(@RequestBody Video video) {
        return videoRepository.save(video);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/videos/{id}")
    public void updateVideo(@RequestBody Video updateVideo, @PathVariable String id) throws VideoNotFoundException{
        Optional<Video> existing = videoRepository.findById(id);
        if (existing.isPresent()) { 
            throw new VideoNotFoundException();
        }
        Video video = existing.get();
            video.setName(updateVideo.getName());
            video.setDescription(updateVideo.getDescription());
            video.setReleaseTime(updateVideo.getReleaseTime());
            video.setAuthor(updateVideo.getAuthor());
            video.setComments(updateVideo.getComments());
            video.setCaptions(updateVideo.getCaptions());
            videoRepository.save(video);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/videos/{id}")
    public void deleteVideo(@PathVariable String id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
        }
    }
}
    
