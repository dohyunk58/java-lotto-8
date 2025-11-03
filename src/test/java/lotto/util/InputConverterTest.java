package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputConverterTest {

    // 로또 구매 입력값 변환 parsePurchaseAmount

    @DisplayName("유효한 구매 금액 문자열을 int로 변환한다.")
    @Test
    void parsePurchaseAmount_Success() {
        String validAmount = "8000";

        int result = InputConverter.parsePurchaseAmount(validAmount);

        assertThat(result).isEqualTo(8000);
    }

    @DisplayName("구매 금액이 정수형이 아닌 경우 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "1000a", " ", "", "1.1", "1000.0"})
    void validatePurchaseAmount_NotInteger(String input) {
        assertThatThrownBy(() -> InputConverter.parsePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // 당첨 번호 입력값 변환 parseWinningNumbers

    @DisplayName("유효한 당첨 번호 문자열을 List<Integer>로 변환한다.")
    @Test
    void parseWinningNumbers_Success() {
        String input = "1,2,3,4,5,6";

        assertThat(InputConverter.parseWinningNumbers(input))
                .isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("당첨 번호 형식이 올바르지 않은 경우(빈 값, 공백, 쉼표로 끝남) IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "123456", "1,2,3,4,5,"})
    void parseWinningNumbers_InvalidFormat(String input) {
        assertThatThrownBy(() -> InputConverter.parseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 6개가 아닌 경우 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void parseWinningNumbers_InvalidCount(String input) {
        assertThatThrownBy(() -> InputConverter.parseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 숫자가 아닌 값(문자, 실수)이 포함된 경우 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,a", "1,2,3,4,5,1.5"})
    void parseWinningNumbers_NotNumber(String input) {
        assertThatThrownBy(() -> InputConverter.parseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // 보너스 번호 입력값 변환 parseBonusNumber

    @DisplayName("정상적인 보너스 번호 문자열을 int로 변환한다.")
    @Test
    void parseBonusNumber_Success() {
        String input = "7";

        int result = InputConverter.parseBonusNumber(input);

        assertThat(result).isEqualTo(7);
    }

    @DisplayName("보너스 번호가 정수형이 아닌 경우 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "1.5", "", " ", "7 ", " 7"})
    void parseBonusNumber_NotInteger(String input) {

        assertThatThrownBy(() -> InputConverter.parseBonusNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
