package prgrms.al.back.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user")
public class User {
    @Id
    private Long id;
}
