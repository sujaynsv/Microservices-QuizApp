package com.quiz.quiz.service;

import com.quiz.quiz.client.QuestionClient;
import com.quiz.quiz.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class QuizService {

    @Autowired
    private QuestionClient questionClient;

    private Map<Integer, Quiz> quizDB = new HashMap<>();

    public QuizService() {
        quizDB.put(1, new Quiz(1, "Java Quiz", "Test your Java knowledge", null));
        quizDB.put(2, new Quiz(2, "SQL Quiz", "Test your SQL knowledge", null));
    }

    public Quiz getQuizWithQuestions(Integer quizId) {
        Quiz quiz = quizDB.get(quizId);
        if (quiz != null) {
            // Call Question Service via Feign to get questions
            quiz.setQuestions(questionClient.getQuestionsByQuizId(quizId));
        }
        return quiz;
    }

    public Quiz getQuiz(Integer quizId) {
        return quizDB.get(quizId);
    }

    public Quiz addQuiz(Quiz quiz) {
        Integer newId = quizDB.size() + 1;
        quiz.setId(newId);
        quizDB.put(newId, quiz);
        return quiz;
    }
}
