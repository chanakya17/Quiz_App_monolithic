package com.github.chanakya17.Quiz.app.Contoller;

import com.github.chanakya17.Quiz.app.Model.QuestionWrapper;
import com.github.chanakya17.Quiz.app.Model.Response;
import com.github.chanakya17.Quiz.app.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ, @RequestParam String title  ){
        return quizService.createQuizQuestions(category,numQ,title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> returnScore(@PathVariable int id,@RequestBody List<Response> responses){
        return quizService.calculateScore(id,responses);
    }

}
