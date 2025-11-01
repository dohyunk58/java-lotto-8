package lotto.domain;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto winningNumbers, int bonusNumber) {
        if(winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
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
