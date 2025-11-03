package lotto.util;

import java.util.ArrayList;
import java.util.List;

public class InputConverter {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String DELIMITER = ",";

    public static Integer parsePurchaseAmount(String input) {
        int amount;
        try {
            amount = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "구매 금액은 유효한 숫자여야 합니다.");
        }
        return amount;
    }

    public static List<Integer> parseWinningNumbers(String input) {
        validateStringFormat(input);

        String[] parts = input.split(DELIMITER);
        validateNumberCount(parts);

        return parseNumbers(parts);
    }

    private static void validateStringFormat(String input) {
        if (input == null || input.trim().isBlank()) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 공백이 될 수 없습니다.");
        }
        if (input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 "+DELIMITER+"로 끝날 수 없습니다.");
        }
    }

    private static void validateNumberCount(String[] parts) {
        if (parts.length != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 6개여야 합니다.");
        }
    }

    private static List<Integer> parseNumbers(String[] parts) {
        List<Integer> numbers = new ArrayList<>();
        try {
            for (String part : parts) {
                numbers.add(Integer.parseInt(part));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 유효한 숫자여야 합니다.");
        }
        return numbers;
    }

    public static int parseBonusNumber(String bonusNumberInput) {
        try {
            return Integer.parseInt(bonusNumberInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "보너스 번호는 유효한 숫자여야 합니다.");
        }
    }
}
