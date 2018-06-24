package objects;

public class Question
{
    private String category;
    private String question;
    private String answer;

    public Question(){

    }

    public Question(final String category, final String question, final String answer) {
	this.category = category;
	this.question = question;
	this.answer = answer;
    }

    public String getCategory() {
	return category;
    }

    public void setCategory(final String category) {
	this.category = category;
    }

    public String getQuestion() {
	return question;
    }

    public void setQuestion(final String question) {
	this.question = question;
    }

    public String getAnswer() {
	return answer;
    }

    public void setAnswer(final String answer) {
	this.answer = answer;
    }
}
