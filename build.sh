#!/bin/sh
ant clean
ant build
ant install
adb shell uiautomator runtest alchemyhour.jar -c com.leandog.driver.Driver
