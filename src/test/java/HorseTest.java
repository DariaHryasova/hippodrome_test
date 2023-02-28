import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    Horse mockHorse = Mockito.mock(Horse.class);

    @Test
    public void validExceptionIfNullInName_PassedNullInName_ValidExceptionWasThrown() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, 1, 1);
                }
        );
    }

    @Test
    public void validMessageIfNullInName_PassedNullInName_ValidMessage() {
        try {
            new Horse(null, 1, 1);
            fail();
        } catch (IllegalArgumentException e) {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        new Horse(null, 1, 1);
                    }
            );

            assertEquals("Name cannot be null.", exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void validExceptionIfBlankInName_PassedBlankInName_ValidExceptionWasThrown(String argument) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(argument, 1, 1);
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void validMessageIfBlankInName_PassedBlankInName_ValidMessage(String argument) {
        try {
            new Horse(argument, 1, 1);
            fail();
        } catch (IllegalArgumentException e) {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        new Horse(argument, 1, 1);
                    }

            );

            assertEquals("Name cannot be blank.", exception.getMessage());
        }
    }

    @Test
    public void validExceptionIfNegativeNumInSpeed_PassedNegativeNumInSpeed_ValidExceptionWasThrown() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("name", -1, 1);
                }
        );
    }

    @Test
    public void validMessageIfNegativeNumInSpeed_PassedNegativeNumInSpeed_ValidMessage() {
        try {
            new Horse("name", -1, 1);
            fail();
        } catch (IllegalArgumentException e) {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        new Horse("name", -1, 1);
                    }

            );

            assertEquals("Speed cannot be negative.", exception.getMessage());
        }
    }

    @Test
    public void validExceptionIfNegativeNumInDistance_PassedNegativeNumInDistance_ValidExceptionWasThrown() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("name", 1, -1);
                }
        );
    }

    @Test
    public void validMessageIfNegativeNumInDistance_PassedNegativeNumInDistance_ValidMessage() {
        try {
            new Horse("name", 1, -1);
            fail();
        } catch (IllegalArgumentException e) {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        new Horse("name", 1, -1);
                    }

            );

            assertEquals("Distance cannot be negative.", exception.getMessage());
        }
    }

    @Test
    public void returnStringEqualsNameInMethodGetName_PassedStringName_ValidString(){
        String expectedName = "name";
        Horse horse = new Horse(expectedName, 1, 1);

        assertEquals(expectedName, horse.getName());
    }

    @Test
    public void returnNumberEqualsSpeedInMethodGetSpeed_PassedNumberSpeed_ValidNumber(){
        double expectedSpeed = 1;
        Horse horse = new Horse("name", expectedSpeed, 1);

        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    public void returnNumberEqualsDistanceInMethodGetDistance_PassedNumberDistance_ValidNumber(){
        double expectedDistance = 1;
        Horse horse = new Horse("name", 1, expectedDistance);

        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    public void returnNullNumberIfConstructorHasTwoParametersInMethodGetDistance_PassedTwoParameters_ReturnNullNumber(){
        double expectedDistance = 0;
        Horse horse = new Horse("name", 1);

        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    public void checkingGetRandomDoubleMethodCallWithParametersInMethodMove_PassedParameters_TheCallIsCorrect(){
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 1, 1);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "0.1, 1.1",
            "0.2, 1.2"
    })
    public void checkingTheCorrectAssignmentOfTheDistance_PassedTwoParameters_TheValuesAreCorrect(double mockRandomDouble, double result) {
        Horse horse = new Horse("name", 1, 1);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockRandomDouble);
            horse.move();
            assertEquals(result, horse.getDistance());
        }
    }
}

