package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ObtDTO {
    private int questionID;
    private int optNum;
    private String optText;
}
