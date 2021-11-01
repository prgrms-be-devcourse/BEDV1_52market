package prgrms.al.back.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.domain.NearAreaStepOne;
import prgrms.al.back.location.domain.NearAreaStepTwo;

import java.util.List;

@Repository
public interface NearAreaStepTwoRepository extends JpaRepository<NearAreaStepTwo, Long> {

    @Query("SELECT n FROM near_area_step_two as n WHERE n.start=:location")
    List<NearAreaStepTwo> findNearLocation(@Param("location") Location location);
}
