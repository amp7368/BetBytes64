package com.ambrosia.add.discord;

import apple.lib.modules.AppleModule;
import apple.lib.modules.configs.factory.AppleConfigLike;
import com.ambrosia.add.discord.create.CreateProfileCommand;
import java.util.List;
import lib.DCF;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class DiscordModule extends AppleModule {

    @Override
    public void onEnable() {
        JDABuilder builder = JDABuilder.createLight(DiscordConfig.get().token);
        JDA jda = builder.build();
        jda.getPresence().setPresence(Activity.playing("!"), false);
        DCF dcf = new DCF(jda);
        dcf.commands().addCommand(new CreateProfileCommand());
        dcf.commands().updateCommands();
    }


    @Override
    public List<AppleConfigLike> getConfigs() {
        return List.of(configJson(DiscordConfig.class, "Discord.config"), configJson(DiscordPermissions.class, "Permissions.config"));
    }

    @Override
    public String getName() {
        return "Discord";
    }
}
