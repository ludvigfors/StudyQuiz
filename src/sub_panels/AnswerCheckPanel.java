package sub_panels;

import backend_logic.Constants;
import backend_logic.StudyQuiz;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays the correct answer and the answer the user inputed.
 *
 * Lets the user determine if their answer was accepted.
 */
public class AnswerCheckPanel extends JPanel
{
    private final StudyQuiz studyQuiz;
    private JLabel correctAnswer;
    private JLabel playerAnswer;

    public AnswerCheckPanel(StudyQuiz studyQuiz) {
        this.studyQuiz = studyQuiz;
        this.correctAnswer = new JLabel();
        this.playerAnswer = new JLabel();
        createPanel();
    }

    private void createPanel() {
	setLayout(new MigLayout("align 50% 50%"));
	setPreferredSize(Constants.PREFFERED_SIZE);
	JLabel correctAnswerHeading = new JLabel("Rätt svar: ");
	JLabel playerAnswerHeading = new JLabel("Ditt svar: ");
	JLabel wasItCorrectHeading = new JLabel("Var det rätt? ");
	JButton yesButton = new JButton("YES");
	JButton noButton = new JButton("NO");
	yesButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		studyQuiz.addGoodQuestionAndTakeNextQuestion(playerAnswer.getText());
	    }
	});
	noButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		studyQuiz.goToNextQuestion();
	    }
	});

	add(correctAnswerHeading);
	add(correctAnswer, "wrap");
	add(playerAnswerHeading);
	add(playerAnswer,"wrap");
	add(wasItCorrectHeading);
	add(yesButton);
	add(noButton);

    }

    public void setCorrectAnswer(final String correctAnswer) {
	this.correctAnswer.setText(correctAnswer);
    }

    public void setPlayerAnswer(final String playerAnswer) {
        this.playerAnswer.setText(playerAnswer);
    }

}
