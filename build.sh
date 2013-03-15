#!/bin/sh
ant clean
ant build
ant install
adb forward tcp:9090 tcp:54767
adb shell uiautomator runtest pi.jar -c com.leandog.driver.Driver
