package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {

    @DisplayName("요청한 개수(count)만큼 로또 리스트를 생성한다.")
    @Test
    void generateLottosByCount() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        int count = 8;

        List<Lotto> generatedLottos = lottoGenerator.generateLottos(count);

        assertThat(generatedLottos.size()).isEqualTo(8);

        // 각 로또가 null이 아니며, 6개의 숫자를 가지고 있는지 검증
        assertThat(generatedLottos).allSatisfy(lotto -> {
            assertThat(lotto).isNotNull();
            assertThat(lotto.getNumbers().size()).isEqualTo(6);
        });
    }
}
