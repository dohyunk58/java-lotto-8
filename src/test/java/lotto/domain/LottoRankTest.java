package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @DisplayName("일치 개수와 보너스 여부로 정확한 등수를 반환한다.")
    @Test
    void determineRank() {
        assertThat(LottoRank.of(6, false)).isEqualTo(LottoRank.FIRST);

        assertThat(LottoRank.of(5, true)).isEqualTo(LottoRank.SECOND);

        assertThat(LottoRank.of(5, false)).isEqualTo(LottoRank.THIRD);

        assertThat(LottoRank.of(4, false)).isEqualTo(LottoRank.FOURTH);

        assertThat(LottoRank.of(3, false)).isEqualTo(LottoRank.FIFTH);

        assertThat(LottoRank.of(2, false)).isEqualTo(LottoRank.MISS);

        assertThat(LottoRank.of(0, true)).isEqualTo(LottoRank.MISS);
    }
}