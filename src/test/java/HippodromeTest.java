import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {

    @Test
    public void validExceptionIfNullInConstructor_PassedNullInConstructor_ValidExceptionWasThrown() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                }
        );
    }

    @Test
    public void validMessageIfNullInConstructor_PassedNullInConstructor_ValidMessage() {
        try {
            new Hippodrome(null);
            fail();
        } catch (IllegalArgumentException e) {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        new Hippodrome(null);
                    }
            );

            assertEquals("Horses cannot be null.", exception.getMessage());
        }
    }

    @Test
    public void validExceptionIfEmptyListInConstructor_PassedEmptyListInConstructor_ValidExceptionWasThrown() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(new ArrayList<>());
                }
        );
    }

    @Test
    public void validMessageIfEmptyListInConstructor_PassedEmptyListInConstructor_ValidMessage() {
        try {
            new Hippodrome(new ArrayList<>());
            fail();
        } catch (IllegalArgumentException e) {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        new Hippodrome(new ArrayList<>());
                    }
            );

            assertEquals("Horses cannot be empty.", exception.getMessage());
        }
    }

    @Test
    public void methodGetHorsesReturnCorrectObjectsInTheCorrectSequence_ListOfDifferentHorses_ValidList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            horses.add(new Horse("horse №" + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void checkingThatTheMethodMoveIsCalledOnAllHorses_PassedList50MockHorses_MethodWasCalledForAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            horses.add(mockHorse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses){
            Mockito.verify(horse).move();
        }
    }

    @Test
    public void returnOfTheHorseWithTheLongestDistance_PassedListHorses_CorrectReturn() {
        List<Horse> horses = List.of(
                new Horse("Буцефал", 2.4, 1),
                new Horse("Туз Пик", 2.5, 2),
                new Horse("Зефир", 2.6, 3),
                new Horse("Пожар", 2.7, 4),
                new Horse("Лобстер", 2.8, 5),
                new Horse("Пегас", 2.9, 6),
                new Horse("Вишня", 3, 7)
        );

        Hippodrome hippodrome = new Hippodrome(horses);

        assertSame(horses.get(6), hippodrome.getWinner());
    }
}
