package panels;

import handlers.ActionHandler;
import mainprogram.Constants;
import net.miginfocom.swing.MigLayout;
import objects.Question;

import javax.swing.*;
import java.util.ArrayList;

public class QuestionDisplayPanel extends JPanel
{
    private ArrayList<Question> questions;
    private JLabel questionCount = new JLabel();
    private JLabel questionLabel = new JLabel();
    private JTextField playerAnswer = new JTextField(12);
    private ActionHandler handler;
    private int currentQuestionIndex = 0; // MAGISK KONSTANT

    public QuestionDisplayPanel(final ArrayList<Question> questions, ActionHandler handler) {
	this.questions = questions;
	this.handler = handler;
	createPanel();
	showNextQuestion();
    }

    private void showNextQuestion() {
        questionCount.setText("Fråga "+(currentQuestionIndex+1));
	questionLabel.setText(questions.get(currentQuestionIndex).getQuery());
    }

    private void createPanel() {
	setLayout(new MigLayout("debug, align 50% 50%"));
	setPreferredSize(Constants.PREFFERED_SIZE);
	JButton submitButton = new JButton("Svara!");
	add(questionCount, "wrap, align 50%");
	add(questionLabel,"wrap, align 50%");
	add(playerAnswer,"wrap, alignx center");
	add(submitButton, "align center");



    }

    public JLabel getQuestionLabel() {
	return questionLabel;
    }

    public void setQuestionLabel(final JLabel questionLabel) {
	this.questionLabel = questionLabel;
    }
}
