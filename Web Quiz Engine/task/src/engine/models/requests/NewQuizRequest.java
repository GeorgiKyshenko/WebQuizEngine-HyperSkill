package engine.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import engine.models.Answer;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j(topic = "NewQuizRequest - class")
//@Component
public class NewQuizRequest {
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
    private List<String> options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> answer;

//    @PostConstruct
//    public void checkValue() {
//        log.info(Arrays.toString(answer));
//    }
}
