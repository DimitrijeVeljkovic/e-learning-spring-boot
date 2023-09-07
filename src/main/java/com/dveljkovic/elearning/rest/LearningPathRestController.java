package com.dveljkovic.elearning.rest;

import com.dveljkovic.elearning.entity.LearningPath;
import com.dveljkovic.elearning.service.LearningPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learning-paths")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class LearningPathRestController {
    private LearningPathService lpService;

    @Autowired
    public LearningPathRestController(LearningPathService lps) {
        lpService = lps;
    }

    @GetMapping("")
    public List<LearningPath> findAll() {
        return lpService.findAll();
    }
}
