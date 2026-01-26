package lotto.controller;

import lotto.domain.*;
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
        PurchaseAmount purchaseAmount = getPurchaseAmountRetry();
        // 구매 금액에 따라 로또 발행 및 출력
        int lottoCount = purchaseAmount.getLottoCount();
        outputView.printPurchaseCount(lottoCount);
        List<Lotto> lottos = lottoGenerator.generateLottos(lottoCount);
        outputView.printLottos(lottos);
        // 당첨 번호와 보너스 번호 입력
        WinningLotto winningLotto = getWinningLottoRetry();
        // 당첨 통계 계산
        LottoResult lottoResult = calculateResults(lottos, winningLotto);

        outputView.printStatistics(lottoResult);
        outputView.printProfitRate(lottoResult.calculateProfitRate(purchaseAmount.getAmount()));
    }

    private PurchaseAmount getPurchaseAmountRetry() {
        PurchaseAmount purchaseAmount = null;
        while (purchaseAmount == null) {
            purchaseAmount = processPurchaseAmount();
        }
        return purchaseAmount;
    }

    private PurchaseAmount processPurchaseAmount() {
        try {
            String input = inputView.readPurchaseAmount();
            int parsedAmount = InputConverter.parsePurchaseAmount(input);
            return new PurchaseAmount(parsedAmount);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return null;
        }
    }

    private Lotto getWinningNumbersRetry() {
        Lotto winningNumbers = null;
        while (winningNumbers == null) {
            winningNumbers = processWinningNumbers();
        }
        return winningNumbers;
    }

    private Lotto processWinningNumbers() {
        try {
            String input = inputView.readWinningNumbers();
            List<Integer> numbers = InputConverter.parseWinningNumbers(input);
            return new Lotto(numbers);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return null;
        }
    }

    private WinningLotto getWinningLottoRetry() {
        Lotto winningNumbers = getWinningNumbersRetry();

        WinningLotto winningLotto = null;
        while (winningLotto == null) {
            winningLotto = processBonusNumber(winningNumbers);
        }
        return winningLotto;
    }

    private WinningLotto processBonusNumber(Lotto winningNumbers) {
        try {
            String input = inputView.readBonusNumber();
            int bonusNumber = InputConverter.parseBonusNumber(input);
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return null;
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
