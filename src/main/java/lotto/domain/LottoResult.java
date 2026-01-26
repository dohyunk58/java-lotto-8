package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {

    private final Map<LottoRank, Integer> counts;

    public LottoResult() {
        this.counts = new EnumMap<>(LottoRank.class);

        // 모든 LottoRank를 0으로 초기화
        for(LottoRank rank : LottoRank.values()) {
            counts.put(rank, 0);
        }
    }

    public void addRank(LottoRank rank) {
        counts.put(rank, counts.get(rank) + 1);
    }

    public long getTotalPrize() {
        long totalPrize = 0L;

        for(Map.Entry<LottoRank, Integer> entry : counts.entrySet()) {
            LottoRank rank = entry.getKey();
            int count = entry.getValue();

            totalPrize += rank.getPrizeMoney() * count;
        }
        return totalPrize;
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = getTotalPrize();

        if(totalPrize == 0) {
            return 0.0;
        }

        double rate = (double) totalPrize / purchaseAmount * 100;
        return Math.round(rate*10.0) / 10.0; // 둘째 자리에서 반올림(ex. 33.33 -> 333.3 -> 333 -> 33.3)
    }

    public Map<LottoRank, Integer> getResults() {
        return Collections.unmodifiableMap(counts);
    }
}
