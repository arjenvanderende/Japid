@echo off
echo ~
echo ~ this command is for use in none-Play! environment.
echo ~ use "play japid:%1" for Play applications.
echo ~
java -classpath "%~dp0../lib/*" cn.bran.japid.compiler.JapidRender %*
