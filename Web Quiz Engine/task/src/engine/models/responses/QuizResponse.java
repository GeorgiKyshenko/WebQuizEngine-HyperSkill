package engine.models.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponse {
    private String title;
    private String text;
    private List<String> options;
}
