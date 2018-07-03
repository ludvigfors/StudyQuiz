package sub_panels;

import backend_logic.Fonts;
import backend_logic.StudyQuiz;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static backend_logic.LanguageConstants.*;

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
	setLayout(new MigLayout("debug, fillx"));

	JPanel middlePanel = new JPanel(new MigLayout("gapy 10::10"));
	JLabel congratzHeading = new JLabel(CONGRATULATIONS_LABEL_TEXT);
	congratzHeading.setFont(new Font("georgia",Font.PLAIN,30));
	JLabel resultLabel = new JLabel(RESULT_LABEL_TEXT);
	resultLabel.setFont(Fonts.LABEL_FONT);
	result = new JLabel();
	result.setFont(Fonts.LABEL_FONT);
	JButton startScreenButton = new JButton(START_SCREEN_LABEL_TEXT);
	JButton playAgainButton = new JButton(PLAY_AGAIN_LABEL_TEXT);
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

	add(congratzHeading,"wrap,alignx center");
	middlePanel.add(resultLabel,"split,align center");
	middlePanel.add(result,"wrap");
	middlePanel.add(playAgainButton,"split, h 40::40 ");
	middlePanel.add(startScreenButton, "h 40::40");
	add(middlePanel,"pushy, align center");
    }

    public void setResultCount(final String result) {
	this.result.setText(result);
    }
}
