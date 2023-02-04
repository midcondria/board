package my.board.domain.entity;

import lombok.Data;

@Data
public class Order {

    private Long id;
    private String menuName;
    private int amount;

}
