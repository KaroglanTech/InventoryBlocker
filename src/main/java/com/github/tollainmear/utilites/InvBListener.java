package com.github.tollainmear.utilites;

import com.github.tollainmear.InventoryBlocker;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.advancement.AdvancementEvent;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.entity.ChangeEntityEquipmentEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.entity.TargetEntityEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.*;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

public class InvBListener {
    private InventoryBlocker plugin;

    public InvBListener(InventoryBlocker plugin) {
        this.plugin = plugin;
    }

    @Listener
    public void closingPlayersInventory(TargetEntityEvent e, @First Player player) {
        if (plugin.getBlockedMap().containsKey(player.getName())) {
            if (!isExpired(plugin.getBlockedMap().get(player.getName()))) {
                if(player.isViewingInventory()){

                    player.sendMessage(Text.of(player.getOpenInventory().isPresent()));
                }
                player.closeInventory();
            } else {
                plugin.getBlockedMap().remove(player.getName());
                player.sendMessage(Text.of("你已经被解除限制了"));
            }
        }
    }

    @Listener
    public void interactEvent(InteractBlockEvent e, @First Player player) {
        player.sendMessage(Text.of("事件监听中...."));
        if (plugin.getBlockedMap().containsKey(player.getName())) {
            if (!isExpired(plugin.getBlockedMap().get(player.getName()))) {
                player.sendMessage(Text.of("你现在不能打开背包。"));
                if (player.hasPermission(InventoryBlocker.getPluginid() + ".unlock")) {

                    Text unlockText = Text
                            .builder("如果你确定要打开背包的话，请点击这里")
                            .color(TextColors.GOLD)
                            .style(TextStyles.UNDERLINE)
                            .onClick(TextActions.runCommand("/inventorybloker unlock " + player.getName())).build();

                    player.sendMessage(unlockText);
                }
            }
        }
    }

    @Listener
    public void testEvent2(AdvancementEvent e, @First Player player) {
        player.sendMessage(Text.of("AdvancementEvent"));
    }

    private boolean isExpired(long endingTime) {
        long now = System.currentTimeMillis();
        return now > endingTime;
    }
}
