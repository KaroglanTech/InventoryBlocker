package com.github.tollainmear.CommandExecutor;

import com.github.tollainmear.InventoryBlocker;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.type.Inventory2D;
import org.spongepowered.api.text.Text;

public class lockExecutor implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player target;

        //判断输入的数字是否为负数
        if ((int)args.getOne(Text.of("duration")).get() < 0){
            src.sendMessage(Text.of("你必须指定一个非负数作为封禁的秒数"));
        }
        //判断控制台执行是否参数齐全
        if (!(args.getOne("player").isPresent())){
            if (!(src instanceof Player)){
                src.sendMessage(Text.of("请指定一个玩家再尝试执行此命令！"));
                return CommandResult.success();
            }
        }else {
            target = (Player) (args.getOne(Text.of("player")).get());
        }
        //判断玩家是否指定了可选对象
        if (src instanceof Player){
            if (!(args.getOne("player").isPresent())){
                target = (Player) src;
            }
            else {
                target = (Player) args.getOne(Text.of("player")).get();
            }

        }else return CommandResult.success();
            Long ending = System.currentTimeMillis() + (int)args.getOne(Text.of("duration")).get() * 1000;
            InventoryBlocker.getInstance().getBlockedMap().put(target.getName(),ending);
            target.closeInventory();
            if (target.isOnline()){
                if (target.isViewingInventory()){
                }
            }
            return CommandResult.success();
    }
}
