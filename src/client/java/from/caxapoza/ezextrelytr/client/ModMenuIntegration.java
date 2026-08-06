package from.caxapoza.ezextrelytr.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import from.caxapoza.ezextrelytr.config.ModConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ModConfig config = ModConfig.load();

            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.translatable("title.ezextrelytr.config"));

            ConfigCategory category = builder.getOrCreateCategory(Text.translatable("category.ezextrelytr.general"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            // 1. Слайдер скорости (0.05 - 3.00)
            category.addEntry(entryBuilder.startIntSlider(
                            Text.translatable("option.ezextrelytr.forward_boost"),
                            Math.round(config.forwardBoost * 100.0f),
                            5,
                            300
                    )
                    .setDefaultValue(10)
                    .setTooltip(Text.translatable("tooltip.ezextrelytr.forward_boost"))
                    .setTextGetter(value -> Text.literal(String.format("%.2f", value / 100.0f)))
                    .setSaveConsumer(newValue -> config.forwardBoost = newValue / 100.0f)
                    .build());

            // 2. Слайдер частиц (0 - 200)
            category.addEntry(entryBuilder.startIntSlider(
                            Text.translatable("option.ezextrelytr.particle_amount"),
                            config.particleAmount,
                            0,
                            200
                    )
                    .setDefaultValue(50)
                    .setTooltip(Text.translatable("tooltip.ezextrelytr.particle_amount"))
                    .setTextGetter(value -> {
                        if (value == 0) {
                            return Text.translatable("option.ezextrelytr.particles.disabled");
                        }
                        return Text.literal(String.valueOf(value));
                    })
                    .setSaveConsumer(newValue -> config.particleAmount = newValue)
                    .build());

            // 3. Слайдер громкости (0% - 100%)
            category.addEntry(entryBuilder.startIntSlider(
                            Text.translatable("option.ezextrelytr.sound_volume"),
                            config.soundVolume,
                            0,
                            100
                    )
                    .setDefaultValue(80)
                    .setTooltip(Text.translatable("tooltip.ezextrelytr.sound_volume"))
                    .setTextGetter(value -> Text.literal(value + "%"))
                    .setSaveConsumer(newValue -> config.soundVolume = newValue)
                    .build());

            builder.setSavingRunnable(config::save);

            return builder.build();
        };
    }
}