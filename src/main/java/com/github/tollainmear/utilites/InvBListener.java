package com.github.tollainmear.utilites;

import com.github.tollainmear.InventoryBlocker;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.ChangeEntityEquipmentEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.item.inventory.*;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

public class InvBListener {
    private InventoryBlocker plugin;
    public InvBListener(InventoryBlocker plugin){
        this.plugin = plugin;
    }

    @Listener
    public void onOpenInventory(InteractInventoryEvent.Open e, @First Player player){
        player.sendMessage(Text.of("检测到打开背包事件"));
        if (plugin.getBlockedMap().containsKey(player.getName())){
            if (!isExpired(plugin.getBlockedMap().get(player.getName()))){
                player.closeInventory();
                player.sendMessage(Text.of("你现在不能打开背包。"));
                if (player.hasPermission(InventoryBlocker.getPluginid() + ".unlock")){

                    Text unlockText = Text
                            .builder("如果你确定要打开背包的话，请点击这里")
                            .color(TextColors.GOLD)
                            .style(TextStyles.UNDERLINE)
                            .onClick(TextActions.runCommand("/inventorybloker unlock "+ player.getName())).build();

                    player.sendMessage(unlockText);
                }
            }
            else {
                plugin.getBlockedMap().remove(player.getName());
                player.sendMessage(Text.of("移除"));
            }
        }
    }
    @Listener
    public void testEvent1(InteractInventoryEvent.Close e, @First Player player){
        player.sendMessage(Text.of("Click Inventory"));
    }

    private boolean isExpired(long endingTime){
        long now = System.currentTimeMillis();
        return now > endingTime;
    }
}
