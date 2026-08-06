package from.caxapoza.ezextrelytr.config;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;

public enum ParticleStyle {
    FLAME(ParticleTypes.FLAME, ParticleTypes.SOUL_FIRE_FLAME),
    END_ROD(ParticleTypes.END_ROD, ParticleTypes.END_ROD),
    DRAGON_BREATH(ParticleTypes.DRAGON_BREATH, ParticleTypes.DRAGON_BREATH),
    PORTAL(ParticleTypes.PORTAL, ParticleTypes.REVERSE_PORTAL),
    FIREWORK(ParticleTypes.FIREWORK, ParticleTypes.FIREWORK);

    private final DefaultParticleType normalParticle;
    private final DefaultParticleType nitroParticle;

    ParticleStyle(DefaultParticleType normalParticle, DefaultParticleType nitroParticle) {
        this.normalParticle = normalParticle;
        this.nitroParticle = nitroParticle;
    }

    public DefaultParticleType getParticle(boolean isNitro) {
        return isNitro ? nitroParticle : normalParticle;
    }
}