package panels;


import mainprogram.MainFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 */
public class CreationPanel extends JPanel
{
    private JPanel p;
    private JTextField questionField;
    private JTextField answerField;
    private String[] dummyCred = {"TATA65","TDDD78"};

    private Border textFieldBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private JComboBox box;

    public CreationPanel() {
        displayTextFields();
    }

    private void displayTextFields() {
        setLayout(new MigLayout());

        box = new JComboBox(dummyCred);
        box.setSelectedIndex(1);
        JButton categoryButton = new JButton("Add");
        categoryButton.addActionListener(new ButtonClicked());
	JButton createButton = new JButton("Create question");
	createButton.addActionListener(new ButtonClicked());
        JLabel category = new JLabel("What cource?");
        JLabel newCategory = new JLabel("New Category");
        JLabel questName = new JLabel("The question");
        JLabel answerName = new JLabel("The answer");
	JTextField categoryField = new JTextField(10);
	questionField = new JTextField(10);
	answerField = new JTextField(10);
	categoryField.setBorder(textFieldBorder);
	questionField.setBorder(textFieldBorder);
	answerField.setBorder(textFieldBorder);



	add(category);
	add(box,"split2");
	add(newCategory);
	add(categoryField);
	add(categoryButton,"wrap");
	add(questName);
	add(questionField,"wrap");
	add(answerName);
	add(answerField);
	add(createButton,"wrap");

    }


    @Override public void setBorder(final Border border) {
	super.setBorder(border);
    }



    public String getQuestion() {
	return questionField.getText();
    }

    public String getAnswer() {
	return answerField.getText();
    }

    public String getCategory() {
	return box.getSelectedItem().toString();
    }
}
