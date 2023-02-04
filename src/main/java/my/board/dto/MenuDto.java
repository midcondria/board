package my.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuDto {

    private Long id;
    private String menuName;
    private int amount;
}
