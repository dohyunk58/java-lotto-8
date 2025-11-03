package lotto.domain;

import java.util.List;

public enum LottoRank {
    FIRST(2_000_000_000L, "6개 일치"),
    SECOND(30_000_000L, "5개 일치, 보너스 볼 일치"),
    THIRD(1_500_000L, "5개 일치"),
    FOURTH(50_000L, "4개 일치"),
    FIFTH(5_000L, "3개 일치"),
    MISS(0L, "");

    private final long prizeMoney;
    private final String description;

    LottoRank(long prizeMoney, String description) {
        this.prizeMoney = prizeMoney;
        this.description = description;
    }

    public static LottoRank of(int matchCount, boolean hasBonus) {
        if (matchCount == 6) {
            return FIRST;
        }

        if (matchCount == 5 && hasBonus) {
            return SECOND;
        }

        if (matchCount == 5) {
            return THIRD;
        }

        if (matchCount == 4) {
            return FOURTH;
        }

        if (matchCount == 3) {
            return FIFTH;
        }

        return MISS;
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
