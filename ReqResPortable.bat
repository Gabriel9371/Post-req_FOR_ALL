@echo off
where javaw >nul 2>nul
if errorlevel 1 (
    echo Java nao encontrado. Instale o Java 17 ou superior.
    pause
    exit /b 1
)
start "" javaw -jar "%~dp0ReqResPortable.jar"