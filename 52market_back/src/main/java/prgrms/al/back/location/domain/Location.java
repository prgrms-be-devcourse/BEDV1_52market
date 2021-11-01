package prgrms.al.back.location.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "location")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    private String name;

    private Double N; // x좌표

    private Double E; // y좌표

    @Builder
    public Location(String name, Double n, Double e) {
        this.name = name;
        N = n;
        E = e;
    }

    public Location(String name) {
        this.name = name;
    }
}
