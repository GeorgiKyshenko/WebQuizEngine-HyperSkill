package engine.services;

import engine.models.Answer;
import engine.models.Quiz;
import engine.models.User;
import engine.models.UserSecurity;
import engine.models.responses.FeedbackResponse;
import engine.models.responses.QuizPageResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface QuizService {
//    Quiz addQuiz(Quiz quiz);

    FeedbackResponse getFeedback(int answer);

    Quiz addQuiz(Quiz quiz, UserSecurity user);

    Optional<Quiz> getQuizById(long id);

    QuizPageResponse findAllQuizzes(int page, int pageSize);

    FeedbackResponse feedbackByQuizId(long id, Answer answer);

    void deleteQuizById(long id, UserSecurity user);
}
