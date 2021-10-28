package prgrms.al.back.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.domain.NearAreaStepOne;
import java.util.List;

@Repository
public interface NearAreaStepOneRepository extends JpaRepository<NearAreaStepOne, Long> {

    @Query("SELECT n FROM near_area_step_one as n WHERE n.start=:location")
    List<NearAreaStepOne> findNearLocation(@Param("location") Location location);
}
