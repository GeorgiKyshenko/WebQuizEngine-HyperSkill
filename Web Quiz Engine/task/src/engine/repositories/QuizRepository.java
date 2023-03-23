package engine.repositories;

import engine.exceptions.QuizNotFound;
import engine.models.requests.NewQuizRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Repository
public interface QuizRepository extends JpaRepository<NewQuizRequest, Long> {



    /**
     * Hardcoded methods of the previous stage before connecting to the DB, when QuizRepository was a class.
     * I keep them just in case
     * @return
     */

    List<NewQuizRequest> quizzes = new ArrayList<>();
    default NewQuizRequest addQuiz(String title, String text, List<String> options, Set<Integer> answer) {
        NewQuizRequest quiz = new NewQuizRequest(title, text, options, answer);
        quizzes.add(quiz);
        return quiz;
    }

    default NewQuizRequest getQuizById(long id) throws QuizNotFound {
        try {
            Predicate<? super NewQuizRequest> predicate = quiz -> quiz.getId() == id;
            return quizzes.stream().filter(predicate).findFirst().orElseThrow();
        } catch (Exception exception) {
            throw new QuizNotFound("Quiz not found. Try new ID!");
        }
    }

    default List<NewQuizRequest> getAllQuizzes() {
        return Collections.unmodifiableList(quizzes);
    }
}
