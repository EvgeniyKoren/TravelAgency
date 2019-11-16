package ua.nure.koren.summaryTask4.web.tagHandlers;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class StarsReflection extends TagSupport {

    private static final char STAR_SYMBOL = '\u2606';

    private int stars;

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        StringBuilder starContainer = new StringBuilder();
        for (int i = 0; i < stars; i++) {
            starContainer.append(STAR_SYMBOL);
        }

        try {
            out.println(starContainer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
