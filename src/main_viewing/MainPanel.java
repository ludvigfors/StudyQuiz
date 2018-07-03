package main_viewing;

import backend_logic.QuizListener;
import backend_logic.StudyQuiz;
import objects.Question;
import sub_panels.AnswerCheckPanel;
import sub_panels.CreationPanel;
import sub_panels.QuestionDisplayPanel;
import sub_panels.QuizSelectPanel;
import sub_panels.ResultPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The panel that displays all different panels.
 *
 * Checks which panel to display when StudyQuiz initiates notifyListeners().
 */
public class MainPanel extends JPanel implements QuizListener
{
    /**
     * The name of the CreationPanel in the cardlayout.
     */
    public static final String CREATE_PANEL = "create";
    /**
     * The name of the QuizSelectPanel in the cardlayout.
     */
    public static final String QUIZ_SELECT_PANEL = "quiz";
    /**
     * The name of the QuestionDisplayPanel in the cardlayout.
     */
    public static final String QUIZ_QUESTION_DISPLAY_PANEL = "QPanel";
    /**
     * The name of the AnswerCheckPanel in the cardlayout.
     */
    public static final String QUIZ_ANSWER_CHECK_PANEL = "check";
    /**
     * The name of the ResultPanel in the cardlayout.
     */
    public static final String QUIZ_RESULT_PANEL = "result";

    /**
     * Width of window.
     */
    public static final int PANEL_WIDTH = 660;
    /**
     * Height of window.
     */
    public static final int PANEL_HEIGHT = 440;


    private StudyQuiz studyQuiz;
    private CardLayout cardLayout;
    private QuizSelectPanel quizSelectPanel;
    private QuestionDisplayPanel questionPanel;
    private AnswerCheckPanel answerCheckPanel;
    private ResultPanel resultPanel;

    public MainPanel(final StudyQuiz studyQuiz) {
	this.studyQuiz = studyQuiz;
	this.cardLayout = new CardLayout();
	createContent();

    }

    private void createContent() {
	setLayout(cardLayout);
	CreationPanel createPanel = new CreationPanel(studyQuiz);
	quizSelectPanel = new QuizSelectPanel(studyQuiz);
	createQuestionDisplay();
	add(createPanel, CREATE_PANEL);
	add(quizSelectPanel, QUIZ_SELECT_PANEL);
    }

    @Override public void quizChanged() {
	switch (studyQuiz.getCurrentPanel()){
	    case QUIZ_SELECT_PANEL:
		startQuizSelector();
		break;

	    case DISPLAY_PANEL:
		updateQuestions();
		break;

	    case CREATION_PANEL:
		startCreate();
		break;

	    case RESULT_PANEL:
		displayResult();
		break;

	    case ANSWER_CHECK_PANEL:
		displayAnswerCheck();
		break;
	}
    }

    @Override public Dimension getPreferredSize() {
	return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }

    private void displayAnswerCheck() {
	Question currentQuestion = studyQuiz.getCurrentQuestion();
	answerCheckPanel.setCorrectAnswer(currentQuestion.getAnswer());
	answerCheckPanel.setPlayerAnswer(studyQuiz.getLastSubmittedAnswer());
	cardLayout.show(this, QUIZ_ANSWER_CHECK_PANEL);
    }

    private void displayResult() {
	resultPanel.setResultCount(studyQuiz.generateResult());
	cardLayout.show(this, QUIZ_RESULT_PANEL);
    }

    private void updateQuestions() {
	questionPanel.showQuestion();
	cardLayout.show(this, QUIZ_QUESTION_DISPLAY_PANEL);
    }

    private void createQuestionDisplay() {
	questionPanel = new QuestionDisplayPanel(studyQuiz);
	answerCheckPanel = new AnswerCheckPanel(studyQuiz);
	resultPanel = new ResultPanel(studyQuiz);
	addQuizPanelsToCardLayout();
    }

    public void startQuizSelector() {
	quizSelectPanel.updateComboBox();
	cardLayout.show(this, QUIZ_SELECT_PANEL);
    }

    private void addQuizPanelsToCardLayout() {
	add(questionPanel, QUIZ_QUESTION_DISPLAY_PANEL);
	add(answerCheckPanel, QUIZ_ANSWER_CHECK_PANEL);
	add(resultPanel, QUIZ_RESULT_PANEL);
    }


    public void startCreate() {
	//removeQuizPanelsFromCardLayout();
	cardLayout.show(this, CREATE_PANEL);
    }

}
