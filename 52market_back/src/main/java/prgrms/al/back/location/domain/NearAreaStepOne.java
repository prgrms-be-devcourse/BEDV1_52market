package prgrms.al.back.location.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prgrms.al.back.user.domain.User;

import javax.persistence.*;

@Entity(name = "near_area_step_one")
@Getter
@NoArgsConstructor
public class NearAreaStepOne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_location_id", referencedColumnName = "location_id")
    private Location start;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_location_id", referencedColumnName = "location_id")
    private Location end;

    @Builder
    public NearAreaStepOne(Location start, Location end) {
        this.start = start;
        this.end = end;
    }
}
