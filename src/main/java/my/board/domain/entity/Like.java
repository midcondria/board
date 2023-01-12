package my.board.domain.entity;

import lombok.Getter;

import javax.persistence.*;
import java.lang.reflect.Member;

@Entity(name = "likes")
@Getter
public class Like {

    @Id @GeneratedValue
    private Long id;




}
