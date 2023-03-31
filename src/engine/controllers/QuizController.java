package engine.controllers;

import engine.models.Answer;
import engine.models.Quiz;
import engine.models.User;
import engine.models.UserSecurity;
import engine.models.responses.CompleteTaskPageResponse;
import engine.models.responses.FeedbackResponse;
import engine.models.responses.QuizPageResponse;
import engine.services.CompletedTaskService;
import engine.services.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('USER')")
@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;
    private final CompletedTaskService taskService;


    @PostMapping("/api/quiz")
    public ResponseEntity<FeedbackResponse> feedback(@RequestParam int answer) {
        FeedbackResponse feedback = quizService.getFeedback(answer);
        return ResponseEntity.ok().body(feedback);
    }

    @PostMapping
    public ResponseEntity<Quiz> addQuiz(@RequestBody @Valid Quiz quiz, @AuthenticationPrincipal UserSecurity user) {
        log.info("User email: {}, user ID: {}", user.getUser().getEmail(), user.getUser().getId());
        Quiz savedQuiz = quizService.addQuiz(quiz, user);
        return ResponseEntity.ok().body(savedQuiz);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Quiz>> getQuizById(@PathVariable long id) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        return ResponseEntity.ok().body(quiz);
    }

    @GetMapping
    public ResponseEntity<QuizPageResponse> findAllQuizzes(
            @AuthenticationPrincipal UserSecurity user,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int pageSize) {
        log.info("User email: {}, user ID: {}", user.getUser().getEmail(), user.getUser().getId());
        QuizPageResponse quizzes = quizService.findAllQuizzes(page, pageSize);
        return ResponseEntity.ok().body(quizzes);
    }

    @GetMapping("/completed")
    public ResponseEntity<CompleteTaskPageResponse> findAllCompletedTasks(@RequestParam(defaultValue = "0", required = false) int page,
                                                                          @AuthenticationPrincipal UserSecurity user) {
        CompleteTaskPageResponse completeTasks = taskService.findAllCompletedTasks(page, user);
        return ResponseEntity.ok().body(completeTasks);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<FeedbackResponse> solveQuiz(@PathVariable long id, @RequestBody(required = false) Answer answer,
                                                      @AuthenticationPrincipal UserSecurity user) {
        FeedbackResponse feedbackResponse = quizService.feedbackByQuizId(id, answer);
        if (feedbackResponse.isSuccess()) {
            taskService.addCompletedTask(id, user);
        }
        return ResponseEntity.ok().body(feedbackResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable long id, @AuthenticationPrincipal UserSecurity user) {
        quizService.deleteQuizById(id, user);
        return ResponseEntity.status(204).build();
    }
}

