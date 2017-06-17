FOR /F "tokens=5 delims= " %%P IN ('netstat -a -n -o ^| findstr 0:8090') DO TaskKill.exe /PID %%P /f
exit 0