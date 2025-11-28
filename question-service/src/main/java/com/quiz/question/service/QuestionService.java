package com.quiz.question.service;

import com.quiz.question.model.Question;
import com.quiz.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getQuestionsByQuizId(Integer quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question getQuestionById(Integer questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        return question.orElse(null);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question updateQuestion(Integer id, Question question) {
        Optional<Question> existing = questionRepository.findById(id);
        if (existing.isPresent()) {
            question.setId(id);
            return questionRepository.save(question);
        }
        return null;
    }

    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }
}
