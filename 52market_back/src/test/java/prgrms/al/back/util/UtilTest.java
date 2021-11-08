package prgrms.al.back.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;

class UtilTest {

    @Test
    void test() {
        System.out.println(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }
}
