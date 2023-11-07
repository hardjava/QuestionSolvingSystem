package persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class InsertObtDTO {
    private int questionID;
    private int optNum;
    private String optText;

    public InsertObtDTO(int questionID, int optNum, String optText) {
        this.questionID = questionID;
        this.optNum = optNum;
        this.optText = optText;
    }
}
