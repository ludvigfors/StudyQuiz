package sub_panels;

import backend_logic.Fonts;
import backend_logic.LanguageConstants;
import backend_logic.StudyQuiz;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays each question in the quiz. Maximum of 10 questions.
 */
public class QuestionDisplayPanel extends JPanel
{

    private StudyQuiz studyQuiz;
    private JLabel questionCount;
    private JLabel questionLabel;
    private JTextField playerAnswer;
    /**
       * The amount of columns in JTextFields on the panels.
       */
      public static final int TEXTFIELD_COLUMNS = 20;



    public QuestionDisplayPanel(final StudyQuiz studyQuiz) {
	this.studyQuiz = studyQuiz;
	this.questionCount = new JLabel();
	this.questionLabel = new JLabel();
	this.playerAnswer = new JTextField(TEXTFIELD_COLUMNS);
	this.questionCount.setFont(Fonts.LABEL_FONT);
	this.questionLabel.setFont(Fonts.LABEL_FONT);
	this.playerAnswer.setFont(Fonts.TEXTFIELD_FONT);
	createPanel();
    }

    public void showQuestion() {
	questionCount.setText("Fråga " + (studyQuiz.getCurrentQuestionIndex() + 1) + ":");
	questionLabel.setText(studyQuiz.getCurrentQuestion().getQuery()+"?");
    }


    private void createPanel() {
	setLayout(new MigLayout("fill"));
	JButton endButton = new JButton(LanguageConstants.END_BUTTON_TEXT);
	endButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		studyQuiz.showCreationPanel();
	    }
	});
	JButton submitButton = new JButton(LanguageConstants.SUBMIT_BUTTON_TEXT);
	submitButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		controlAnswer();
	    }
	});
	JPanel middlePanel = new JPanel(new MigLayout("gapy 10::10"));

	add(endButton,"wrap");
	middlePanel.add(questionCount, "wrap, sgx 1");
	middlePanel.add(questionLabel,"wrap, sgx 1");
	middlePanel.add(playerAnswer,"wrap, sgx 1, h 30::30");
	middlePanel.add(submitButton, "sgx 1, h 40::40 ");
	add(middlePanel,"pushy, align center");


    }

    private void controlAnswer() {
	String submittedAnswer = playerAnswer.getText();
	if(!submittedAnswer.isEmpty()){
	    playerAnswer.setText("");
	    studyQuiz.checkAnswer(submittedAnswer);
	} else{
	    JOptionPane.showMessageDialog(null, LanguageConstants.MESSAGE_NO_ANSWER_INPUTED);
	}
    }

}
