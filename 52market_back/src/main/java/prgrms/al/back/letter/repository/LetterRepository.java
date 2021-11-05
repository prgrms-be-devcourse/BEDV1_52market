package prgrms.al.back.letter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prgrms.al.back.letter.domain.Letter;

import java.util.List;

public interface LetterRepository extends JpaRepository<Letter, Long> {

    @Query("SELECT l FROM letter l WHERE l.sender.id=:id OR l.receiver.id=:id")
    List<Letter> findAllBySenderAndReceiver(@Param("id") Long id);
}
