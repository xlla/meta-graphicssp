# meta-graphicssp
Contains recipes for virtualgl and turbovnc

First add ```meta-java``` layer with priority 6 and branch ```dora``` (since above meta-layer is built for ```fido```).

Then add ```meta-graphicssp``` layer with priority 7.

Build the recipes in the following order -
* freeglut
* giflib
* libxtst
* libxv
* krb5
* libjpeg-turbo
* virtualgl
* turbovnc (in progress)

Add the following lines in local.conf file.
```
DISTRO_FEATURES_append += " pam"
DISTRO_FEATURES_append += " opengl"
PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"
PREFERRED_PROVIDER_jpeg-native = "libjpeg-turbo-native"
````
Do not forget to edit the bblayers.conf file and do not touch the layer.conf file (unless you want to set higher priority to ```meta-graphicssp``` than already provided).