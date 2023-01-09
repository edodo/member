package member.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_member")

public class Member extends AbstractEntityModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String username;

    private String password;
    private String passwordConfirm;

    //@Unique
    private String email;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private MemberRole role;
}
