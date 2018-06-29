package panels;


import mainprogram.ActionHandler;
import mainprogram.Constants;
import net.miginfocom.swing.MigLayout;
import objects.Category;
import objects.Question;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 */
public class CreationPanel extends JPanel implements CustomPanels
{
    private final ActionHandler handler;
    private ArrayList<Category> categoryList;
    private JTextField categoryField;
    private static final Border TEXT_FIELD_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private JTextField questionField;
    private JTextField answerField;
    private JLabel category;
    private JLabel newCategory;
    private JLabel questName;
    private JLabel answerName;
    private JButton categoryButton;
    private JButton createButton;
    private JComboBox<String> categoryComboBox;
    private JButton quizStartButton;

    public CreationPanel(ArrayList<Category> categoryList, ActionHandler handler) {
	this.categoryList = categoryList;
	this.handler = handler;
	createComboBox();
	createContent();

    }



    private void createContent() {
        setLayout(new MigLayout());
        setPreferredSize(Constants.PREFFERED_SIZE);
       // setBackground(Color.BLUE);
	setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
	fillPanelWithContent();
    }

    private void createComboBox() {
	categoryComboBox = new JComboBox<>();
	for (Category category : categoryList) {
	    categoryComboBox.addItem(category.getName());
	}
    }

    private void updateComboBox() {
	categoryComboBox.removeAllItems();
	for (Category category : categoryList) {
	    categoryComboBox.addItem(category.getName());
	}

    }

    private void fillPanelWithContent() {

	createButtons();
	createLables();
	createTextFields();
	addComponentsToMainPanel();

    }

    private void addComponentsToMainPanel() {

	add(category);
	add(categoryComboBox, "wrap, split2");
	add(newCategory);
	add(categoryField);
	add(categoryButton, "wrap");
	add(questName);
	add(questionField, "wrap");
	add(answerName);
	add(answerField,"wrap");
	add(createButton);
	add(quizStartButton,"skip1");
    }

    private void createTextFields() {
	categoryField = new JTextField(10);
	questionField = new JTextField(10);
	answerField = new JTextField(10);
	categoryField.setBorder(TEXT_FIELD_BORDER);
	questionField.setBorder(TEXT_FIELD_BORDER);
	answerField.setBorder(TEXT_FIELD_BORDER);
    }

    private void createLables() {
	category = new JLabel("What cource?");
	newCategory = new JLabel("New Category");
	questName = new JLabel("The question");
	answerName = new JLabel("The answer");
    }

    private void createButtons() {
	categoryButton = new JButton(Constants.BUTTON_ADD_CATEGORY);
	categoryButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		handler.addNewCategory(categoryField.getText());
		categoryField.setText("");
		categoryList = handler.getCategories();
		updateComboBox();

	    }
	});
	createButton = new JButton(Constants.BUTTON_CREATE);
	createButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		String query = questionField.getText();
		String answer = answerField.getText();

		Question question = new Question(query, answer);
		if(categoryComboBox.getSelectedItem() != null && !query.isEmpty() && !answer.isEmpty()) {
		    questionField.setText("");
		    answerField.setText("");
		    handler.addNewQuestion(question, categoryComboBox.getSelectedItem().toString());
		} else {
		    JOptionPane.showMessageDialog(null, "Please fill all neccesary fields");
		}
	    }
	});
	quizStartButton = new JButton(Constants.BUTTON_QUIZ);
	quizStartButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		handler.startQuiz();
	    }
	});

    }

}


