package sub_panels;

import backend_logic.StudyQuiz;
import net.miginfocom.swing.MigLayout;
import objects.Category;
import objects.Question;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static backend_logic.Fonts.LABEL_FONT;
import static backend_logic.Fonts.TEXTFIELD_FONT;
import static backend_logic.LanguageConstants.*;

/**
 * The first panel you see when program starts.
 *
 * Displays JTextFields for creating a new question in a chosen categoryLabel.
 *
 */
public class CreationPanel extends JPanel
{

    private final StudyQuiz studyQuiz;
    private JTextField categoryField;
    private JTextField questionField;
    private JTextField answerField;
    private JLabel categoryLabel;
    private JLabel newCategoryLabel;
    private JLabel questionCreationLabel;
    private JLabel answerCreationLabel;
    private JButton addCategoryButton;
    private JButton createButton;
    private JComboBox<String> categoryComboBox;
    private JButton quizStartButton;



    public CreationPanel(final StudyQuiz studyQuiz) {
	this.studyQuiz = studyQuiz;
	createComboBox();
	createContent();
    }


    private void createContent() {
	setLayout(new MigLayout("fillx,gapy 30::30"));
	setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
	fillPanelWithContent();
    }

    private void createComboBox() {
	categoryComboBox = new JComboBox<>();
	categoryComboBox.setFont(TEXTFIELD_FONT);
	for (Category category : studyQuiz.getRootXMLClass().getCategories()) {
	    categoryComboBox.addItem(category.getName());
	}
    }

    private void updateComboBox() {
	categoryComboBox.removeAllItems();
	for (Category category :  studyQuiz.getRootXMLClass().getCategories()) {
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
	add(questionCreationLabel);
	add(questionField, "wrap, pushx, growx, h 40::40");
	add(answerCreationLabel);
	add(answerField,"wrap,pushx, growx, h 40::40");
	add(categoryLabel);
	add(categoryComboBox, "wrap, pushx, growx, h 40::40");
	add(createButton,"span2, grow,wrap, height 40::60");
	add(newCategoryLabel);
	add(categoryField,"pushx, growx,split2, h 40::40");
	add(addCategoryButton, "wrap, h 40::40");
	add(quizStartButton,"span2, pushy, grow, h 60::60, aligny top");

    }

    private void createTextFields() {
	categoryField = new JTextField();
	questionField = new JTextField();
	answerField = new JTextField();


	categoryField.setFont(TEXTFIELD_FONT);
	questionField.setFont(TEXTFIELD_FONT);
	answerField.setFont(TEXTFIELD_FONT);
    }

    private void createLables() {
	categoryLabel = new JLabel(COURCE_LABEL_TEXT);
	newCategoryLabel = new JLabel(NEW_CATEGORY_LABEL_TEXT);
	questionCreationLabel = new JLabel(QUESTION_LABEL_TEXT);
	answerCreationLabel = new JLabel(ANSWER_LABEL_TEXT);

	categoryLabel.setFont(LABEL_FONT);
	newCategoryLabel.setFont(LABEL_FONT);
	questionCreationLabel.setFont(LABEL_FONT);
	answerCreationLabel.setFont(LABEL_FONT);
    }

    private void createButtons() {
	addCategoryButton = new JButton(BUTTON_ADD_CATEGORY_TEXT);
	addCategoryButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		studyQuiz.addNewCategory(categoryField.getText());
		categoryField.setText("");
		updateComboBox();

	    }
	});
	createButton = new JButton(BUTTON_CREATE_TEXT);
	createButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		String query = questionField.getText();
		String answer = answerField.getText();

		Question question = new Question(query, answer);
		if(categoryComboBox.getSelectedItem() != null && !query.isEmpty() && !answer.isEmpty()) {
		    questionField.setText("");
		    answerField.setText("");
		    studyQuiz.addNewQuestion(question,categoryComboBox.getSelectedItem().toString());
		} else {
		    JOptionPane.showMessageDialog(null, MESSAGE_FILL_ALL_FIELDS);
		}
	    }
	});
	quizStartButton = new JButton(BUTTON_QUIZ_TEXT);
	quizStartButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		studyQuiz.showQuizSelectorPanel();
	    }
	});
    }

}


