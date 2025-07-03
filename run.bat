@echo off
echo Compilando y ejecutando Sistema de Fidelidad Gamificada...
echo.

REM Compilar el proyecto
call gradlew build -q

REM Ejecutar la aplicación directamente con Java
java -cp "build/classes/java/main;build/resources/main" cl.usm.fidelidad.Main

pause 