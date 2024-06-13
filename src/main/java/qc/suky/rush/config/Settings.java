package qc.suky.rush.config;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import lombok.Getter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@Getter
@Configuration
@SuppressWarnings("FieldMayBeFinal")
public class Settings {

	@Comment("The world where players will spawn.")
	private String spawn_world = "world";

	@Comment("The location where players will spawn.")
	private Location spawn_location; // spawn_world can be removed when this is implemented.
	// I don't think this config lib supports Locations by default, you can add a type adapter for it.
	// I'll try to implement it later

	@Comment("The list of arenas.")
	private List<ArenaSettings> arenas = new ArrayList<>();

	@Getter
	@Configuration
	public static class ArenaSettings {
		private String name;
		private List<TeamSettings> teams = List.of(new TeamSettings());
		private List<SpawnerSettings> spawners = List.of(new SpawnerSettings());

		@Getter
		@Configuration
		public static class TeamSettings {
			private String name = "White";
			private String color = "FF00000";
			private String spawn_x = "0";
			private String spawn_y = "0";
			private String spawn_z = "0";

		}

		@Getter
		@Configuration
		public static class SpawnerSettings {
			private String type = "bronze";
			private String spawn_x = "0";
			private String spawn_y = "0";
			private String spawn_z = "0";
		}
	}

}
