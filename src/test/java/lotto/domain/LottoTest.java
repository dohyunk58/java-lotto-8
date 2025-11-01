package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("Lotto가 특정 번호를 포함하고 있는지 여부를 반환한다.")
    @Test
    void containsNumber() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.contains(3)).isTrue();   // 포함된 경우
        assertThat(lotto.contains(7)).isFalse();  // 포함되지 않은 경우
    }

    @DisplayName("Lotto가 정답 번호와 일치하는 숫자의 개수를 반환한다.")
    @Test
    void calculateMatchCount() {
        List<Integer> numbersOfLotto = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(numbersOfLotto);

        List<Integer> numbersMatchSix = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> numbersMatchFive = List.of(1, 2, 3, 4, 5, 7);
        List<Integer> numbersMatchFour = List.of(1, 2, 3, 4, 7, 8);
        List<Integer> numbersMatchThree = List.of(1, 2, 3, 7, 8, 9);
        List<Integer> numbersMatchTwo = List.of(1, 2, 7, 8, 9, 10);
        List<Integer> numbersMatchOne = List.of(1, 7, 8, 9, 10, 11);
        List<Integer> numbersMatchZero = List.of(7, 8, 9, 10, 11, 12);

        assertThat(lotto.countMatchingNumbers(numbersMatchSix)).isEqualTo(6);
        assertThat(lotto.countMatchingNumbers(numbersMatchFive)).isEqualTo(5);
        assertThat(lotto.countMatchingNumbers(numbersMatchFour)).isEqualTo(4);
        assertThat(lotto.countMatchingNumbers(numbersMatchThree)).isEqualTo(3);
        assertThat(lotto.countMatchingNumbers(numbersMatchTwo)).isEqualTo(2);
        assertThat(lotto.countMatchingNumbers(numbersMatchOne)).isEqualTo(1);
        assertThat(lotto.countMatchingNumbers(numbersMatchZero)).isEqualTo(0);
    }
}