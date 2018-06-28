package objects;

import javax.xml.bind.annotation.*;

@XmlRootElement

//@XmlType
public class Question
{
    private String query;
    private String answer;

    public Question(final String query, final String answer) {
	this.query = query;
	this.answer = answer;
    }

    public Question() {
    }

    public String getQuery() {
	return query;
    }

    public void setQuery(final String query) {
	this.query = query;
    }

    public String getAnswer() {
	return answer;
    }

    public void setAnswer(final String answer) {
	this.answer = answer;
    }
}

