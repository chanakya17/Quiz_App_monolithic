package com.github.chanakya17.Quiz.app.Repo;

import com.github.chanakya17.Quiz.app.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
}
