package sub_panels;

import backend_logic.Fonts;
import backend_logic.LanguageConstants;
import backend_logic.StudyQuiz;
import net.miginfocom.swing.MigLayout;
import objects.Category;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static backend_logic.LanguageConstants.CATEGORY_CHOOSER_LABEL_TEXT;
import static backend_logic.LanguageConstants.GO_BUTTON_TEXT;
import static backend_logic.LanguageConstants.MESSAGE_CHOOSE_A_CATEGORY;

/**
 * Displays a JComboBox of all avalible categories to choose from and start a quiz on.
 */
public class QuizSelectPanel extends JPanel
{

    private StudyQuiz studyQuiz;
    private JComboBox<String> categoryComboBox;

    public QuizSelectPanel(final StudyQuiz studyQuiz) {
	this.studyQuiz = studyQuiz;
	this.categoryComboBox = new JComboBox<>();
	this.categoryComboBox.setFont(Fonts.TEXTFIELD_FONT);
	createComponents();//Kanske kan kallas utanför classen
    }

    private void createComponents() {
	setLayout(new MigLayout("fillx"));
	JButton backButton = new JButton(LanguageConstants.BACK_BUTTON_TEXT);
	backButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		studyQuiz.showCreationPanel();
	    }
	});

	JPanel middlePanel = new JPanel(new MigLayout("gapy 25::25"));
	JLabel comboBoxLabel = new JLabel();
	comboBoxLabel.setText(CATEGORY_CHOOSER_LABEL_TEXT);
	comboBoxLabel.setFont(Fonts.LABEL_FONT);

	// Go Button
	JButton goButton = new JButton(GO_BUTTON_TEXT);
	goButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {

		if(categoryComboBox.getSelectedItem() != null){
		    studyQuiz.startQuizGame(Objects.requireNonNull(categoryComboBox.getSelectedItem()).toString());
		} else {
		    JOptionPane.showMessageDialog(null, MESSAGE_CHOOSE_A_CATEGORY);
		}
	    }
	});

	add(backButton, " wrap");
	middlePanel.add(comboBoxLabel,"wrap, sg 1");
	middlePanel.add(categoryComboBox,"wrap, sg 1");
	middlePanel.add(goButton,"wrap, sg 1, h 40::40");
	add(middlePanel,"align center, pushy");
    }

    public void updateComboBox() {
	categoryComboBox.removeAllItems();
	for (Category category : studyQuiz.getRootXMLClass().getCategories()) {
	    categoryComboBox.addItem(category.getName());
	}
    }
}
