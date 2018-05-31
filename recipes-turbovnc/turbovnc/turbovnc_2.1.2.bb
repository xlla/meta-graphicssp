DESCRIPTION = "TurboVNC is high speed 3D friendly tightVNC compatible remote desktop software"
LICENSE = "CLOSED"

DEPENDS = "libjpeg-turbo libx11 libpam openjdk-7"

SRC_URI = "https://sourceforge.net/projects/turbovnc/files/2.1.2/turbovnc-2.1.2.tar.gz/"

S = "${WORKDIR}/turbovnc-${PV}"

inherit autotools
