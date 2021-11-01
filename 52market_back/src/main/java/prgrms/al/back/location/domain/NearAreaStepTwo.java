package prgrms.al.back.location.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "near_area_step_two")
@Getter
@NoArgsConstructor
public class NearAreaStepTwo {
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
    public NearAreaStepTwo(Location start, Location end) {
        this.start = start;
        this.end = end;
    }
}


