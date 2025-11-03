package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ValidatorTest {

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
}
