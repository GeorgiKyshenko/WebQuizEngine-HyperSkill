package engine.services.Impl;

import engine.models.CompletedTask;
import engine.models.Quiz;
import engine.models.UserSecurity;
import engine.models.responses.CompleteTaskPageResponse;
import engine.repositories.CompletedTaskRepository;
import engine.services.CompletedTaskService;
import engine.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompletedTaskServiceImpl implements CompletedTaskService {

    private final CompletedTaskRepository completedTaskRepository;
    private final QuizService quizService;

    @Override
    public void addCompletedTask(long id, UserSecurity user) {
        Quiz quizById = quizService.getQuizById(id).get();
        completedTaskRepository.save(new CompletedTask(quizById.getId(), LocalDateTime.now(), user.getUser()));
    }

    @Override
    public CompleteTaskPageResponse findAllCompletedTasks(int page, UserSecurity user) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("completedAt").descending());
        Page<CompletedTask> completedTasks = completedTaskRepository.findCompletedTaskByUserId(user.getUser().getId(), pageable);
        List<CompletedTask> content = completedTasks.getContent();
        return CompleteTaskPageResponse.builder()
                .totalPages(completedTasks.getTotalPages())
                .totalElements(completedTasks.getTotalElements())
                .last(completedTasks.isLast())
                .first(completedTasks.isFirst())
                .empty(completedTasks.isEmpty())
                .content(content)
                .build();

    }
}
