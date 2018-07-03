package main_viewing;


import backend_logic.StudyQuiz;

import javax.swing.*;
import java.awt.*;


/**
 * The frame that contains the mainpanel.
 */
public class MainFrame extends JFrame
{
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
        //setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
