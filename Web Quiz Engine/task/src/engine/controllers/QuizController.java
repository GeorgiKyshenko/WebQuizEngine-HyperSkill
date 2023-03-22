package engine.controllers;


import engine.exceptions.QuizNotFound;
import engine.models.Answer;
import engine.models.requests.NewQuizRequest;
import engine.models.responses.AnswerResponse;
import engine.models.responses.QuizResponse;
import engine.services.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/quiz")
    public ResponseEntity<QuizResponse> getQuiz() {
        QuizResponse response = quizService.returnQuiz();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/quizzes/{id}/solve")
    // request should be like this -> http://localhost:8080/api/quizzes/1/solve?answer=2  with question mark after quiz!!!
    public ResponseEntity<AnswerResponse> feedback(@PathVariable long id, @RequestBody(required = false) Answer answer) throws QuizNotFound {
//        log.info("answers - " + answer.getAnswer().toString());
        AnswerResponse response = quizService.sendFeedback(id, answer);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/quizzes")
    public ResponseEntity<NewQuizRequest> insertNewQuiz(@RequestBody @Valid NewQuizRequest newQuiz) {
        NewQuizRequest savedQuiz = quizService.save(newQuiz);
//        log.info(Arrays.toString(new String[]{"quizAnswers - " + newQuiz.getAnswer().toString()}));
        return ResponseEntity.ok().body(savedQuiz);
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<NewQuizRequest> getQuiz(@PathVariable long id) throws QuizNotFound {
        NewQuizRequest quiz = quizService.findQuizById(id);
        return ResponseEntity.ok().body(quiz);
    }

    @GetMapping("/quizzes")
    public ResponseEntity<List<NewQuizRequest>> allQuizzes() {
        List<NewQuizRequest> listOfQuizzes = quizService.retrieveALlQuizzes();
        return ResponseEntity.ok().body(listOfQuizzes);
    }

}
