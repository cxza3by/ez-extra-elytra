package from.caxapoza.ezextrelytr.client;

import from.caxapoza.ezextrelytr.config.ModConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class EzextrelytrClient implements ClientModInitializer {

    private static final Random RANDOM = new Random();
    private int soundCooldown = 0;

    @Override
    public void onInitializeClient() {
        ModConfig.get();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ClientPlayerEntity player = client.player;

            if (player != null && player.isFallFlying()) {
                handleElytraFlight(player, client);
            }
        });
    }

    private void handleElytraFlight(ClientPlayerEntity player, MinecraftClient client) {
        ModConfig config = ModConfig.get();

        if (soundCooldown > 0) {
            soundCooldown--;
        }

        boolean isNitro = client.options.sprintKey.isPressed();
        float currentBoost = config.forwardBoost * (isNitro ? config.nitroMultiplier : 1.0f);
        float volumeMultiplier = config.soundVolume / 100.0f;

        // 1. Ускорение (W)
        if (client.options.forwardKey.isPressed()) {
            Vec3d look = player.getRotationVector();
            player.addVelocity(
                    look.x * currentBoost,
                    look.y * currentBoost,
                    look.z * currentBoost
            );

            spawnBoostParticles(player, look, isNitro, config.particleAmount);

            if (soundCooldown == 0 && volumeMultiplier > 0.01f) {
                // Нитро на +1.5 dB (x1.1885) громче основного звука
                float baseVol = 0.5f * volumeMultiplier;
                float finalVol = isNitro ? (baseVol * 1.1885f) : baseVol;

                player.getWorld().playSound(
                        player,
                        player.getBlockPos(),
                        isNitro ? SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST : SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH,
                        SoundCategory.PLAYERS,
                        finalVol,
                        isNitro ? 1.4f : 1.0f
                );
                soundCooldown = isNitro ? 6 : 10;
            }
        }

        // 2. Торможение (S)
        if (client.options.backKey.isPressed()) {
            Vec3d vel = player.getVelocity();
            double retention = Math.max(0.0, 1.0 - config.brakeForce);
            player.setVelocity(vel.x * retention, vel.y * retention, vel.z * retention);

            spawnBrakeParticles(player, config.particleAmount);

            if (soundCooldown == 0 && volumeMultiplier > 0.01f) {
                player.getWorld().playSound(
                        player,
                        player.getBlockPos(),
                        SoundEvents.BLOCK_FIRE_EXTINGUISH,
                        SoundCategory.PLAYERS,
                        0.4f * volumeMultiplier,
                        1.2f
                );
                soundCooldown = 8;
            }
        }

        // 3. Подъём (Space)
        if (client.options.jumpKey.isPressed()) {
            player.addVelocity(0, 0.04 * (isNitro ? config.nitroMultiplier * 0.7 : 1.0), 0);
        }

        // 4. Спуск (Shift)
        if (client.options.sneakKey.isPressed()) {
            player.addVelocity(0, -0.04 * (isNitro ? config.nitroMultiplier * 0.7 : 1.0), 0);
        }
    }

    private void spawnBoostParticles(ClientPlayerEntity player, Vec3d look, boolean isNitro, int particleAmount) {
        if (particleAmount <= 0) return;

        int count = Math.max(1, particleAmount / 15);
        if (isNitro) count = (int) (count * 1.5);

        for (int i = 0; i < count; i++) {
            double offsetX = (RANDOM.nextDouble() - 0.5) * 0.4;
            double offsetY = (RANDOM.nextDouble() - 0.5) * 0.4;
            double offsetZ = (RANDOM.nextDouble() - 0.5) * 0.4;

            double px = player.getX() - look.x * 0.6 + offsetX;
            double py = player.getBodyY(0.5) - look.y * 0.6 + offsetY;
            double pz = player.getZ() - look.z * 0.6 + offsetZ;

            double vx = -look.x * 0.2 + (RANDOM.nextDouble() - 0.5) * 0.05;
            double vy = -look.y * 0.2 + (RANDOM.nextDouble() - 0.5) * 0.05;
            double vz = -look.z * 0.2 + (RANDOM.nextDouble() - 0.5) * 0.05;

            player.getWorld().addParticle(
                    isNitro ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME,
                    px, py, pz,
                    vx, vy, vz
            );
        }
    }

    private void spawnBrakeParticles(ClientPlayerEntity player, int particleAmount) {
        if (particleAmount <= 0) return;

        int count = Math.max(1, particleAmount / 20);
        for (int i = 0; i < count; i++) {
            double px = player.getX() + (RANDOM.nextDouble() - 0.5) * 0.8;
            double py = player.getBodyY(0.5) + (RANDOM.nextDouble() - 0.5) * 0.8;
            double pz = player.getZ() + (RANDOM.nextDouble() - 0.5) * 0.8;

            player.getWorld().addParticle(
                    ParticleTypes.CLOUD,
                    px, py, pz,
                    0, 0, 0
            );
        }
    }
}