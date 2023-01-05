package my.board.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class LoginDto {
    private String username;
    private String password;
}