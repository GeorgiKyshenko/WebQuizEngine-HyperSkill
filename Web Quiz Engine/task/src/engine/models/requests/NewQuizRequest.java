package engine.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Slf4j(topic = "NewQuizRequest - class")
@Entity
@Table(name = "quizzes")
public class NewQuizRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    @NotBlank
    @NotEmpty
    private String title;
    @NotBlank
    @NotEmpty
    private String text;
    @Size(min = 2)
    @NotNull
//    @CollectionTable(name = "options")
    @ElementCollection
    private List<String> options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    private Set<Integer> answer;

    public NewQuizRequest(String title, String text, List<String> options, Set<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    //    @PostConstruct
//    public void checkValue() {
//        log.info(Arrays.toString(answer));
//    }
}
