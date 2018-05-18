package com.github.tollainmear;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import java.util.LinkedHashMap;
import java.util.Map;

@Plugin(id ="inventoryblocker",name = "InventoryBlocker", version = "1.0",authors = "Tollainmear")
public class InventoryBlocker {
    private Map<String,Boolean> blockedMap;

    @Listener
    public void onInit(GameStartedServerEvent e){
        blockedMap = new LinkedHashMap<>();
    }

}
