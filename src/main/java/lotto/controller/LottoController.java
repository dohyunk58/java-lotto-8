package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.util.InputConverter;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoGenerator = new LottoGenerator();
    }

    public void run() {
        // 구매 금액 입력
        int purchaseAmount = getPurchaseAmountWithRetry();
        // 구매 금액에 따라 로또 발행 및 출력
        int lottoCount = purchaseAmount / 1000;
        outputView.printPurchaseCount(lottoCount);
        List<Lotto> lottos = lottoGenerator.generateLottos(lottoCount);
        outputView.printLottos(lottos);
        // 당첨 번호와 보너스 번호 입력
        WinningLotto winningLotto = getWinningLottoRetry();
        // 당첨 통계 계산
        LottoResult lottoResult = calculateResults(lottos, winningLotto);

        outputView.printStatistics(lottoResult);
        outputView.printProfitRate(lottoResult.calculateProfitRate(purchaseAmount));
    }

    private int getPurchaseAmountWithRetry() {
        while (true) {
            try {
                String input = inputView.readPurchaseAmount();
                return InputConverter.parsePurchaseAmount(input);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private WinningLotto getWinningLottoRetry() {
        Lotto winningNumbers = getWinningNumbersRetry(); // 당첨 번호 입력

        while (true) {
            try {
                String input = inputView.readBounusNumber();
                int bonusNumber = InputConverter.parseBonusNumber(input);
                return new WinningLotto(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    // 당첨 번호 입력
    private Lotto getWinningNumbersRetry() {
        while (true) {
            try {
                String input = inputView.readWinningNumbers();
                List<Integer> numbers = InputConverter.parseWinningNumbers(input);
                return new Lotto(numbers);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private LottoResult calculateResults(List<Lotto> lottos, WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();

        for (Lotto lotto : lottos) {
            LottoRank rank = winningLotto.match(lotto);
            lottoResult.addRank(rank);
        }
        return lottoResult;
    }
}
