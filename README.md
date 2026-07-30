# EZ Extra Elytra

![Minecraft Version|148](https://shields.io)
![Mod Loader|107](https://shields.io)

Tired of constantly crafting and carrying fireworks? **Not anymore!** With this mod, you can accelerate and (in the future) decelerate absolutely for free.

*Inspired by a similar feature in the [Wurst Client](https://wurstclient.net).*

---

## ⚙️ How it Works
The mod utilizes simple **packet spoofing**. When you hold `R` (and `W`/`S` in future updates), your client sends a specific movement packet to the server. The server registers this as a legitimate physics-based acceleration and processes it. As a result, your speed boosts from the base walking speed (0.1) up to a whopping 0.8!

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
