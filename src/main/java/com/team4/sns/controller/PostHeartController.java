package com.team4.sns.controller;

import com.team4.sns.controller.dto.HeartDto;
import com.team4.sns.service.PostHeartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PostHeartController {

    private PostHeartService postHeartService;

    @PostMapping("/{userId}/like")
    public void insertHeart(@PathVariable int userId, @RequestBody HeartDto heartDTO) {
        if (postHeartService.isValidateHeart(heartDTO)) {
            postHeartService.deleteHeart(heartDTO);
        }
        else {
            postHeartService.insertHeart(heartDTO);
        }
    }
}
