package ua.nure.koren.summaryTask4.web.tagHandlers;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class BurningReflection extends TagSupport {

    private static final String FIRE_SYMBOL = "\uD83D\uDD25";

    private boolean isLastMinute;

    public void setLastMinute(boolean lastMinute) {
        isLastMinute = lastMinute;
    }

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        if (isLastMinute) {
            try {
                out.println(FIRE_SYMBOL + FIRE_SYMBOL + FIRE_SYMBOL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return SKIP_BODY;
    }
}
