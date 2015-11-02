package fr.unice.polytech.si5.al.projet.s3.truck.assembly;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Etienne Strobbe (02/11/2015).
 */
public class Utils {


    public static String getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = Utils.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }
}
