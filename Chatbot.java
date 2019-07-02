import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Chatbot extends JFrame {

    JTextArea msgArea = new JTextArea();
    JTextField type = new JTextField();
    JButton ok = new JButton("ok");
    JFrame frame = new JFrame();

    String msgs = "";
    String file = "C:\\Users\\GT\\eclipse-workspace\\Chatbot\\src\\meta.txt";
    String subprogram = "C:\\Users\\GT\\eclipse-workspace\\Chatbot\\src\\cmdPyhton.cmd";

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
        frame.add(msgArea);
        type.setBounds(50, 800, 800, 30);
        frame.add(type);
        ok.setBounds(900, 800, 100, 30);
        frame.add(ok);

        msgArea.setEditable(false);
    }

    public void listeners() {
        ok.addActionListener((ActionEvent arg0) -> {
            update();
            python();
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (Exception e) {
                System.out.println(e);
            }
            String answer = read(file);
            msgs = msgs + "\n" + answer;
            msgArea.setText(msgs);
        });
    }

    public void update() {
        if (type.getText() != "") {
            write(type.getText(), file);
            msgs = msgs + "\n" + type.getText();
            msgArea.setText(msgs);
            type.setText("");
        }
    }

    public void python() {
        try {
        Runtime.getRuntime().exec(subprogram, null, new File("C:\\Users\\GT\\eclipse-workspace\\Chatbot\\src\\"));            

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
                //System.out.println(sCurrentLine);
                lastLine = sCurrentLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }

    public static void main(String[] args) {
        Chatbot c1 = new Chatbot();
        System.out.println(read(c1.file));
    }
}
