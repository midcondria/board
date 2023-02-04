package my.board.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostSaveDto {

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    private String content;

    private Long id;

    private int amount;

    public PostSaveDto(String author, Long id, int amount) {
        this.author = author;
        this.title = "123";
        this.id = id;
        this.amount = amount;
    }
}
