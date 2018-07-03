import backend_logic.StudyQuiz;
import main_viewing.MainFrame;


/**
 *
 */
public final class StudyQuizStart
{
    private StudyQuizStart() {}

    public static void main(String[] args) {
	StudyQuiz studyQuiz = new StudyQuiz(); //Handles the logic
        new MainFrame(studyQuiz).showFrame();
    }
}
