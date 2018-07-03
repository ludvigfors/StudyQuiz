package sub_panels;

import backend_logic.StudyQuiz;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static backend_logic.LanguageConstants.*;

/**
 * Displays the correct answer and the answer the user inputed.
 *
 * Lets the user determine if their answer was accepted.
 */
public class AnswerCheckPanel extends JPanel
{
    /**
     * The font for the labels.
     */
    public static final Font ANSWER_PANEL_LABEL_FONT = new Font("georgia", Font.PLAIN, 30);


    private final StudyQuiz studyQuiz;
    private JLabel correctAnswer;
    private JLabel playerAnswer;

    public AnswerCheckPanel(StudyQuiz studyQuiz) {
        this.studyQuiz = studyQuiz;
        this.correctAnswer = new JLabel();
        this.playerAnswer = new JLabel();
        this.correctAnswer.setFont(ANSWER_PANEL_LABEL_FONT);
        this.playerAnswer.setFont(ANSWER_PANEL_LABEL_FONT);
        createPanel();
    }

    private void createPanel() {
	setLayout(new MigLayout("align 50% 50%, gapy 10::10"));
	JLabel correctAnswerLabel = new JLabel(CORRECT_ANSWER_LABEL);
	JLabel playerAnswerLabel = new JLabel(PLAYER_ANSWER_LABEL);
	JLabel wasItCorrectLabel = new JLabel(WAS_IT_CORRECT_LABEL);

	correctAnswerLabel.setFont(ANSWER_PANEL_LABEL_FONT);
	playerAnswerLabel.setFont(ANSWER_PANEL_LABEL_FONT);
	wasItCorrectLabel.setFont(ANSWER_PANEL_LABEL_FONT);

	JButton yesButton = new JButton(YES_BUUTON);
	JButton noButton = new JButton(NO_BUTTON);
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

	add(correctAnswerLabel,"split");
	add(correctAnswer, "wrap");
	add(playerAnswerLabel,"split");
	add(playerAnswer,"wrap");
	add(wasItCorrectLabel);
	add(yesButton,"h 40::40, sg 1, split, align center");
	add(noButton,"sg 1");

    }

    public void setCorrectAnswer(final String correctAnswer) {
	this.correctAnswer.setText(correctAnswer);
    }

    public void setPlayerAnswer(final String playerAnswer) {
        this.playerAnswer.setText(playerAnswer);
    }

}
