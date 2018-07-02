package sub_panels;

import backend_logic.StudyQuiz;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The panel that displays the result from a quiz.
 */
public class ResultPanel extends JPanel
{
    private StudyQuiz studyQuiz;
    private JLabel result;


    public ResultPanel(final StudyQuiz studyQuiz) {
	this.studyQuiz = studyQuiz;
	displayResult();
    }

    private void displayResult() {
	setLayout(new MigLayout("align 50% 50%"));
	JLabel congratzHeading = new JLabel("Congratulations");
	JLabel resultHeading = new JLabel("You got: ");
	result = new JLabel();
	JButton startScreenButton = new JButton("Start Screen");
	JButton playAgainButton = new JButton("Play Again?");
	playAgainButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		studyQuiz.showQuizSelectorPanel();
	    }
	});
	startScreenButton.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		studyQuiz.showCreationPanel();
	    }
	});

	add(congratzHeading,"wrap, span 2, align 50%");
	add(resultHeading,"align right");
	add(result,"wrap");
	add(playAgainButton);
	add(startScreenButton);
    }

    public void setResultCount(final String result) {
	this.result.setText(result);
    }
}
