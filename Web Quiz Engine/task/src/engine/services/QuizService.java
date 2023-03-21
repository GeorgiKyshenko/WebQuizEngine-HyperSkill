package engine.services;

import engine.models.AnswerResponse;
import engine.models.QuizResponse;

public interface QuizService {
    QuizResponse returnQuiz();
    AnswerResponse sendFeedback(int answer);

}
