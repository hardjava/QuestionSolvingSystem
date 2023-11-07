package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionDTO {
    private int questionID;
    private String categoryID;
    private String questionText;
    private String answer;
    private String commentary;
}
