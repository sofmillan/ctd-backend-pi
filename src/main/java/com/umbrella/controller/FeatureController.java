package com.umbrella.controller;

import com.umbrella.dto.response.FeatureResponseDto;
import com.umbrella.service.IFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feature")
public class FeatureController {

    private final IFeatureService featureService;
    @GetMapping()
    public List<FeatureResponseDto> getFeatures(){
        return featureService.getFeatures();
    }
}
