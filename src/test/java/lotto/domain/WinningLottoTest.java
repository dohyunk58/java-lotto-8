package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningLottoTest {

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 IllegalArgumentException이 발생한다.")
    @Test
    void createWithDuplicatedBonusNumber() {
        // given
        // 당첨 번호 (1, 2, 3, 4, 5, 6)
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 6; // 당첨 번호와 중복되는 보너스 번호

        // when & then
        // 이 객체를 생성할 때 예외가 터져야 함
        assertThrows(IllegalArgumentException.class, () -> {
            new WinningLotto(winningNumbers, bonusNumber);
        });
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되지 않으면 정상 생성된다.")
    @Test
    void createWithValidBonusNumber() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7; // 중복되지 않는 보너스 번호

        // when & then
        assertDoesNotThrow(() -> { // 예외가 발생하지 않아야 함
            new WinningLotto(winningNumbers, bonusNumber);
        });
    }
}