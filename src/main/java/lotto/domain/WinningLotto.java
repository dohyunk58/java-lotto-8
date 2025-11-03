package lotto.domain;

import static lotto.domain.LottoConfig.*;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        validateRange(bonusNumber);
        validateDuplicate(winningNumbers, bonusNumber);

        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber < MIN_LOTTO_NUMBER || bonusNumber > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ERROR_PREFIX + "로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateDuplicate(Lotto winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_PREFIX + "보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public LottoRank match(Lotto userLotto) {
        int matchCount = userLotto.countMatchingNumbers(this.winningNumbers.getNumbers());

        boolean hasBonus = false;
        if (matchCount == 5) {
            hasBonus = userLotto.contains(this.bonusNumber);
        }

        return LottoRank.of(matchCount, hasBonus);
    }
}
