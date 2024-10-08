package com.github.chanakya17.Quiz.app.Service;

import com.github.chanakya17.Quiz.app.Model.Question;
import com.github.chanakya17.Quiz.app.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class QuestionService {
	@Autowired
    QuestionRepo questionRepo;

	public ResponseEntity<List<Question>> getAllQuestions() {

		return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questionRepo.save(question);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	public ResponseEntity<String> deleteQuestion(int id){
		questionRepo.deleteById(id);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}
