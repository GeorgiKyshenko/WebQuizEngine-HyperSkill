package engine.repositories;

import engine.exceptions.BusinessException;
import engine.exceptions.QuizNotFound;
import engine.models.Answer;
import engine.models.requests.NewQuizRequest;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Repository
public class QuizRepository {
    private List<NewQuizRequest> quizzes = new ArrayList<>();
    private static long id = 0;

    public NewQuizRequest addQuiz(String title, String text, List<String> options, Set<Integer> answer) {
        NewQuizRequest quiz = new NewQuizRequest(++id, title, text, options, answer);
        quizzes.add(quiz);
        return quiz;
    }

    public NewQuizRequest getQuizById(long id) throws QuizNotFound {
        try {
            Predicate<? super NewQuizRequest> predicate = quiz -> quiz.getId() == id;
            return quizzes.stream().filter(predicate).findFirst().orElseThrow();
        } catch (Exception exception) {
            throw new QuizNotFound("Quiz not found. Try new ID!");
        }
    }

    public List<NewQuizRequest> getAllQuizzes() {
        return Collections.unmodifiableList(quizzes);
    }
}
