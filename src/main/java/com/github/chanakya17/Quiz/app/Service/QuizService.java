package com.github.chanakya17.Quiz.app.Service;


import com.github.chanakya17.Quiz.app.Model.Question;
import com.github.chanakya17.Quiz.app.Model.QuestionWrapper;
import com.github.chanakya17.Quiz.app.Model.Quiz;
import com.github.chanakya17.Quiz.app.Model.Response;
import com.github.chanakya17.Quiz.app.Repo.QuestionRepo;
import com.github.chanakya17.Quiz.app.Repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    QuizRepo quizRepo;

    public ResponseEntity<String> createQuizQuestions(String category, int numQ, String title){
        List<Question> questionList = questionRepo.getRandomQuestions(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Quiz created", HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id){
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questionsForDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q: questionsForDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateScore(int id, List<Response> responses) {
        System.out.println("submit");
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        int right=0;
        int i=0;
        for(Response r : responses){
            if(r.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
