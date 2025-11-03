package lotto.domain;

public class PurchaseAmount {
    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_PREFIX = "[ERROR] ";

    private final int amount;

    public PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_PREFIX + "구매 금액은 1000원 이상이어야 합니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_PREFIX + "구매 금액은 1000원 단위여야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }

    public int getLottoCount() {
        return amount / LOTTO_PRICE;
    }
}
