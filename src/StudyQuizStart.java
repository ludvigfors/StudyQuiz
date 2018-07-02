package backend_logic;

import mainViewing.MainFrame;

import javax.xml.bind.JAXBException;

/**
 *
 */
public final class StudyQuizStart
{

    private StudyQuizStart() {}

    public static void main(String[] args) throws JAXBException {
	StudyQuiz studyQuiz = new StudyQuiz(); //Handles the logic
        new MainFrame(studyQuiz).showFrame();
    }
}
