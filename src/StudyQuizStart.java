import backend_logic.StudyQuiz;
import main_viewing.MainFrame;


/**
 *
 */
public final class StudyQuizStart
{
    /**
        * Width of window.
        */
       public static final int WIDTH = 360;
       /**
        * Height of window.
        */
       public static final int HEIGHT= 160;

    private StudyQuizStart() {}

    public static void main(String[] args) {
	StudyQuiz studyQuiz = new StudyQuiz(); //Handles the logic
        new MainFrame(studyQuiz).showFrame();
    }
}
