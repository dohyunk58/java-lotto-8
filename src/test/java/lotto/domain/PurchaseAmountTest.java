package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchaseAmountTest {
    @DisplayName("구매 금액이 1000원 미만인 경우 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 0, -1000})
    void validateAmount_LessThan1000(int amount) {
        assertThatThrownBy(() -> new PurchaseAmount(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구매 금액이 1000원 단위가 아닌 경우 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {1001, 1500})
    void validateAmount_NotMultipleOf1000(int amount) {
        assertThatThrownBy(() -> new PurchaseAmount(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
