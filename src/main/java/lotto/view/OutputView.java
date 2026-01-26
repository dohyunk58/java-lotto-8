package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void printPurchaseCount(int count) {
        System.out.println("\n"+count+"개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> generatedLottos) {
        for(Lotto lotto : generatedLottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printStatistics(LottoResult result) {
        System.out.println("\n당첨 동계");
        System.out.println("---");

        Map<LottoRank, Integer> stats = result.getResults();
        List<LottoRank> Ranks = LottoRank.getRanks();

        for(LottoRank rank : Ranks) {
            System.out.printf(
                    "%s (%,d원) - %d개\n",
                    rank.getDescription(),
                    rank.getPrizeMoney(),
                    stats.get(rank)
            );
        }
    }

    public void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %,.1f%%입니다.\n", profitRate);
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
