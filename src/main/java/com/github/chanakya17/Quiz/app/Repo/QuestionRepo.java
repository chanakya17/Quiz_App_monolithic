package com.github.chanakya17.Quiz.app.Repo;

import com.github.chanakya17.Quiz.app.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String Category);
    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY random() LIMIT :numQ", nativeQuery = true)
    List<Question> getRandomQuestions(String category, int numQ);
}
