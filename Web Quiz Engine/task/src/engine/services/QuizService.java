package engine.services;

import engine.exceptions.QuizNotFound;
import engine.models.Answer;
import engine.models.requests.NewQuizRequest;
import engine.models.responses.AnswerResponse;
import engine.models.responses.QuizResponse;

import java.util.List;

public interface QuizService {
    QuizResponse returnQuiz();

    AnswerResponse sendFeedback(long id, Answer answer) throws QuizNotFound;

    NewQuizRequest save(NewQuizRequest quiz);

    NewQuizRequest findQuizById(long id) throws QuizNotFound;

    List<NewQuizRequest> retrieveALlQuizzes();
}
