package mainViewing;


import backend_logic.StudyQuiz;
import objects.RootXMLClass;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame
{
    public static final String QUESTIONS_XML = "questions.xml";
    public static final String CREATE_CONSTRAINT = "create";
    public static final String QUIZ_CONSTRAINT = "quiz";

    private RootXMLClass rootXMLFile; // WHY NOT CHANGE??

    public MainFrame(final StudyQuiz studyQuiz){
	super("Study Quiz");
	MainPanel mainPanel = new MainPanel(studyQuiz);
	setLayout(new BorderLayout());
	studyQuiz.addQuizListener(mainPanel);
	add(mainPanel, BorderLayout.CENTER);
    }

    public void showFrame(){
        pack();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
