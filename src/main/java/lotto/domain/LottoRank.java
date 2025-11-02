package lotto.domain;

public enum LottoRank {
    FIRST(2_000_000_000L),
    SECOND(30_000_000L),
    THIRD(1_500_000L),
    FOURTH(50_000L),
    FIFTH(5_000L),
    MISS(0L);

    private final long prizeMoney;

    LottoRank(long prizeMoney) {
        this.prizeMoney = prizeMoney;
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

    public long getPrizeMoney() {
        return prizeMoney;
    }
}
