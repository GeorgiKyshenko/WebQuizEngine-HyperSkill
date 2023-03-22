package engine.services;

import engine.exceptions.QuizNotFound;
import engine.models.Answer;
import engine.models.requests.NewQuizRequest;
import engine.models.responses.AnswerResponse;
import engine.models.responses.QuizResponse;
import engine.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public QuizResponse returnQuiz() {
        return QuizResponse.builder()
                .title("The Java Logo")
                .text("What is depicted on the Java logo?")
                .options(List.of("Robot", "Tea leaf", "Cup of coffee", "Bug")).build();
    }

    @Override
    public AnswerResponse sendFeedback(long id, Answer answer) throws QuizNotFound {
        NewQuizRequest quiz = quizRepository.getQuizById(id);
        Set<Integer> quizAnswers = quiz.getAnswer();


        if (quizAnswers == null && answer == null) {
            return AnswerResponse.builder()
                    .success(true)
                    .feedback("Congratulations, you're right!")
                    .build();
        }

        if (quizAnswers == null) {
            if (answer.getAnswer().isEmpty()) {
                return AnswerResponse.builder()
                        .success(true)
                        .feedback("Congratulations, you're right!")
                        .build();
            } else {
                return AnswerResponse.builder()
                        .success(false)
                        .feedback("Wrong answer! Please, try again.")
                        .build();
            }
        }

        if (answer == null) {
            if (quizAnswers.isEmpty()) {
                return AnswerResponse.builder()
                        .success(true)
                        .feedback("Congratulations, you're right!")
                        .build();
            } else {
                return AnswerResponse.builder()
                        .success(false)
                        .feedback("Wrong answer! Please, try again.")
                        .build();
            }
        }
        if (quizAnswers.isEmpty() && answer.getAnswer().isEmpty()) {
            return AnswerResponse.builder()
                    .success(true)
                    .feedback("Congratulations, you're right!")
                    .build();
        }

        if (Arrays.equals(quizAnswers.stream().sorted().toArray(), answer.getAnswer().stream().sorted().toArray())) {
            return AnswerResponse.builder()
                    .success(true)
                    .feedback("Congratulations, you're right!")
                    .build();
        }
        return AnswerResponse.builder()
                .success(false)
                .feedback("Wrong answer! Please, try again.")
                .build();
    }


    @Override
    public NewQuizRequest save(NewQuizRequest quiz) {
        return quizRepository.addQuiz(quiz.getTitle(), quiz.getText(), quiz.getOptions(), quiz.getAnswer());
    }

    @Override
    public NewQuizRequest findQuizById(long id) throws QuizNotFound {
        return quizRepository.getQuizById(id);
    }

    @Override
    public List<NewQuizRequest> retrieveALlQuizzes() {
        return quizRepository.getAllQuizzes();
    }
}
