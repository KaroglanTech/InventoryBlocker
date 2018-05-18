package com.github.tollainmear;

import com.github.tollainmear.CommandExecutor.lockExecutor;
import com.github.tollainmear.CommandExecutor.mainExecutor;
import com.github.tollainmear.CommandExecutor.unlockExecutor;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class CommandManager {
    private CommandSpec lock;
    private CommandSpec unlock;

    public CommandManager(InventoryBlocker plugin){
        lock = CommandSpec.builder()
                .permission(InventoryBlocker.pluginid + ".lock")
                .description(Text.of("This command is using to prevent players to open their inventory"))
                .arguments(GenericArguments.seq(
                        GenericArguments.optional(GenericArguments.player(Text.of("player"))),
                        GenericArguments.integer(Text.of("duration"))))
                .executor(new lockExecutor())
                .build();

        unlock = CommandSpec.builder()
                .permission(InventoryBlocker.pluginid + ".unlock")
                .description(Text.of("This command is using to unlock the player who was limited to open their inventory"))
                .arguments(GenericArguments.optional(GenericArguments.player(Text.of("player"))))
                .executor(new unlockExecutor())
                .build();
    }


    public void init(InventoryBlocker plugin){
        Sponge.getCommandManager().register(plugin,mainCommand(),"inventoryblocker","inveb","invb","inb","ib");
    }

    private CommandCallable mainCommand(){
        return CommandSpec.builder()
                .description(Text.of("Main command"))
                .child(lock,"lock", "block")
                .child(unlock,"unlock","open","refresh")
                .executor(new mainExecutor())
                .build();
    }
}
