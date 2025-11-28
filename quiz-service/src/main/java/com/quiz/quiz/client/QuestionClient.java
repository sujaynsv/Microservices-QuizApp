package com.quiz.quiz.client;

import com.quiz.quiz.model.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "question-service")
public interface QuestionClient {

    @GetMapping("/questions/quiz/{quizId}")
    List<Question> getQuestionsByQuizId(@PathVariable("quizId") Integer quizId);
}
