# Ugorge
这个 Minecraft 模组能够让名为「UgoCraft（机关）」的古老模组，在 1.7.10 版本的 Forge 环境下正常运行。
<br>
**请绝对不要去原作者 maocat 的官方页面报告关于本模组的 Bug。**

### 运行环境
 - Minecraft: 1.7.10
 - Forge: 1.7.10-10.13.4.1614
 - UgoCraft: 1.7.10

### 安装方法
 - 将本模组（Ugorge）的 jar 文件放入 `mods` 文件夹中，然后启动一次 Minecraft。
 - 由于此时 UgoCraft 的 jar 本体文件还不存在，游戏会崩溃一次，但游戏目录下会自动生成一个 `ugocraft` 文件夹。
 - 请将额外下载好的 UgoCraft 本体 jar 文件放入该文件夹中。（你也可以一开始就手动创建 `ugocraft` 文件夹并把核心文件放进去）
 - 再次启动游戏，搭载了 UgoCraft 模块的 Minecraft 应该就能顺利运行了~

### 已知问题
 - 如果安装了 EntityCulling 模组，会导致较小的 UgoObject（活动方块群体）变得不可见。

### 如果无法启动
请确认以下几点：
 - 放入 `ugocraft` 文件夹中的 UgoCraft 本体，其对应的 Minecraft 版本是否是 1.7.10？
 - 放入 `ugocraft` 文件夹中的 UgoCraft 模组 jar 文件名，是否已经被重命名为了 **UgoCraft_Client.jar**？

<br>
因此，如果你尝试了上述步骤仍无法解决问题，欢迎提交 Issue 来报告 Bug，这将对未来的改进有很大帮助。
<br>
提交时，如果能附上你的操作系统版本、安装的其他模组等环境说明，以及游戏启动时的完整日志，我们将不胜感激。