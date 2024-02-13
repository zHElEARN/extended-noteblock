# 一点小记

原版中每个音色只有一个音频文件

例如Harp对于harp.ogg

每个音色的音调都是F#4

Minecraft中`World.playSound()`有一个`pitch`参数可以修改音调

音调和`pitch`的转换方式如下`float pitch = (float)Math.pow(2.0D, (double)(notePitch - 12) / 12.0D);`

其中当`notePitch`为0时所表示的音是F#3，此时的`pitch`是0.5

当`notePitch`为24的时候所表示的音是F#5，此时的`pitch`是2

`notePitch`就是游戏中音符盒方块的`note`状态

调整音调的`pitch`范围是从`0.5`到`2.0`

在Audition中，想要调整一段音频的音调可以在`效果-伸缩与变调`中调整

如果我们锁定伸缩和变调的话，那么此时在Audition中变调的方式就和Minecraft中一致

因为在Minecraft通过调整pitch的原理就是伸缩和变调音频

就先记到这里
