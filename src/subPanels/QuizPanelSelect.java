package subPanels;

import backend_logic.QuizListener;
import tools.Constants;
import backend_logic.StudyQuiz;
import net.miginfocom.swing.MigLayout;
import objects.Category;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class QuizPanelSelect extends JPanel
{
    private StudyQuiz studyQuiz;
    private QuizListener handler;
    private JComboBox categoryComboBox = new JComboBox();

    public QuizPanelSelect(QuizListener handler) {
	this.handler = handler;
	createComponents();
    }

    public QuizPanelSelect(final StudyQuiz studyQuiz) {
	//this.handler = handler;
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

    public void updateComboBox(ArrayList<Category> categoryList) {
	categoryComboBox.removeAllItems();
	for (Category category : categoryList) {
	    categoryComboBox.addItem(category.getName());
	}
    }
}
