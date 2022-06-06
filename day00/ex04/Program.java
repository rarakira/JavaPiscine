import java.util.Scanner;

public class Program {
    private static final int MAX_CHARS = 65_535;
    private static final int TOP_TEN = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        short [] charStats = countChars(input);
        char [] topChars = getTopTen(charStats);
        System.out.println();
        printStats(topChars, charStats);

    }

    private static short [] countChars(String input) {
        int size = input.length();
        char [] array = input.toCharArray();
        short [] charStats = new short[MAX_CHARS];

        for(int i = 0; i < size; i++) {
            if (charStats[array[i]] == 999) {
                continue;
            }
            charStats[array[i]] += 1;
        }
        return (charStats);
    }

    private static char [] getTopTen(short [] charStats) {
        char [] topChars = new char[TOP_TEN];

        for (int i = 0; i < MAX_CHARS; i++) {
            short current_max = charStats[i];
            if (current_max > 0) {
                for (int j = 0; j < TOP_TEN; j++) {
                    if (charStats[topChars[j]] < current_max) {
                        topChars = updateCharTop(topChars, (char) i, j);
                        break;
                    }
                }
            }
        }
        return (topChars);
    }

    private static char[] updateCharTop(char[] prev, char ch, int current) {
        char [] topChars = new char[TOP_TEN];

        for (int i = 0; i < current; i++) {
            topChars[i] = prev[i];
        }
        topChars[current] = ch;
        for (int i = current + 1; i < TOP_TEN; i++) {
            topChars[i] = prev[i - 1];
        }
        return (topChars);
    }

    private static void printStats(char[] topTen, short[] charStats) {
        short max = charStats[topTen[0]];
        short[] cols = new short[TOP_TEN];
        short maxHeight = (short) (max <= 10 ? max : 10);
        short totalLines = (short) (2 + maxHeight);

        for (int i = 0; i < TOP_TEN; i++) {
            if (max <= 10) {
                cols[i] = (short) charStats[topTen[i]];
            } else {
                cols[i] = (short) (charStats[topTen[i]] * 10 / max);
            }
        }
        for (int i = 0; i < totalLines; i++) {
            for (int j = 0; j < TOP_TEN; j++) {
                if (topTen[j] != 0) {
                    if (i + cols[j] + 2 == totalLines) {
                        System.out.printf("%3d", charStats[topTen[j]]);
                    } else if (i == totalLines - 1) {
                        System.out.printf("%3c", topTen[j]);
                    } else if (i + cols[j] >= maxHeight) {
                        System.out.printf("%3c", '#');
                    }
                    System.out.printf("%c", ' ');
                }
            }
            System.out.println();
        }
    }
}

