package sub_panels;

import backend_logic.StudyQuiz;
import net.miginfocom.swing.MigLayout;
import objects.Category;
import backend_logic.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Displays a JComboBox of all avalible categories to choose from and start a quiz on.
 */
public class QuizSelectPanel extends JPanel
{
    private StudyQuiz studyQuiz;
    private JComboBox<String> categoryComboBox = new JComboBox<>();

    public QuizSelectPanel(final StudyQuiz studyQuiz) {
	this.studyQuiz = studyQuiz;
	createComponents();//Kanske kan kallas utanför classen
    }

    private void createComponents() {
	JPanel p = new JPanel(); // TODO Kanske ska ändras senare
	p.setLayout(new MigLayout());
	//p.setBackground(Color.GREEN);
	p.setPreferredSize(Constants.PREFFERED_SIZE);
	JButton backButton = new JButton("Tillbaka");
	backButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		studyQuiz.showCreationPanel();
	    }
	});

	JLabel comboBoxTitle = new JLabel();
	comboBoxTitle.setText("Vilken kurs vill du förhöra dig på?");


	// Go Button
	JButton goButton = new JButton("GO!!!");
	goButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {

		studyQuiz.startQuizGame(Objects.requireNonNull(categoryComboBox.getSelectedItem()).toString());
	    }
	});

	p.add(backButton, "wrap");
	p.add(comboBoxTitle,"wrap,skip1,sg 1");
	p.add(categoryComboBox,"wrap,skip1,sg 1");
	p.add(goButton,"skip1,align center");
	add(p);
    }

    public void updateComboBox() {
	categoryComboBox.removeAllItems();
	for (Category category : studyQuiz.getRootXMLClass().getCategories()) {
	    categoryComboBox.addItem(category.getName());
	}
    }
}
