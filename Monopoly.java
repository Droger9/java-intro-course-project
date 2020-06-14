import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Monopoly {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random randomNumGenerator = new Random();

        final byte ROWS = 4;
        final byte COLUMNS = 7;
        int randRow = 0;
        int randColumn = 0;
        int lastRow = 3;
        int firstRow = 0;

        int counter = 0;

        String[][] monopolyBoard = {
                         //position         9   10   11   12   13   14    15

                /* position 9*/           {"X", "X", "X", "X", "X", "X", "X"},       //position 15
                /* position 8*/           {"X", " ", " ", " ", " ", " ", "X"},       //position 16
                /* position 7*/           {"X", " ", " ", " ", " ", " ", "X"},       //position 17
                /* position 6*/           {"X", "X", "X", "X", "X", "X", "GO"},       //position 0
        };
                             //position     6    5    4    3    2    1    0 - Start

        System.out.println("GO - Start box");
        System.out.println("T - Trap box");
        System.out.println("P - Party box");
        System.out.println("C - Chance box");
        System.out.println("S - Steal box");

        placeAllBoxesOnBoard(monopolyBoard, counter, ROWS, COLUMNS,
                randRow, randColumn, randomNumGenerator, lastRow, firstRow);

        System.out.println(Arrays.deepToString(monopolyBoard)
                .replace("],", "\n").replace(",", "")
                .replaceAll("[\\[\\]]", " "));
    }

    /**
     * метод който слага трап кутийките в масива
     * @param monopolyBoard дъската
     */
    public static void trapBoxPlacer(String[][] monopolyBoard) {

            for (byte lineNo = 0; lineNo < 4; lineNo++) {

                for (int columnNo = 0; columnNo < 7; columnNo++) {

                    if (monopolyBoard[lineNo][columnNo].equalsIgnoreCase("X")) {

                        monopolyBoard[lineNo][columnNo] = "T";
                }
            }
        }
    }

    /**
     * метод който слага инвест кутийките
     * @param monopolyBoard дъската
     * @param counter брояч
     * @param COLUMNS колони
     * @param randRow случаен ред
     * @param randColumn случайна колона
     * @param randomNumGenerator генерира число
     * @param lastRow последен ред
     * @param fisrtRow първи ред
     */
    public static void investBoxPlacer(String[][] monopolyBoard, int counter, byte COLUMNS,
                                       int randRow, int randColumn, Random randomNumGenerator, int lastRow, int fisrtRow) {
        while (counter < 3) {
            int randomOfTwoInts;

            randomOfTwoInts = new Random().nextBoolean() ? lastRow : fisrtRow;
            randRow = randomOfTwoInts;

            randColumn = randomNumGenerator.nextInt(COLUMNS - 1);

            if (monopolyBoard[randRow][randColumn].equalsIgnoreCase("X")) {

                monopolyBoard[randRow][randColumn] = "I";

                counter++;
            }
        }
    }

    /**
     * метод който слага парти кутийките
     * @param monopolyBoard дъската
     * @param counter брояч
     * @param COLUMNS колони
     * @param randRow случаен ред
     * @param randColumn случайна колона
     * @param randomNumGenerator генерира число
     */
    public static void partyBoxPlacer(String[][] monopolyBoard, int counter, byte ROWS, byte COLUMNS,
                                      int randRow, int randColumn, Random randomNumGenerator) {
        while (counter < 3) {

            randRow = randomNumGenerator.nextInt(ROWS - 1);

            randColumn = randomNumGenerator.nextInt(COLUMNS - 1);

            if (monopolyBoard[randRow][randColumn].equalsIgnoreCase("X")) {

                monopolyBoard[randRow][randColumn] = "P";

                counter++;
            }
        }
    }

    /**
     * метод който слага парти кутийките
     * @param monopolyBoard дъската
     * @param counter брояч
     * @param COLUMNS колони
     * @param randRow случаен ред
     * @param randColumn случайна колона
     * @param randomNumGenerator генерира число
     */
    public static void chanceBoxPlacer(String[][] monopolyBoard, int counter, byte ROWS, byte COLUMNS,
                                       int randRow, int randColumn, Random randomNumGenerator) {
        while (counter < 3) {

            randRow = randomNumGenerator.nextInt(ROWS - 1);

            randColumn = randomNumGenerator.nextInt(COLUMNS - 1);

            if (monopolyBoard[randRow][randColumn].equalsIgnoreCase("X")) {

                monopolyBoard[randRow][randColumn] = "C";

                ++counter;
            }
        }
    }

    public static void stealBoxPlacer (String[][] monopolyBoard, int counter, byte ROWS, byte COLUMNS,
                                       int randRow, int randColumn, Random randomNumGenerator) {
        while (counter < 3) {

            randRow = randomNumGenerator.nextInt(ROWS - 1) + 1;

            randColumn = randomNumGenerator.nextInt(COLUMNS - 1) + 1;

            if (monopolyBoard[randRow][randColumn].equalsIgnoreCase("X")) {

                monopolyBoard[randRow][randColumn] = "S";

                ++counter;
            }
        }
    }

    public static void placeAllBoxesOnBoard (String[][] monopolyBoard, int counter, byte ROWS, byte COLUMNS,
                                             int randRow, int randColumn, Random randomNumGenerator, int lastRow, int firstRow) {

        partyBoxPlacer(monopolyBoard, counter, ROWS, COLUMNS,
                randRow, randColumn, randomNumGenerator);

        chanceBoxPlacer(monopolyBoard, counter, ROWS, COLUMNS,
                randRow, randColumn, randomNumGenerator);

        stealBoxPlacer(monopolyBoard, counter, ROWS, COLUMNS,
                randRow, randColumn, randomNumGenerator);

        investBoxPlacer(monopolyBoard, counter, COLUMNS,
                randRow, randColumn, randomNumGenerator, lastRow, firstRow);

        trapBoxPlacer(monopolyBoard);
    }
}