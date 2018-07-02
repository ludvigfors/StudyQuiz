package objects;

import tools.IDAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType
public class Question
{
    @XmlAttribute
    @XmlJavaTypeAdapter(IDAdapter.class)
    private Integer id = 0;

    @XmlElementWrapper(name = "goodAnswers")
    @XmlElement(name = "answer")
    private ArrayList<String> goodAnswers = new ArrayList<>();
    private String query;
    private String answer;

    public Question(final String query, final String answer) {
	this.query = query;
	this.answer = answer;
	addGoodAnswer(answer);
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

    public ArrayList<String> getAcceptedAnswers() {
	return goodAnswers;
    }

    public void setGoodAnswers(final ArrayList<String> goodAnswers) {
	this.goodAnswers = goodAnswers;
    }

    public void addGoodAnswer(final String goodAnswer){
	this.goodAnswers.add(goodAnswer);
    }

    public Integer getId() {
	return id;
    }
}

