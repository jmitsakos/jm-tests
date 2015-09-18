package general.test.activemq.spring;

import java.io.Serializable;
import java.util.Map;

/**
 * User: i.mitsakos
 * Date: 2/11/2011
 * Time: 2:33 μμ
 */
public class JmsObjectMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String text;
    private Map<String,String> params;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
