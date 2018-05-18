package com.github.tollainmear;

import com.github.tollainmear.utilites.InvBListener;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import java.util.LinkedHashMap;
import java.util.Map;

@Plugin(id ="inventoryblocker",name = "InventoryBlocker", version = "1.0",authors = "Tollainmear",description = "Prevent players open their inventories")
public class InventoryBlocker {
    public static final String pluginid = "inventoryblocker";
    private static InventoryBlocker instance;
    private Map<String,Long> blockedMap;
    private CommandManager cmdManager;
    private InvBListener listener;

    @Listener
    public void onInit(GameStartedServerEvent e){
        instance = this;
        cmdManager = new CommandManager(this);
        cmdManager.init(this);
        blockedMap = new LinkedHashMap<>();
        listener = new InvBListener(this);
        Sponge.getEventManager().registerListeners(this,listener);
    }


    public Map<String, Long> getBlockedMap() {
        return blockedMap;
    }

    public static String getPluginid() {
        return pluginid;
    }

    public static InventoryBlocker getInstance() {
        return instance;
    }
}
