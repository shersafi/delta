package latex;

import lombok.Data;
import lombok.experimental.Accessors;
import org.scilab.forge.jlatexmath.TeXConstants;

import java.awt.*;

@Data
@Accessors(fluent = true)
public class LatexBuilder {

    private int style = TeXConstants.STYLE_DISPLAY;
    private int size = 24;
    private int padding = 30;
    private Color background = Color.WHITE;
    private Color foreground = Color.BLACK;
    private int borderSize = 2;
    private Color borderColor = Color.DARK_GRAY;

    public LatexRender build() {
        return new LatexRender(this);
    }
}
