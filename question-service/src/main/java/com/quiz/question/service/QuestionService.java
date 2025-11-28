package com.quiz.question.service;

import com.quiz.question.model.Question;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private Map<Integer, Question> questionDB = new HashMap<>();
    private Integer questionIdCounter = 1;

    public QuestionService() {
        // Sample questions
        questionDB.put(1, new Question(1, "What is Java?", "Language", "Platform", "Both", "None", "Both", 1));
        questionDB.put(2, new Question(2, "What is Spring?", "Framework", "Library", "Tool", "None", "Framework", 1));
        questionDB.put(3, new Question(3, "What is SQL?", "Language", "Tool", "Both", "None", "Language", 2));
    }

    public List<Question> getQuestionsByQuizId(Integer quizId) {
        return questionDB.values().stream()
                .filter(q -> q.getQuizId().equals(quizId))
                .collect(Collectors.toList());
    }

    public Question addQuestion(Question question) {
        question.setId(++questionIdCounter);
        questionDB.put(question.getId(), question);
        return question;
    }

    public Question getQuestionById(Integer questionId) {
        return questionDB.get(questionId);
    }
}
