package persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class InsertQuestionDTO {
    private String categoryID;
    private String questionText;
    private String answer;
    private String commentary;

    public InsertQuestionDTO(String categoryID, String questionText, String answer, String commentary) {
        this.categoryID = categoryID;
        this.questionText = questionText;
        this.answer = answer;
        this.commentary = commentary;
    }
}
