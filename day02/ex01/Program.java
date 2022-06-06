import java.io.*;
import java.util.*;

public class Program {
    public static List<String> inputA = new ArrayList<>();
    public static List<String> inputB = new ArrayList<>();
    public static SortedSet<String> dictionary = new TreeSet<>();

    public static void main(String[] args) {

        try {
            BufferedReader readerA = new BufferedReader(new FileReader("inputA.txt"));
            BufferedReader readerB = new BufferedReader(new FileReader("inputB.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"));

            String line;
            while ((line = readerA.readLine()) != null) {
                String [] split = line.split(" ");
                inputA.addAll(Arrays.asList(split));
                dictionary.addAll(inputA);
            }

            while ((line = readerB.readLine()) != null) {
                String [] split = line.split(" ");
                inputB.addAll(Arrays.asList(split));
                dictionary.addAll(inputB);
            }

            Iterator value = dictionary.iterator();
            while (value.hasNext()) {
                writer.write(value.next().toString());
                if (value.hasNext()) {
                    writer.write(", ");
                }
            }
            writer.close();
            readerA.close();
            readerB.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Similarity = %.2f\n", calculateSimilarity());
    }

    private static double calculateSimilarity() {
        List<Integer> repeatsInA = new ArrayList<>(dictionary.size());
        List<Integer> repeatsInB = new ArrayList<>(dictionary.size());
        double result = 0.0;

        calculateOccurrence(repeatsInA, inputA);
        calculateOccurrence(repeatsInB, inputB);

        int numerator = 0;
        for (int i = 0; i < dictionary.size(); i++) {
            numerator += repeatsInA.get(i) * repeatsInB.get(i);
        }

        double denominator = calculateDenominator(repeatsInA, repeatsInB);
        if (denominator == 0)
            return result;
        result = numerator / denominator;
        return result == 0.0 ? result : result - 0.005;
    }

    private static double calculateDenominator(List<Integer> repeatsInA, List<Integer> repeatsInB) {
        double sumOfSquaredA = 0;
        double sumOfSquaredB = 0;

        for (Integer a : repeatsInA) {
            sumOfSquaredA += a * a;
        }

        for (Integer b : repeatsInB) {
            sumOfSquaredB += b * b;

        }
        return Math.sqrt(sumOfSquaredA) * Math.sqrt(sumOfSquaredB);
    }

    private static void calculateOccurrence(List<Integer> list, List<String> source) {
        int count = 0;
        int i = 0;
        for (String dictElement : dictionary) {
            for (String sourceElement : source) {
                if (dictElement.equals(sourceElement)) {
                    count++;
                }
            }
            list.add(i, count);
            count = 0;
            i++;
        }
    }

}
