import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Саша on 28.04.2017.
 */
public class ElectronicTestBuilder {

    private JTextArea question;
    private JTextArea answer_1;
    private JTextArea answer_2;
    private JTextArea answer_3;
    private JTextArea answer_4;
    private JTextArea answer_5;
    private ArrayList<ElectronicTest> testList;
    private JFrame frame;

    public void go() {

        frame = new JFrame("");
        JPanel mainPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        Font mainFont = new Font("sanserif", Font.BOLD, 24);

        question = new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(mainFont);
        JScrollPane qScroller = new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer_1 = new JTextArea(6, 20);
        answer_1.setLineWrap(true);
        answer_1.setWrapStyleWord(true);
        answer_1.setFont(mainFont);
        JScrollPane a1Scroller = new JScrollPane(answer_1);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer_2 = new JTextArea(6, 20);
        answer_2.setLineWrap(true);
        answer_2.setWrapStyleWord(true);
        answer_2.setFont(mainFont);
        JScrollPane a2Scroller = new JScrollPane(answer_2);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer_3 = new JTextArea(6, 20);
        answer_3.setLineWrap(true);
        answer_3.setWrapStyleWord(true);
        answer_3.setFont(mainFont);
        JScrollPane a3Scroller = new JScrollPane(answer_3);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer_4 = new JTextArea(6, 20);
        answer_4.setLineWrap(true);
        answer_4.setWrapStyleWord(true);
        answer_4.setFont(mainFont);
        JScrollPane a4Scroller = new JScrollPane(answer_4);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer_5 = new JTextArea(6, 20);
        answer_5.setLineWrap(true);
        answer_5.setWrapStyleWord(true);
        answer_5.setFont(mainFont);
        JScrollPane a5Scroller = new JScrollPane(answer_5);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextButton = new JButton("Наступне тест");
        nextButton.addActionListener(new NextTestListener());

        JButton saveButton = new JButton("Зберегти");
        saveButton.addActionListener(new SaveButtonListener());

        JButton cleanButton = new JButton("Очистити тему");
        cleanButton.addActionListener(new CleanTestListener());

        testList = new ArrayList<ElectronicTest>();

        JLabel qLabel = new JLabel("Тест");
        JLabel aLabel = new JLabel("Варіанти відповідей");

        mainPanel.add(qLabel);
        mainPanel.add(qScroller);
        mainPanel.add(aLabel);
        mainPanel.add(a1Scroller);
        mainPanel.add(a2Scroller);
        mainPanel.add(a3Scroller);
        mainPanel.add(a4Scroller);
        mainPanel.add(a5Scroller);
        buttonPanel.add(nextButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(cleanButton);


        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.setSize(900, 900);
        frame.setVisible(true);
    }

    private class NextTestListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            ElectronicTest test = new ElectronicTest(question.getText(), answer_1.getText(), answer_2.getText(),
                    answer_3.getText(), answer_4.getText(), answer_5.getText());
            testList.add(test);
            clearTest();
        }
    }

    private void clearTest() {
        question.setText("");
        answer_1.setText("");
        answer_2.setText("");
        answer_3.setText("");
        answer_4.setText("");
        answer_5.setText("");
        question.requestFocus();
    }

    private class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            ElectronicTest test = new ElectronicTest(question.getText(), answer_1.getText(), answer_2.getText(),
                    answer_3.getText(), answer_4.getText(), answer_5.getText());
            testList.add(test);

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    private void saveFile(File selectedFile) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));

            for (ElectronicTest test : testList) {
                writer.write(test.getQuestion() + "/");
                writer.write(test.getAnswer1() + "/");
                writer.write(test.getAnswer2() + "/");
                writer.write(test.getAnswer3() + "/");
                writer.write(test.getAnswer4() + "/");
                writer.write(test.getAnswer5() + "/");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class CleanTestListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            testList.clear();
            clearTest();
        }
    }
}
