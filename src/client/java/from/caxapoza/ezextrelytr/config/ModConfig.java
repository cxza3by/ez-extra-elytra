package from.caxapoza.ezextrelytr.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("ezextrelytr.json").toFile();

    public float forwardBoost = 0.10f; // Сила ускорения (W)
    public int particleAmount = 50;     // Количество частиц (0 - 200)
    public int soundVolume = 80;        // Громкость звука (0% - 100%)

    public static ModConfig load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                ModConfig config = GSON.fromJson(reader, ModConfig.class);
                if (config != null) {
                    return config;
                }
            } catch (IOException e) {
                System.err.println("[EZ Extra Elytra] Failed to load config, using defaults.");
            }
        }
        ModConfig defaultConfig = new ModConfig();
        defaultConfig.save();
        return defaultConfig;
    }

    public void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            System.err.println("[EZ Extra Elytra] Failed to save config!");
        }
    }
}