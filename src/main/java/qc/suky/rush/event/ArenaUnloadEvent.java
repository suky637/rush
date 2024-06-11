package qc.suky.rush.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import qc.suky.rush.RushArena;

@Getter
public final class ArenaUnloadEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private final RushArena arena;
	@Setter
	@Getter
	private boolean cancelled;

	public ArenaUnloadEvent(RushArena arena) {
		this.arena = arena;
	}

	public @NotNull HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}