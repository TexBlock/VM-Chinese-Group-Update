package top.vmctcn.vmtranslationupdate;

import dev.architectury.event.events.common.PlayerEvent;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.*;
import top.vmctcn.vmtranslationupdate.util.ConfigUtil;
import top.vmctcn.vmtranslationupdate.util.DownloadUtil;

public class VMTranslationUpdateMod {
    public static final String MOD_ID = "vmtranslationupdate";
    
    public static void init() {
        PlayerEvent.PLAYER_JOIN.register((player) -> {
            String localVersion = ConfigUtil.getConfig().translationVersion;
            String onlineVersion = DownloadUtil.getOnlineVersion(player);
            String downloadUrl = DownloadUtil.getDownloadUrl();

            if (onlineVersion != null && !localVersion.equals(onlineVersion)) {
                player.sendSystemMessage(Component.translatable("vmtranslationupdate.message.update", player.getDisplayName().getString(), localVersion, DownloadUtil.getOnlineVersion(player)));

                Component message = Component.translatable("vmtranslationupdate.message.update2")
                        .append(Component.translatable(downloadUrl).withStyle(Style.EMPTY
                                .withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, downloadUrl))
                                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.translatable("vmtranslationupdate.message.hover")))
                                .withColor(ChatFormatting.AQUA)
                        ))
                        .append(Component.translatable("vmtranslationupdate.message.update3"));

                player.sendSystemMessage(message);
            }
        });
    }
}
