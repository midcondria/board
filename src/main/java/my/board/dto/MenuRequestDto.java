package my.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MenuRequestDto {

    private List<MenuDto> menuList;
    private String name;

}
