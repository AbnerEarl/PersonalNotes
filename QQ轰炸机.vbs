Set WshShell=WScript.CreateObject ("Wscript.Shell")
WshShell.AppActivate"我会一直在 、"
for i=1 to 100
WScript.sleep 300
WshShell.Sendkeys"^v"
WshShell.Sendkeys i
WshShell.Sendkeys"%s"
Next