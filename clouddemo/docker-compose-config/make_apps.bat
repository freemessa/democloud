@echo off
@REM 进入批处理指令所在的目录
cd /d %~dp0

set srcPath=.\project\services
set appPath=.\deploy\apps

@REM 创建deploy部署目录
if not exist %appPath% md %appPath%

@REM 拷贝指定模块
set modules=gateway auth common integration minio process process-data system
for %%s in (%modules%) do (
  if exist %srcPath%\sky-%%s (
    XCOPY %srcPath%\sky-%%s\target\sky-%%s-0.0.1-SNAPSHOT.jar %appPath%\sky-%%s\target\ /S /Y
    @REM XCOPY %srcPath%\sky-%%s\Dockerfile %appPath%\sky-%%s\ /S /Y
  )
)

@REM 拷贝所有sky-*模块
@REM for /d %%i in (%srcPath%\sky-*) do (
@REM   XCOPY %%i\target\%%~nxi-0.0.1-SNAPSHOT.jar %appPath%\%%~nxi\target\ /S /Y
@REM   XCOPY %%i\Dockerfile %appPath%\%%~nxi\ /S /Y
@REM )

pause