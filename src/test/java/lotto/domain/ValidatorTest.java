package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ValidatorTest {

    // 구매 금액 입력값 검증

    @DisplayName("유효한 1000원단위의 양수는 예외를 발생시키지 않는다.")
    @Test
    void validatePurchaseAmount_Success() {
        String validAmount = "8000";

        assertDoesNotThrow(() -> {
            Validator.validatePurchaseAmount(validAmount);
        });
    }

    @DisplayName("구매 금액이 정수형이 아닌 경우 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "1000a", " ", "", "1.1", "1000.0"})
    void validatePurchaseAmount_NotInteger(String input) {
        assertThatThrownBy(() -> Validator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구매 금액이 1000원 미만인 경우 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"999", "0", "-1000"})
    void validatePurchaseAmount_LessThan1000(String input) {
        assertThatThrownBy(() -> Validator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구매 금액이 1000원 단위가 아닌 경우 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"1001", "1500"})
    void validatePurchaseAmount_NotMultipleOf1000(String input) {
        assertThatThrownBy(() -> Validator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // 당첨 번호 입력값 검증

    @DisplayName("유효한 당첨 번호(6개, 쉼표 구분)는 예외를 발생시키지 않는다.")
    @Test
    void validateWinningNumbers_Success() {
        String winningNumbers = "1,2,3,4,5,6";

        assertDoesNotThrow(() -> {
            Validator.validateWinningNumbers(winningNumbers);
        });
    }

    @DisplayName("당첨 번호가 6개가 아닌 경우 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void validateWinningNumbers_InvalidCount(String input) {
        assertThatThrownBy(() -> Validator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호 형식이 올바르지 않은 경우(빈 값, 공백, 쉼표로 끝남) IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "1,2,3,4,5,"})
    void validateWinningNumbers_InvalidFormat(String input) {
        assertThatThrownBy(() -> Validator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
