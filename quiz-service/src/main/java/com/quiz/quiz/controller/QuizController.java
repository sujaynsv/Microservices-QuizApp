package com.quiz.quiz.controller;

import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/{quizId}")
    public Quiz getQuiz(@PathVariable Integer quizId) {
        return quizService.getQuiz(quizId);
    }

    @GetMapping("/{quizId}/questions")
    public Quiz getQuizWithQuestions(@PathVariable Integer quizId) {
        return quizService.getQuizWithQuestions(quizId);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @PostMapping
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    @PutMapping("/{quizId}")
    public Quiz updateQuiz(@PathVariable Integer quizId, @RequestBody Quiz quiz) {
        return quizService.updateQuiz(quizId, quiz);
    }

    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable Integer quizId) {
        quizService.deleteQuiz(quizId);
    }
}
