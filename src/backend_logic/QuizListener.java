package backend_logic;

/**
 * Makes it so components that are interested in
 * what is happening in StudyQuiz knows when to update.
 */
public interface QuizListener
{

    public void quizChanged();

}
