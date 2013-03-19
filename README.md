Pi - making Android's uiautomator a bit more tasty
===========
------------------
[Did someone say Pie? No Steve, not pie... Pi!](http://www.youtube.com/watch?v=Mfr7xG6smhU)

Pi is simple tool that (hopefully) makes testing Android well... easy as pie.

Starting Pi
------------------
For starters, be sure you've set ANDROID_HOME to point to your local Android SDK and ensure that [adb](http://developer.android.com/tools/help/adb.html) is within your path.
As an example, my .bash_profile has the following lines in it:  
`export ANDROID_HOME=/Users/daveshah/Development/android-sdks`  
`PATH=$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools`  

Start up you're local emulator or device. 
execute the build.sh script

That's it. You should now be able to hit localhost:9090 to interact with Pi!


Notes for Hacking on Pi
-------------------
If you're importing the project to eclipse - 
Add the following Extenral jars:  
* android.jar  
* uiautomator.jar
(These can be found in $ANDROID_HOME/platforms/android-17/)_

You'll also want to add BOTH the Junit3 & Junit4 libaries - the unit tests under 'test' use JUnit4
the main driver depends on JUnit3 as well as all jars within the /libs directory

Thanks
-------------------
special thanks to [nanohttp](https://github.com/elonen/nanohttpd) - NanoHTTPD works like a champ so far! 
