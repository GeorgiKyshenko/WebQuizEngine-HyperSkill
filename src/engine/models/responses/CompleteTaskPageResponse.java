package engine.models.responses;

import engine.models.CompletedTask;
import engine.models.Quiz;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompleteTaskPageResponse {
    private int totalPages;
    private long totalElements;
    private boolean last;
    private boolean first;
    private boolean empty;
    private List<CompletedTask> content;
}
