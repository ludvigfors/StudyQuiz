package mainViewing;

import backend_logic.QuizListener;
import backend_logic.StudyQuiz;
import objects.Question;
import subPanels.AnswerCheckPanel;
import subPanels.CreationPanel;
import subPanels.QuestionDisplayPanel;
import subPanels.QuizPanelSelect;
import subPanels.ResultPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements QuizListener
{
    public static final String QUESTIONS_XML = "questions.xml";
    public static final String CREATE_CONSTRAINT = "create";
    public static final String QUIZ_SELECT_CONSTRAINT = "quiz";
    public static final String QUESTION_DISPLAY_PANEL = "QPanel";
    public static final String CHECK_PANEL = "check";
    public static final String RESULT_PANEL = "result";

    private StudyQuiz studyQuiz;
    private CardLayout cardLayout;
    private CreationPanel createPanel;
    private QuizPanelSelect quizPanelSelect;
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
	createPanel = new CreationPanel(studyQuiz);
	quizPanelSelect = new QuizPanelSelect(studyQuiz);
	createQuestionDisplay();
	add(createPanel, CREATE_CONSTRAINT);
	add(quizPanelSelect, QUIZ_SELECT_CONSTRAINT);
	//cardLayout.show(this,CREATE_CONSTRAINT);
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

    private void displayAnswerCheck() {
        Question currentQuestion = studyQuiz.getCurrentQuestion();
	answerCheckPanel.setCorrectAnswer(currentQuestion.getAnswer());
	answerCheckPanel.setPlayerAnswer(studyQuiz.getLastSubmittedAnswer());
	cardLayout.show(this,CHECK_PANEL);
    }

    private void displayResult() {
	resultPanel.setResultCount(studyQuiz.generateResult());
	cardLayout.show(this,RESULT_PANEL);
    }

    private void updateQuestions() {
	questionPanel.showQuestion();
	cardLayout.show(this,QUESTION_DISPLAY_PANEL);
    }

    private void createQuestionDisplay() {
	questionPanel = new QuestionDisplayPanel(studyQuiz);
	answerCheckPanel = new AnswerCheckPanel(studyQuiz);
	resultPanel = new ResultPanel(studyQuiz);
	addQuizPanelsToCardLayout();
    }

    public void startQuizSelector() {
	quizPanelSelect.updateComboBox(studyQuiz.getRootXMLClass().getCategories());
	cardLayout.show(this, QUIZ_SELECT_CONSTRAINT);
    }

    private void addQuizPanelsToCardLayout() {
	add(questionPanel, QUESTION_DISPLAY_PANEL);
	add(answerCheckPanel, CHECK_PANEL);
	add(resultPanel, RESULT_PANEL);
    }

    private void removeQuizPanelsFromCardLayout() {
	remove(questionPanel);
	remove(answerCheckPanel);
	remove(resultPanel);
    }



    public void startCreate() {
	//removeQuizPanelsFromCardLayout();
	cardLayout.show(this,CREATE_CONSTRAINT);
    }

}
