package prgrms.al.back.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prgrms.al.back.location.domain.NearAreaStepOne;
import prgrms.al.back.location.domain.NearAreaStepTwo;

@Repository
public interface NearAreaStepTwoRepository extends JpaRepository<NearAreaStepTwo, Long> {
}
