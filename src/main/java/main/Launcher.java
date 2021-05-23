package main;

import cmds.Cmd;
import image.ByteImages;
import latex.LatexBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

@Slf4j
@RequiredArgsConstructor
public class Launcher {

    public static String prefix = "-";



    public static void main(String[] args) throws LoginException {
        try {
            String token = "";
            JDABuilder builder = JDABuilder.createDefault(token);
            JDA jda = builder.build();
            jda.getPresence().setStatus(OnlineStatus.IDLE);
            jda.getPresence().setActivity(Activity.playing("α"));
            jda.addEventListener(new Cmd(new LatexBuilder().build(), new ByteImages()));

        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

}
