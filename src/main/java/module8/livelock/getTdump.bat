set /A COUNTER=1
:top
jstack %1 > D:\projects\IntelliJIdea\jmp\jstack\file%COUNTER%.log
timeout /t 3
set /A COUNTER=%COUNTER%+1
goto top