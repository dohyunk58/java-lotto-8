package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {

    private LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        // LottoResult 생성 시 모든 등수가 0개로 초기화
        lottoResult = new LottoResult();
    }

    @DisplayName("당첨 내역을 저장한다.")
    @Test
    void addProfit() {
        lottoResult.addRank(LottoRank.FIRST);

        assertThat(lottoResult.getTotalPrize()).isEqualTo(2_000_000_000L);
    }

    @DisplayName("당첨 내역을 추가하고 수익률을 계산하면 수익률을 반환한다.")
    @Test
    void calculateProfitRate() {
        // 5등(5,000원) 1개 당첨
        lottoResult.addRank(LottoRank.FIFTH);

        int purchaseAmount = 8000;

        // (총 당첨금 5,000원 / 구매금액 8,000원) * 100 = 62.5%
        double profitRate = lottoResult.calculateProfitRate(purchaseAmount);

        // 요구 사항 예시의 "62.5%"
        assertThat(profitRate).isEqualTo(62.5);
    }

    @DisplayName("여러 당첨 내역의 총 상금을 계산한다.")
    @Test
    void calculateTotalPrize() {
        lottoResult.addRank(LottoRank.FIFTH);  // 5,000
        lottoResult.addRank(LottoRank.FIFTH);  // 5,000
        lottoResult.addRank(LottoRank.FOURTH); // 50,000

        long totalPrize = lottoResult.getTotalPrize(); // 총합 60,000

        assertThat(totalPrize).isEqualTo(60_000L);
    }

    @DisplayName("당첨 내역이 없을 때 수익률은 0.0이다.")
    @Test
    void calculateProfitRateWithNoWins() {
        int purchaseAmount = 1000;

        double profitRate = lottoResult.calculateProfitRate(purchaseAmount);

        assertThat(profitRate).isEqualTo(0.0);
    }

}
