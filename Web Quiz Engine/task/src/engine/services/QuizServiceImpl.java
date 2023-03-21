package engine.services;

import engine.models.AnswerResponse;
import engine.models.QuizResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    @Override
    public QuizResponse returnQuiz() {
        return QuizResponse.builder()
                .title("The Java Logo")
                .text("What is depicted on the Java logo?")
                .options(List.of("Robot", "Tea leaf", "Cup of coffee", "Bug")).build();
    }

    @Override
    public AnswerResponse sendFeedback(int answer) {
        return answer == 2 ?
                AnswerResponse.builder()
                        .success(true)
                        .feedback("Congratulations, you're right!")
                        .build() :
                AnswerResponse.builder()
                        .success(false)
                        .feedback("Wrong answer! Please, try again.")
                        .build();
    }
}
