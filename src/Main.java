import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile = null;
        String rec;
        int line = 0;
        int words = 0;
        int characters = 0;
        String fileName;

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while (reader.ready()){
                    rec = reader.readLine();
                    line ++;
                    characters = characters + rec.length();
                    words = words + rec.split(" ", 0).length;
                }
                reader.close();
                System.out.println("\n\nData file read!");
            }
            else {
                System.out.println("You must choose a file! Exiting...");
                System.exit(0);
            }
        }

        catch (FileNotFoundException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        fileName = selectedFile.getName();

        System.out.printf("Your file, %s, has %s lines, %s words, and %s characters.", fileName, line, words, characters);
    }
}