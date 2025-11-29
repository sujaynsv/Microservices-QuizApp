package com.quiz.quiz.service;

import com.quiz.quiz.client.QuestionClient;
import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.repository.QuizRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionClient questionClient;

    
    
    @CircuitBreaker(
    		name="questionServiceCB",
    		fallbackMethod="getQuestionsWithFallback"
    )
    public Quiz getQuizWithQuestions(Integer quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (quiz.isPresent()) {
            Quiz quizData = quiz.get();
            // Feign usage
            quizData.setQuestions(questionClient.getQuestionsByQuizId(quizId));
            return quizData;
        }
        return null;
    }

    public Quiz getQuiz(Integer quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        return quiz.orElse(null);
    }

    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz updateQuiz(Integer id, Quiz quiz) {
        Optional<Quiz> existing = quizRepository.findById(id);
        if (existing.isPresent()) {
            quiz.setId(id);
            return quizRepository.save(quiz);
        }
        return null;
    }

    public void deleteQuiz(Integer id) {
        quizRepository.deleteById(id);
    }
    
    
    public Quiz getQuestionsWithFallback(Integer quizId, Exception ex) {
    	System.out.println("Circuit Brekaer Trigerred! Question Service not available");
    	
    	Quiz quiz=quizRepository.findById(quizId).orElse(null);
    	
    	if(quiz!=null) {
    		quiz.setQuestions(new ArrayList<>());
    	}
    	return quiz;
    }
}
