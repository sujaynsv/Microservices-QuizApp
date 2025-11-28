package com.quiz.question.controller;

import com.quiz.question.model.Question;
import com.quiz.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsByQuizId(@PathVariable Integer quizId) {
        return questionService.getQuestionsByQuizId(quizId);
    }

    @GetMapping("/{questionId}")
    public Question getQuestionById(@PathVariable Integer questionId) {
        return questionService.getQuestionById(questionId);
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping
    public Question addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PutMapping("/{questionId}")
    public Question updateQuestion(@PathVariable Integer questionId, @RequestBody Question question) {
        return questionService.updateQuestion(questionId, question);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable Integer questionId) {
        questionService.deleteQuestion(questionId);
    }
}
