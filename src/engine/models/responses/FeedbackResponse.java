package engine.models.responses;

import lombok.Value;

@Value
public class FeedbackResponse {
    boolean success;
    String feedback;
}
