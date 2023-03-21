package engine.controllers;


import engine.models.AnswerResponse;
import engine.models.QuizResponse;
import engine.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<QuizResponse> getQuiz() {
        QuizResponse response = quizService.returnQuiz();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping()
    // request should be like this -> http://localhost:8889/api/quiz?answer=3  with question mark after quiz!!!
    public ResponseEntity<AnswerResponse> feedback(@RequestParam int answer) {
        AnswerResponse response = quizService.sendFeedback(answer);
        return ResponseEntity.ok().body(response);
    }

}
