package lotto.domain;

import static lotto.exception.ExceptionMessage.ERROR_NEGATIVE_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {-3, -100})
    @DisplayName("0 미만의 숫자로 Money 객체를 생성할 수 없다.")
    void create_withUnderZero(int value) {
        assertThatThrownBy(() -> new Money(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_NEGATIVE_VALUE.getMessage());
    }

    @Test
    @DisplayName("countMaxNumberOfItemToBuy 메소드는 주어진 가격의 물건의 최대 구매 가능 개수를 계산한다.")
    void countMaxNumberOfItemToBuy() {
        assertThat(new Money(12500).countMaxNumberOfItemToBuy(1000)).isEqualTo(12);
    }

    @Test
    @DisplayName("take 메소드는 현재 가지고 있는 금액에서 원하는 만큼의 금액을 가져온 후 남은 금액을 반환한다.")
    void take() {
        Money money = new Money(10000);
        Money left = money.take(3000);

        assertThat(left).isEqualTo(new Money(7000));
    }
}