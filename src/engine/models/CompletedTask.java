package engine.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "completed_tasks")
public class CompletedTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long id;


    @JsonProperty(value = "id")
    private long quizId;

    @Column(name = "completion_date")
    private LocalDateTime completedAt;

    @ManyToOne(targetEntity = User.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    public CompletedTask(long quizId, LocalDateTime completedAt, User user) {
        this.quizId = quizId;
        this.completedAt = completedAt;
        this.user = user;
    }
}
