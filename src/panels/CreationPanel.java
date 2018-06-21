package panels;





import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CreationPanel extends JPanel
{
    private JPanel p;
    private JTextField questionField;
    private JTextField answerField;
    private String[] dummyCred = {"TATA65","TDDD78"};

    public CreationPanel() {
        displayTextFields();
    }

    private void displayTextFields() {
        setLayout(new MigLayout());

        JComboBox box = new JComboBox(dummyCred);
        box.setSelectedIndex(1);

        JLabel category = new JLabel("What cource?");
        JLabel newCategory = new JLabel("New Category");
        JLabel questName = new JLabel("The question");
        JLabel answerName = new JLabel("The answer");
        JButton createButton = new JButton("Create question");
        JButton quizButton = new JButton();
	JTextField categoryField = new JTextField();
	questionField = new JTextField();
	answerField = new JTextField();
	categoryField.setPreferredSize(new Dimension(300,20));
	questionField.setPreferredSize(new Dimension(700,30));
	answerField.setPreferredSize(new Dimension(700,30));

	add(category);
	add(box,"split2, split3");
	add(newCategory);
	add(categoryField,"wrap");
	add(questName);
	add(questionField,"wrap");
	add(answerName);
	add(answerField,"wrap");
    }
}
