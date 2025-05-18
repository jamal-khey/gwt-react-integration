
@echo off
REM Build React app
cd src\main\webapp\react-app
call npm run build

REM Copy React build output to GWT war directory
xcopy /E /Y dist ..\..\..\..\war\react-app\dist
