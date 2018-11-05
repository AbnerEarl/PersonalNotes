net stop wuauserv 
net stop CryptSvc 
ren %windir%\system32\catroot2 catroot2.old 
ren %windir%\SoftwareDistribution sold.old 
net start CryptSvc 
net start wuauserv 
pause