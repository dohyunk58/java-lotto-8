package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningLottoTest {

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 IllegalArgumentException이 발생한다.")
    @Test
    void createWithDuplicatedBonusNumber() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 6;

        assertThrows(IllegalArgumentException.class, () -> {
            new WinningLotto(winningNumbers, bonusNumber);
        });
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되지 않으면 정상 생성된다.")
    @Test
    void createWithValidBonusNumber() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        assertDoesNotThrow(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        });
    }

    @DisplayName("사용자의 로또롸 비교해 당첨 등수를 반환한다")
    @Test
    void matchUserLotto() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // 1등-꽝까지
        Lotto firstPlaceLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto secondPlaceLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto thirdPlaceLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto fourthPlaceLotto = new Lotto(List.of(1, 2, 3, 4, 7, 8));
        Lotto fifthPlaceLotto = new Lotto(List.of(1, 2, 3, 7, 8, 9));
        Lotto missLotto = new Lotto(List.of(1, 2, 7, 8, 9, 10));

        assertThat(winningLotto.match(firstPlaceLotto)).isEqualTo(LottoRank.FIRST);
        assertThat(winningLotto.match(secondPlaceLotto)).isEqualTo(LottoRank.SECOND);
        assertThat(winningLotto.match(thirdPlaceLotto)).isEqualTo(LottoRank.THIRD);
        assertThat(winningLotto.match(fourthPlaceLotto)).isEqualTo(LottoRank.FOURTH);
        assertThat(winningLotto.match(fifthPlaceLotto)).isEqualTo(LottoRank.FIFTH);
        assertThat(winningLotto.match(missLotto)).isEqualTo(LottoRank.MISS);
    }
}