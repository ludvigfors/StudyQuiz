package panels;

import handlers.ActionHandler;
import mainprogram.Constants;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class AnswerCheckPanel extends JPanel
{
    private JLabel correctAnswer;
    private JLabel playerAnswer;
    private ActionHandler handler;

    public AnswerCheckPanel(ActionHandler handler) {
        this.correctAnswer = new JLabel();
        this.playerAnswer = new JLabel();
        this.handler = handler;
        createPanel();
    }

    private void createPanel() {
	setLayout(new MigLayout());
	setPreferredSize(Constants.PREFFERED_SIZE);
	JLabel correctAnswerHeading = new JLabel("Rätt svar: ");
	JLabel playerAnswerHeading = new JLabel("Ditt svar: ");
	JLabel wasItCorrectHeading = new JLabel("Var det rätt? ");
	JButton yesButton = new JButton("YES");
	JButton noButton = new JButton("NO");

	add(correctAnswerHeading);
	add(correctAnswer, "wrap");
	add(playerAnswerHeading);
	add(playerAnswer,"wrap");
	add(wasItCorrectHeading);
	add(yesButton);
	add(noButton);

    }
}
