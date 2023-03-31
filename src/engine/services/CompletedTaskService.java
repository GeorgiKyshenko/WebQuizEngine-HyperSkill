package engine.services;

import engine.models.CompletedTask;
import engine.models.User;
import engine.models.UserSecurity;
import engine.models.responses.CompleteTaskPageResponse;

import java.util.List;

public interface CompletedTaskService {
    void addCompletedTask(long id, UserSecurity user);
    CompleteTaskPageResponse findAllCompletedTasks(int page, UserSecurity user);
}
