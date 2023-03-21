package engine.controllers;


import engine.exceptions.QuizNotFound;
import engine.models.requests.NewQuizRequest;
import engine.models.responses.AnswerResponse;
import engine.models.responses.QuizResponse;
import engine.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/quiz")
    public ResponseEntity<QuizResponse> getQuiz() {
        QuizResponse response = quizService.returnQuiz();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/quiz")
    // request should be like this -> http://localhost:8080/api/quiz?answer=2  with question mark after quiz!!!
    public ResponseEntity<AnswerResponse> feedback(@RequestParam int answer) {
        AnswerResponse response = quizService.sendFeedback(answer);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/quizzes")
    public ResponseEntity<NewQuizRequest> insertNewQuiz(@RequestBody NewQuizRequest newQuiz) {
        NewQuizRequest savedQuiz = quizService.save(newQuiz);
        return ResponseEntity.ok().body(savedQuiz);
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<NewQuizRequest> getQuiz(@PathVariable long id) throws QuizNotFound {
        NewQuizRequest quiz = quizService.findQuizById(id);
        return ResponseEntity.ok().body(quiz);
    }

}
