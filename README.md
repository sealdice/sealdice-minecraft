# sealdice-minecraft
该仓库是一个PaperMC插件用于支持海豹的minecraft接入

This repo is a paperMC plugin supports sealdice minecraft adapter 

## 第一次使用方法
1. 前往 https://papermc.io/ 按照指示下载PaperMC服务端
2. 运行一次之后，打开目录中的eula.txt 将里面的 eula=false 改成 eula=true
3. 然后将从 Release 中下载的 jar 文件放进 plugins 文件夹中
4. 再次运行服务器，你应该就可以使用了

## 如何将海豹对接MC服务器
1. 插件将会开启一个端口，默认为 8887 端口 供海豹使用
2. 在海豹的帐号添加页面选择 Minecraft服务器(Paper) 
3. 按照 ip:端口 的格式填入输入框内，然后点下一步

## 如何修改连接端口
使用游戏内指令 /sealport 端口号(整数) 该指令需要op权限，在控制台也可执行

### 游戏内的其他指令
/sealdice 文本... 使用该指令的玩家视为向海豹私聊发送了一条消息 但是命令方块和控制台使用该指令会被视为公屏发送