package sample;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Controller {
    public Button File;
    public TextArea Input;
    public Button Encrypt;
    public Button Decrypt;
    String data=" ";
    String key=" ";
    public File file;
    boolean Charsize=false;

    public void open(ActionEvent actionEvent) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT File", "*.txt"));
        file = fc.showOpenDialog(null);
        Scanner in = new Scanner(file);

        while (in.hasNextLine()){
            data = in.nextLine();
            System.out.println(data);
        }
        in.close();

    }

    public void encryptCode (ActionEvent actionEvent) throws IOException {
        if (Input.getText() !=null) {
            String key = Input.getText();
            String output = "";
            File outputFile = new File(file.getName().substring(0, file.getName().length() - 4) + "_encrypt.txt");
            key = key.toLowerCase();

            for (int i = 0, j = 0; i < data.length(); i++) {
                int ltr = (int) data.charAt(i);


                if (ltr >= 'a' && ltr <= 'z') {
                    output += Character.toString((char) ((ltr + key.charAt(j) - 2 * 'a') % 26 + 'a'));
                    j = ++j % key.length();
                }

                else if (ltr >= 'A' && ltr <= 'Z') {
                    output += Character.toString((char) ((ltr - 'A' + key.charAt(j) - 'a') % 26 + 'A'));
                    j = ++j % key.length();
                } else {
                    output += (char) ltr;
                }

            }
            System.out.println(output);

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(output);

            writer.close();
        }
    }

    public void decryptCode (ActionEvent actionEvent) throws IOException {
        if (Input.getText() !=null) {
            String key = Input.getText();
            String output = "";
            File outputFile = new File(file.getName().substring(0, file.getName().length() - 4) + "_decrypt.txt");
            key = key.toLowerCase();

            for (int i = 0, j = 0; i < data.length(); i++) {
                int ltr = (int) data.charAt(i);

                if (ltr >= 'a' && ltr <= 'z') {
                    output += Character.toString((char) ((ltr - key.charAt(j) + 26) % 26 + 'a'));
                    j = ++j % key.length();
                }

                else if (ltr >= 'A' && ltr <= 'Z') {
                    output += Character.toString((char) ((ltr - key.charAt(j) + 58) % 26 + 'A'));
                    j = ++j % key.length();
                } else {
                    output += (char) ltr;
                }

            }


            System.out.println(output);

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(output);

            writer.close();
        }
    }
}
