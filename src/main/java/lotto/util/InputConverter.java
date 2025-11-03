package lotto.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputConverter {

    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String DELIMITER = ",";
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    public static Integer parsePurchaseAmount(String input) {
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

        return amount;
    }

    public static List<Integer> parseWinningNumbers(String input) {
        validateWinningNumbers(input);

        String[] parts = input.split(DELIMITER);
        // 6개 숫자인지 검증
        validateNumberCount(parts);

        List<Integer> numbers = parseNumbers(parts);
        // 숫자 범위 검증
        validateNumberRange(numbers);
        // 중복 검증
        validateNoDuplicates(numbers);

        return numbers;
    }

    private static void validateWinningNumbers(String input) {
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
                numbers.add(Integer.parseInt(part.trim()));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 유효한 숫자여야 합니다.");
        }
        return numbers;
    }

    private static void validateNumberRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException(ERROR_PREFIX + "로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }

    private static void validateNoDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_PREFIX + "당첨 번호는 중복될 수 없습니다.");
        }
    }
}
