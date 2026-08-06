package from.caxapoza.ezextrelytr.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import from.caxapoza.ezextrelytr.config.ModConfig;
import from.caxapoza.ezextrelytr.config.ParticleMode;
import from.caxapoza.ezextrelytr.config.ParticleStyle;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ModConfig config = ModConfig.get();

            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.translatable("title.ezextrelytr.config"));

            ConfigCategory category = builder.getOrCreateCategory(Text.translatable("category.ezextrelytr.general"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            category.addEntry(entryBuilder.startIntSlider(
                            Text.translatable("option.ezextrelytr.forward_boost"),
                            Math.round(config.forwardBoost * 100.0f), 5, 300)
                    .setDefaultValue(10)
                    .setTooltip(Text.translatable("tooltip.ezextrelytr.forward_boost"))
                    .setTextGetter(value -> Text.literal(String.format("%.2f", value / 100.0f)))
                    .setSaveConsumer(newValue -> config.forwardBoost = newValue / 100.0f)
                    .build());

            category.addEntry(entryBuilder.startIntSlider(
                            Text.translatable("option.ezextrelytr.nitro_multiplier"),
                            Math.round(config.nitroMultiplier * 10.0f), 11, 30)
                    .setDefaultValue(18)
                    .setTooltip(Text.translatable("tooltip.ezextrelytr.nitro_multiplier"))
                    .setTextGetter(value -> Text.literal(String.format("%.1fx", value / 10.0f)))
                    .setSaveConsumer(newValue -> config.nitroMultiplier = newValue / 10.0f)
                    .build());

            category.addEntry(entryBuilder.startIntSlider(
                            Text.translatable("option.ezextrelytr.brake_force"),
                            Math.round(config.brakeForce * 100.0f), 5, 50)
                    .setDefaultValue(15)
                    .setTooltip(Text.translatable("tooltip.ezextrelytr.brake_force"))
                    .setTextGetter(value -> Text.literal(value + "%"))
                    .setSaveConsumer(newValue -> config.brakeForce = newValue / 100.0f)
                    .build());

            category.addEntry(entryBuilder.startEnumSelector(
                            Text.translatable("option.ezextrelytr.particle_style"),
                            ParticleStyle.class,
                            config.particleStyle)
                    .setDefaultValue(ParticleStyle.FLAME)
                    .setTooltip(Text.translatable("tooltip.ezextrelytr.particle_style"))
                    .setEnumNameProvider(enumValue -> Text.translatable("enum.ezextrelytr.style." + enumValue.name().toLowerCase()))
                    .setSaveConsumer(newValue -> config.particleStyle = newValue)
                    .build());

            category.addEntry(entryBuilder.startEnumSelector(
                            Text.translatable("option.ezextrelytr.particle_mode"),
                            ParticleMode.class,
                            config.particleMode)
                    .setDefaultValue(ParticleMode.INTERPOLATED)
                    .setTooltip(Text.translatable("tooltip.ezextrelytr.particle_mode"))
                    .setEnumNameProvider(enumValue -> Text.translatable("enum.ezextrelytr.mode." + enumValue.name().toLowerCase()))
                    .setSaveConsumer(newValue -> config.particleMode = newValue)
                    .build());

            category.addEntry(entryBuilder.startIntSlider(
                            Text.translatable("option.ezextrelytr.particle_amount"),
                            config.particleAmount, 0, 200)
                    .setDefaultValue(50)
                    .setTooltip(Text.translatable("tooltip.ezextrelytr.particle_amount"))
                    .setTextGetter(value -> value == 0 ? Text.translatable("option.ezextrelytr.particles.disabled") : Text.literal(String.valueOf(value)))
                    .setSaveConsumer(newValue -> config.particleAmount = newValue)
                    .build());

            category.addEntry(entryBuilder.startIntSlider(
                            Text.translatable("option.ezextrelytr.sound_volume"),
                            config.soundVolume, 0, 100)
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