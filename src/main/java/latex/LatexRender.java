package latex;

import lombok.RequiredArgsConstructor;
import org.scilab.forge.jlatexmath.ParseException;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@RequiredArgsConstructor
public class LatexRender {
    private final LatexBuilder config;

    public BufferedImage render(String texFormula) throws ParseException {
        TeXIcon icon = new TeXFormula(texFormula).createTeXIcon(config.style(), config.size());

        int width = icon.getIconWidth() + config.padding() * 2 + config.borderSize();
        int height = icon.getIconHeight() + config.padding() * 2 + config.borderSize();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        paintFormulaIconOnImage(icon, image);

        return image;
    }

    private void paintFormulaIconOnImage(TeXIcon icon, BufferedImage image) {
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(config.borderColor());
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        graphics.setColor(config.background());
        graphics.fillRect(config.borderSize(), config.borderSize(),
                image.getWidth() - config.borderSize() * 2, image.getHeight() - config.borderSize() * 2);

        JLabel component = new JLabel();
        component.setForeground(config.foreground());

        icon.paintIcon(component, graphics, config.padding(), config.padding());
    }

}
