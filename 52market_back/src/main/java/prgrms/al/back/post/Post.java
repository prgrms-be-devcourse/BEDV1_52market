package prgrms.al.back.post;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "post")
public class Post {
    @Id
    private Long id;
}
