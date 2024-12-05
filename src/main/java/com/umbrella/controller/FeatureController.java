package com.umbrella.controller;

import com.umbrella.dto.request.FeatureRequestDto;
import com.umbrella.dto.response.FeatureResponseDto;
import com.umbrella.exception.ResourceNotFoundException;
import com.umbrella.service.IFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/{id}")
    public ResponseEntity<String> editFeature(
            @PathVariable Integer id,
            @RequestBody FeatureRequestDto feature){
        featureService.updateFeature(id,feature);
        return ResponseEntity.ok("{\"message\": \"Feature updated successfully\"}");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeature(@PathVariable Integer id) throws ResourceNotFoundException {
        featureService.deleteFeature(id);
        return ResponseEntity.ok("{\"message\": \"Feature deleted successfully\"}");
    }
    @PostMapping
    public ResponseEntity<FeatureResponseDto> createFeature(@RequestBody FeatureRequestDto feature){
        FeatureResponseDto featureReturn = featureService.createFeature(feature);
        if(featureReturn==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(featureReturn);
        }
    }
}
