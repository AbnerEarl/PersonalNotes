Set WshShell=WScript.CreateObject ("Wscript.Shell")
WshShell.AppActivate"�һ�һֱ�� ��"
for i=1 to 100
WScript.sleep 300
WshShell.Sendkeys"^v"
WshShell.Sendkeys i
WshShell.Sendkeys"%s"
Next