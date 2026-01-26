package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum LottoRank {
    FIRST(2_000_000_000L, "6개 일치", 6, false),
    SECOND(30_000_000L, "5개 일치, 보너스 볼 일치", 5, true),
    THIRD(1_500_000L, "5개 일치", 5, false),
    FOURTH(50_000L, "4개 일치", 4, false),
    FIFTH(5_000L, "3개 일치", 3, false),
    MISS(0L, "낙첨", 0, false);

    private final long prizeMoney;
    private final String description;
    private final int matchCount;
    private final boolean bonusRequired;

    LottoRank(long prizeMoney, String description, int matchCount, boolean bonusRequired) {
        this.prizeMoney = prizeMoney;
        this.description = description;
        this.matchCount = matchCount;
        this.bonusRequired = bonusRequired;
    }

    public static LottoRank of(int matchCount, boolean hasBonus) {
        // 2등은 5개, 보너스 true로 유일한 케이스
        if (matchCount == 5 && hasBonus) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> !rank.bonusRequired) // 보너스 필요 없는 등수
                .filter(rank -> rank.matchCount == matchCount) // 일치 개수가 같은 것
                .findFirst()
                .orElse(MISS); // 찾으면 반환 못 찾으면 MISS
    }

    public static List<LottoRank> getRanks() {
        return List.of(FIFTH, FOURTH, THIRD, SECOND, FIRST);
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }

    public String getDescription() {
        return description;
    }
}
