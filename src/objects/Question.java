package objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Question object consisting of the question and the answer
 * as well as a list of accepted answers that will work as well.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Question
{

    @XmlElementWrapper(name = "acceptedAnswers")
    @XmlElement(name = "answer")
    private List<String> acceptedAnswers = new ArrayList<>();
    private String query = null;
    private String answer = null;

    public Question(final String query, final String answer) {
	this.query = query;
	this.answer = answer;
	addGoodAnswer(answer);
    }

    public Question() {}

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

    public List<String> getAcceptedAnswers() {
	return acceptedAnswers;
    }

    public void setAcceptedAnswers(final List<String> acceptedAnswers) {
	this.acceptedAnswers = acceptedAnswers;
    }

    public void addGoodAnswer(final String goodAnswer){
	this.acceptedAnswers.add(goodAnswer);
    }

    public Integer getId() {
	final Integer id = Integer.valueOf(0);
	return id;
    }
}

