import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        Map<String, String> signatures = new HashMap<>();

        try {
            FileInputStream stream = new FileInputStream("signatures.txt");
            StringBuilder line = new StringBuilder();
            int i;

            while ((i = stream.read()) != -1) {
                if (i == '\n' || stream.available() == 0) {
                    String [] split = line.toString().split(", ");
                    signatures.put(split[1].trim(), split[0]);
                    line.delete(0, line.capacity());
                } else {
                    line.append((char) i);
                }
            }
            stream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
        }

        String userInput;
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> ");
        while (!(userInput = scanner.nextLine()).equals("42")) {
            StringBuilder magicNumbers = new StringBuilder();
            try {
                FileInputStream inStream = new FileInputStream(userInput);
                OutputStream outStream = new FileOutputStream("result.txt", true);
                int ch;
                for (int i = 0; i < 12 && (ch = inStream.read()) != -1; i++) {
                    magicNumbers.append(String.format("%02X", ch)).append(" ");
                }

                for (Map.Entry<String, String> signature : signatures.entrySet()) {
                    if (magicNumbers.toString().toUpperCase().startsWith(signature.getKey())) {
                        outStream.write(signature.getValue().getBytes());
                        outStream.write('\n');
                        System.out.println("PROCESSED");
                        break;
                    }
                }
                inStream.close();
                outStream.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found !");
            }
            System.out.print("-> ");
        }
        scanner.close();
    }
}
