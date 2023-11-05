package m3.common.enums;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatisticEnum {

    ID_AUTHORIZE_STANDALONE(101, "Зашел Стандале"),
    ID_AUTHORIZE_VK(100, "Зашел через ВК"),
    ID_BUY_HEALTH(700, "Купил жизни"),
    ID_BUY_HUMMER(400, "Купил молоток"),
    ID_BUY_LIGHTNING(500, "Купил молнию"),
    ID_BUY_LOOSE_TURNS(610, "Купил +10 ходов"),
    ID_BUY_SHUFFLE(600, "Купил вихрь"),
    ID_BUY_VK_MONEY(300, "Купил ВК голоса"),
    ID_EXIT_GAME(703, "Вышел на карту сам"),
    ID_FINISH_PLAY(702, "Выиграл уровень"),
    ID_HUMMER_USE(201, "Использовал молоток"),
    ID_LEVEL_UP(800, "Перешел на следующий уровень"),
    ID_LIGHTNING_USE(202, "Использовал молнию"),
    ID_LOGOUT(102, "Вышел из приложения"),
    ID_LOOSE_GAME(704, "Проиграл"),
    ID_OPEN_CHEST(900, "Открыл сундук"),
    ID_SHUFFLE_USE(203, "Использовал вихрь"),
    ID_START_PLAY(701, "Начал уровень");

    @JsonValue
    private final int id;
    private final String title;
}
