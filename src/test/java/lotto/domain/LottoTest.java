package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
    // 기본 제공 테스트 코드

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // 추가 작성한 테스트 코드

    @DisplayName("Lotto에 6개 숫자가 주어지면 예외가 발생하지 않는다.")
    @Test
    void validateNormalLotto() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        assertDoesNotThrow(() -> new Lotto(numbers));
    }

    @DisplayName("Lotto에 6개가 아닌 숫자가 주어지면 IllegalArgumentException이 발생한다.")
    @Test
    void createLottoByNotSixNumbers() {
        List<Integer> numbersWithFive = List.of(1, 2, 3, 4, 5); // 5개
        List<Integer> numbersWithSeven = List.of(1, 2, 3, 4, 5, 6, 7); // 7개

        assertThrows(IllegalArgumentException.class,
                () -> new Lotto(numbersWithFive));

        assertThrows(IllegalArgumentException.class,
                () -> new Lotto(numbersWithSeven));
    }


}