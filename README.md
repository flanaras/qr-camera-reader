# QR Camera reader

## Tested

This has been tested on:

* openSUSE tumbleweed (x64)

## Prerequisites

* JDK 8+
* The correct webcam driver selected for your platform. Some drivers don't work on all platforms, check [the library's documentation on drivers](https://github.com/sarxos/webcam-capture#capture-drivers)

## Camera modules

There are two camera modules implemented, one of them is the PCWebCamOperations and the other one is IPWebCamOperations.

### PCWebCamOperations

Gets the feed from a webcam attached to the computer.

### IPWebCamOperations

Gets the feed from an IP Camera. This is specified by giving the link which accesses the JPEG or MJPEG feed. Tested with public video feeds found online and also the feed provided by [IP Webcam](https://play.google.com/store/apps/details?id=com.pas.webcam&hl=en).

[More on github.](https://github.com/sarxos/webcam-capture/tree/master/webcam-capture-drivers/driver-ipcam)

## How to run

On Linux, open a terminal and run:

```
./gradlew myRun
```

This will compile and run this app.

To run this on windows, you should open a cmd and type:

```
gradlew myRun
```

Double check on the driver.
