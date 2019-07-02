package chatbot;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Chatbot extends JFrame {

    JTextArea msgArea = new JTextArea();
    JTextField type = new JTextField();
    JButton ok = new JButton("ok");
    JFrame frame = new JFrame();

    String msgs = "";
    String file = "src\\meta.txt";
    String subprogram = "src\\testing.py";

    Chatbot() {
        frame.setSize(1200, 900);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //clear file
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        init();
        listeners();
    }

    public void init() {

        msgArea.setBounds(50, 50, 800, 600);
        frame.add(msgArea, new Integer(1));
        type.setBounds(50, 800, 800, 30);
        frame.add(type, new Integer(1));
        ok.setBounds(900, 800, 100, 30);
        frame.add(ok, new Integer(1));

        msgArea.setEditable(false);
    }

    public void listeners() {
        ok.addActionListener((ActionEvent arg0) -> {
            update();
            python();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
            String answer = read(file);
            msgs = msgs + "\n" + split("AI: " + answer);
            msgArea.setText(msgs);
        });
    }

    public void update() {
        if (type.getText() != "") {
            write(type.getText(), file);
            msgs = msgs + "\n" + split("You: " + type.getText());
            msgArea.setText(msgs);
            type.setText("");
        }
    }

    public void python() {
        try {
            String prg = "def main():\n"
                    + "    f = open(\"C:\\\\Users\\\\piopi\\\\Documents\\\\Chatbot\\\\src\\\\meta.txt\", \"a+\")\n"
                    + "\n"
                    + "    f.write(\"Python answer \\n\")\n"
                    + "\n"
                    + "    f.close()\n"
                    + "\n"
                    + "main()";
            BufferedWriter out = new BufferedWriter(new FileWriter(subprogram));
            out.write(prg);
            out.close();
            Process p = Runtime.getRuntime().exec("python " + subprogram);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret = in.readLine();
            System.out.println("value is : " + ret);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void write(String question, String directory) {
        try (FileWriter fw = new FileWriter(directory, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(question);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static String read(String directory) {
        String lastLine = "";

        try {
            String sCurrentLine;

            BufferedReader br = new BufferedReader(new FileReader(directory));

            while ((sCurrentLine = br.readLine()) != null) {
                lastLine = sCurrentLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }

    public String split(String text) {
        String output = "";
        int index = 0;
        while (index < text.length()) {
            output = output + text.substring(index, Math.min(index + 100, text.length())) + "\n";
            index += 100;
        }
        return output;
    }

    public static void main(String[] args) {
        Chatbot c1 = new Chatbot();
    }
}
