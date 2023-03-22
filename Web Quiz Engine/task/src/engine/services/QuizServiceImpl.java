package engine.services;

import engine.exceptions.QuizNotFound;
import engine.models.requests.NewQuizRequest;
import engine.models.responses.AnswerResponse;
import engine.models.responses.QuizResponse;
import engine.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
    public AnswerResponse sendFeedback(long id, int answer) throws QuizNotFound {
        NewQuizRequest quizById = quizRepository.getQuizById(id);
        return quizById.getAnswer() == answer ?
                AnswerResponse.builder()
                        .success(true)
                        .feedback("Congratulations, you're right!")
                        .build() :
                AnswerResponse.builder()
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
