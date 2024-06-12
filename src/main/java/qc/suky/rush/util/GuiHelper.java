package qc.suky.rush.util;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import qc.suky.rush.RushArena;

public class GuiHelper {

	public static void addOuter(ChestGui gui) {
		ItemStack border_item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
		ItemMeta border_meta = border_item.getItemMeta();
		Component border_name = MiniMessage.miniMessage().deserialize("<gray>Select</gray>");
		border_meta.setDisplayName(LegacyComponentSerializer.legacySection().serialize(border_name));
		border_item.setItemMeta(border_meta);

		Pattern pattern = new Pattern(
				"111111111",
				"100000001",
				"100000001",
				"100000001",
				"100000001",
				"111111111"
		);
		PatternPane background = new PatternPane(0, 0, 9, 6, pattern);
		background.bindItem('1', new GuiItem(border_item));
		gui.addPane(background);
	}

	public static GuiItem createGuiItem(RushArena arena) {
		ItemStack item = new ItemStack(Material.GRASS_BLOCK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(arena.getName());
		item.setItemMeta(meta);
		return new GuiItem(item, event -> {
			arena.addPlayer((Player) event.getWhoClicked());
		});
	}


}
