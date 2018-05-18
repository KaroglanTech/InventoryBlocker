package com.github.tollainmear.CommandExecutor;

import com.github.tollainmear.InventoryBlocker;
import com.github.tollainmear.utilites.InvBListener;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class unlockExecutor implements CommandExecutor {
    InventoryBlocker plugin = InventoryBlocker.getInstance();
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        String target = ((Player)args.getOne(Text.of("player")).get()).getName();
        if (plugin.getBlockedMap().containsKey(target)){
            plugin.getBlockedMap().remove(target);
            src.sendMessage(Text.of("�Ѿ��� " + target +"��������"));
        }else src.sendMessage(Text.of(target + "��ǰû�б����Ʊ�����"));
        return CommandResult.success();
    }
}
