package my.board.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import my.board.domain.user.UserRoleEnum;

import javax.persistence.Entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "users")
public class User extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

//    @OneToMany
//    private List<Post> postList = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
