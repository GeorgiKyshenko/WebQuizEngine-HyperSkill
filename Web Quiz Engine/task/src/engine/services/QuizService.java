package engine.services;

import engine.exceptions.QuizNotFound;
import engine.models.requests.NewQuizRequest;
import engine.models.responses.AnswerResponse;
import engine.models.responses.QuizResponse;

public interface QuizService {
    QuizResponse returnQuiz();

    AnswerResponse sendFeedback(int answer);

    NewQuizRequest save(NewQuizRequest quiz);

    NewQuizRequest findQuizById(long id) throws QuizNotFound;
}
