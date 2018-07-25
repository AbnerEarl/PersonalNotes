@echo off

echo 郑重声明：本软件由杨氏集团独家研发，最终解释权归杨氏集团所有！

echo 开始清理系统垃圾文件，观看屏幕动态，可以查看垃圾处理情况......

rd /s /q C:\360SANDBOX\

rd /s /q C:\Config.Msi\

rd /s /q C:\MAXREPORT\

rd /s /q C:\MFG\

rd /s /q C:\Intel\

rd /s /q C:\MSOCache\

rd /s /q C:\Perflogs\

rd /s /q C:\swshare\

rd /s /q C:\SWTOOLS\

rd /s /q C:\FavoriteVideo\

rd /s /q C:\KanKan\

rd /s /q C:\MediaServer\

rd /s /q C:\MyDrivers\

rd /s /q C:\迅雷下载\

rd /s /q C:\Users\Administrator\Documents\Tencent Files\

rd /s /q C:\Users\Administrator\Documents\Tencent Files\

del C:\p2pdown_30.log

del C:\setup.log

pushd "C:\Windows\SoftwareDistribution\Download\"

del * /q

for /d %%F in (*) do rd /s /q "%%F"

popd

pushd "%USERPROFILE%\AppData\Local\Temp\"

pushd "C:\Windows\Temp\"

pushd "%USERPROFILE%\AppData\Local\Google\Chrome\User Data\Default\Media Cache"

pushd "%USERPROFILE%\AppData\Local\Google\Chrome\User Data\Default\Cache"

del /f /s /q %systemdrive%\*.tmp

del /f /s /q %systemdrive%\*._mp

del /f /s /q %systemdrive%\*.log

del /f /s /q %systemdrive%\*.gid

del /f /s /q %systemdrive%\*.chk

del /f /s /q %systemdrive%\*.old

del /f /s /q %systemdrive%\recycled\*.*

del /f /s /q %windir%\*.bak

del /f /s /q %windir%\prefetch\*.*

echo 系统的垃圾文件全部删除，隐藏的无用的日志和文件也全部清空，电脑清洁一身！

echo 黑客基础技能，想学习加QQ 1320259466 ！

echo. & pause
