package tools;


import javax.xml.bind.annotation.adapters.*;

public class IDAdapter extends XmlAdapter<Integer, Integer>
{

    private int counter = 1;
    @Override public Integer unmarshal(final Integer v) throws Exception {
	return counter++;
    }

    @Override public Integer marshal(final Integer v) throws Exception {
	return counter++;
    }
}
