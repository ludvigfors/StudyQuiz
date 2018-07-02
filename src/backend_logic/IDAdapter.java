package backend_logic;


import javax.xml.bind.annotation.adapters.*;

/**
 * Makes it so a choosen element in the xml file gets a unique id. (in this case every question)
 */
public class IDAdapter extends XmlAdapter<Integer, Integer>
{

    private int counter = 1;
    @Override public Integer unmarshal(final Integer v) {
	counter++;
	return Integer.valueOf(counter);
    }

    @Override public Integer marshal(final Integer v) {
	counter++;
	return Integer.valueOf(counter);
    }
}
