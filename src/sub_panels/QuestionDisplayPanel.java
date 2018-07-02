package sub_panels;

import backend_logic.StudyQuiz;
import net.miginfocom.swing.MigLayout;
import backend_logic.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays each question in the quiz. Maximum of 10 questions.
 */
public class QuestionDisplayPanel extends JPanel
{

    private StudyQuiz studyQuiz;
    private JLabel questionCount = new JLabel();
    private JLabel questionLabel = new JLabel();
    private JTextField playerAnswer = new JTextField(Constants.TEXTFIELD_COLUMNS);




    public QuestionDisplayPanel(final StudyQuiz studyQuiz) {
	this.studyQuiz = studyQuiz;
	createPanel();
    }

    public void showQuestion() {

	questionCount.setText("Fråga " + (studyQuiz.getCurrentQuestionIndex() + 1) + ":");
	questionLabel.setText(studyQuiz.getCurrentQuestion().getQuery()+"?");
	//handler.showPanel();
    }







    private void createPanel() {
	setLayout(new MigLayout());
	setPreferredSize(Constants.PREFFERED_SIZE);
	JButton endButton = new JButton("Avsluta");
	endButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		studyQuiz.showCreationPanel(); // HAHA....
	    }
	});
	JButton submitButton = new JButton("Svara!");
	submitButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		controlAnswer();
	    }
	});
	add(endButton,"wrap");
	add(questionCount, "wrap, skip1, align 50%");
	add(questionLabel,"wrap, skip1, align 50%");
	add(playerAnswer,"wrap, skip1, alignx center");
	add(submitButton, "skip1, align center");



    }

    private void controlAnswer() {
	String submittedAnswer = playerAnswer.getText();
	if(!submittedAnswer.isEmpty()){
	    playerAnswer.setText("");
	    studyQuiz.checkAnswer(submittedAnswer);
	} else{
	    JOptionPane.showMessageDialog(null, "Fyll i ett svar");
	}
    }
/*
    public String getResult(){
	return amountCorrect+"/"+questions.size();
    }

    public JLabel getQuestionLabel() {
	return questionLabel;
    }

    public void setQuestionLabel(final JLabel questionLabel) {
	this.questionLabel = questionLabel;
    }

    public void incrementCorrect(){
	this.amountCorrect++;
    }

    public int getAmountCorrect() {
	return amountCorrect;
    }

    public Question getCurrentQuestion() {
	return currentQuestion;
    }
    */
}
