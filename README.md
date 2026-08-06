# EZ Extra Elytra

![Minecraft Version|1.20.1](https://img.shields.io/badge/minecraft_version-1.20.1-green?style=for-the-badge)
![Mod Loader|0.19.3](https://img.shields.io/badge/fabric_loader-0.19.3-blue?style=for-the-badge)

Tired of constantly crafting and carrying fireworks? **Not anymore!** With this mod, you can accelerate and (in the future) decelerate absolutely for free.

*Inspired by a similar feature in the [Wurst Client](https://wurstclient.net).*

---

## ⚙️ How it Works

The mod utilizes smooth **client-side vector acceleration**. When you activate the boost, the mod directly modifies your player's movement velocity vector (`addVelocity`).

Your Minecraft client then natively calculates the updated trajectory and syncs it with the server using standard movement packets. Because it works seamlessly with the game's built-in physics engine rather than hard-overriding your position, it retains full compatibility with motion and camera mods like *Do A Barrel Roll* and *FlightAssistant*!

---

> [!WARNING]
> **Use at your own risk:** Due to the aggressive transmission of modified packets, anti-cheat systems or servers might flag this behavior. In the best-case scenario, you will be kicked; in the worst-case, you might get banned.

---

## 🚀 Installation
1. Make sure you have **[Fabric Loader](https://fabricmc.net)** installed for Minecraft 1.20.1.
2. Download the mod `.jar` file.
3. Drop the file into your Minecraft `mods` folder.
4. Launch the game and enjoy!

---

## 💡 The Backstory
The idea is simple. I noticed there were no lightweight standalone alternatives to this specific feature. I didn't want to install huge, bloated cheat clients just for one function, so I decided to make a clean, lightweight mod focused entirely on simple acceleration and deceleration.

---

## 🗺️ Roadmap / Future Plans
- [ ] Port the mod to Minecraft versions above 1.20.1.
- [ ] Implement the deceleration mechanic.
- [ ] Intercept default movement keybinds (forward/backward) so the acceleration automatically adapts to your custom controls.
