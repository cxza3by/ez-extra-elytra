package from.caxapoza.ezextrelytr.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class EzextrelytrClient implements ClientModInitializer {
    private static KeyBinding boostKey;

    @Override
    public void onInitializeClient() {
        boostKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ezextrelytr.boost",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.ezextrelytr.title"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (boostKey.isPressed() && client.player != null && client.player.isFallFlying()) {
                Vec3d lookVec = client.player.getRotationVector();
                double speed = 0.08;

                client.player.addVelocity(lookVec.x * speed, lookVec.y * speed, lookVec.z * speed);
            }
        });
    }
}