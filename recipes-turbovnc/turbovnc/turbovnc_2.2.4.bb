DESCRIPTION = "TurboVNC is high speed 3D friendly tightVNC compatible remote desktop software"
LICENSE = "CLOSED"

DEPENDS = "libjpeg-turbo libx11 libxext libpam openjdk-8-native glib-2.0 glib-2.0-native"
FILESEXTRAPATHS_prepend := "${THISDIR}:"
#SRC_URI = "https://sourceforge.net/projects/turbovnc/files/2.2.4/turbovnc-2.2.4.tar.gz/"
SRC_URI = "file://turbovnc-2.2.4.tar.gz"

S = "${WORKDIR}/turbovnc-${PV}"
PACKAGE_ARCH = "aarch64"

#export JAVA_HOME="${STAGING_DIR_NATIVE}/usr/bin/java"
export JAVA_HOME="${STAGING_LIBDIR_NATIVE}/jvm/openjdk-8-native"
# export JAVA_HOME="${STAGING_DIR_NATIVE}/usr/lib/jvm/openjdk-8-native"

EXTRA_OECMAKE += " -DTVNC_BUILDJAVA=Off -DTVNC_INCLUDEJNIJARS=Off"
# EXTRA_OECMAKE += " -DTVNC_BUILDNATIVE=Off"
RDEPENDS_${PN}-utils = "perl"
INSANE_SKIP_${PN}_append = "file-rdeps"

do_install_append () {
    touch ${D}/../build/${PN}-${PV}.jar
}

inherit pkgconfig cmake java-library

BBCLASSEXTEND = "native"

# BBCLASSEXTEND = "native nativesdk"
