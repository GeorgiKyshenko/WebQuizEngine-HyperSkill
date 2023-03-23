package engine.services;

import engine.exceptions.QuizNotFound;
import engine.models.requests.Answer;
import engine.models.requests.NewQuizRequest;
import engine.models.responses.AnswerResponse;
import engine.models.responses.QuizResponse;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    QuizResponse returnQuiz();

    AnswerResponse sendFeedback(long id, Answer answer) throws QuizNotFound;

    NewQuizRequest save(NewQuizRequest quiz);

    Optional<NewQuizRequest> findQuizById(long id) throws QuizNotFound;

    List<NewQuizRequest> retrieveALlQuizzes();
}
