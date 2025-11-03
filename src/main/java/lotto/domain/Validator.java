package lotto.domain;

public class Validator {

    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String DELIMITER = ",";

    public static void validatePurchaseAmount(String input) {
        int amount;

        try {
            amount = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "구매 금액은 유효한 숫자여야 합니다.");
        }

        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_PREFIX + "구매 금액은 1000원 이상이어야 합니다.");
        }

        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_PREFIX + "구매 금액은 1000원 단위여야 합니다.");
        }
    }

    public static void validateWinningNumbers(String input) {
        String[] parts = splitByDelimiter(input);

        validateNumberCount(parts);
    }

    private static String[] splitByDelimiter(String input) {
        if (input == null || input.trim().isBlank()) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 공백이 될 수 없습니다.");
        }
        if (input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 "+DELIMITER+"로 끝날 수 없습니다.");
        }
        return input.split(DELIMITER);
    }

    private static void validateNumberCount(String[] parts) {
        if (parts.length != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 6개여야 합니다.");
        }
    }


}
