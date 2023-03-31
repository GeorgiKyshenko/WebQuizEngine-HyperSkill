package engine.services.Impl;

import engine.models.Answer;
import engine.models.Quiz;
import engine.models.UserSecurity;
import engine.models.responses.FeedbackResponse;
import engine.models.responses.QuizPageResponse;
import engine.repositories.JpaQuizRepository;
import engine.services.QuizService;
import engine.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final JpaQuizRepository quizRepository;
    private final UserService userService;


    @Override
    public FeedbackResponse getFeedback(int answer) {
        final FeedbackResponse feedbackResponse;
        if (answer == 2) {
            feedbackResponse = new FeedbackResponse(true, "Congratulations, you're right!");
        } else {
            feedbackResponse = new FeedbackResponse(false, "Wrong answer! Please, try again.");
        }
        return feedbackResponse;
    }

    @Override
    public Quiz addQuiz(Quiz quiz, UserSecurity user) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userEmail = authentication.getName();
//        Optional<User> userByEmail = userService.findUserByEmail(userEmail);
//        long userId = userByEmail.get().getId();
        quiz.setUser(user.getUser());
        return quizRepository.save(quiz);
    }

    @Override
    public Optional<Quiz> getQuizById(long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            return quiz;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public QuizPageResponse findAllQuizzes(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Quiz> quizzes = quizRepository.findAll(pageable);
        List<Quiz> content = quizzes.getContent();
        return QuizPageResponse.builder()
                .totalPages(quizzes.getTotalPages())
                .totalElements(quizzes.getTotalElements())
                .last(quizzes.isLast())
                .first(quizzes.isFirst())
                .sort(quizzes.getSort())
                .number(quizzes.getNumber())
                .numberOfElements(quizzes.getNumberOfElements())
                .size(quizzes.getSize())
                .empty(quizzes.isEmpty())
                .pageable(quizzes.getPageable())
                .content(content)
                .build();
    }

    @Override
    public FeedbackResponse feedbackByQuizId(long id, Answer answer) {
        final FeedbackResponse successfulFeedback = new FeedbackResponse(true, "Congratulations, you're right!");
        final FeedbackResponse failedFeedback = new FeedbackResponse(false, "Wrong answer! Please, try again.");
        List<Integer> quizAnswers;

        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            quizAnswers = quiz.get().getAnswer();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (quizAnswers == null && answer == null) {
            return successfulFeedback;
        }

        if (quizAnswers == null) {
            if (answer.getAnswer().isEmpty()) {
                return successfulFeedback;
            } else {
                return failedFeedback;
            }
        }

        if (answer == null) {
            if (quizAnswers.isEmpty()) {
                return successfulFeedback;
            } else {
                return failedFeedback;
            }
        }
        if (quizAnswers.isEmpty() && answer.getAnswer().isEmpty()) {
            return successfulFeedback;
        }

        if (Arrays.equals(quizAnswers.stream().sorted().toArray(), answer.getAnswer().stream().sorted().toArray())) {
            return successfulFeedback;
        }
        return failedFeedback;
    }

    @Override
    public void deleteQuizById(long id, UserSecurity user) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userEmail = authentication.getName();
//        Optional<User> userByEmail = userService.findUserByEmail(userEmail);
//        long userId = userByEmail.get().getId();
        Optional<Quiz> quizById = quizRepository.findById(id);
        if (quizById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else if (quizById.get().getUser().getId() != user.getUser().getId()) {
            log.info("USER QUIZ ID: {}, AUTHENTICATED USER: {}", quizById.get().getUser().getId(), user.getUser().getId());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } else {
            quizRepository.deleteById(id);
        }

    }
}
