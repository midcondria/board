package my.board.dto;

import lombok.Data;

@Data
public class SignupDto {

    private String username;
    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";

}