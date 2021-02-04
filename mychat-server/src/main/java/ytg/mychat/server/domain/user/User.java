package ytg.mychat.server.domain.user;

import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-26
 **/
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String account;

    private String nickname;

    private String password;

    private String cover;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Friend>
}
