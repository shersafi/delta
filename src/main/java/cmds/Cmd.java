package cmds;

import latex.LatexRender;
import image.ByteImages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.scilab.forge.jlatexmath.ParseException;

import main.Launcher;

import java.util.Arrays;
import static java.util.regex.Pattern.*;
import java.io.IOException;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Slf4j
public class Cmd extends ListenerAdapter {

    private final LatexRender latexRender;
    private final ByteImages byteImages;

    public void onMessageReceived(MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split("\\s+");
        String arg = Arrays.toString(args).substring(7).replace(",", "").replace("[", "").replace("]", "").trim();

        if(args[0].equalsIgnoreCase(Launcher.prefix + "latex")) {

            try {
                event.getChannel().sendFile(byteImages.saveAsPng(latexRender.render(arg)), "done.png").queue();
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }

        }
    }

}
