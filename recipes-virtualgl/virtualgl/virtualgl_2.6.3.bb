DESCRIPTION = "VirtualGL : 3D without boundaries"
LICENSE = "CLOSED"

DEPENDS = "libjpeg-turbo libx11 libxcb xcb-util-keysyms libxext libxtst libglu libxv virtual/libgl"

SRCREV = "39358c55a7f6c5fa99e75419297cd7692382611b"
SRC_URI = "git://github.com/VirtualGL/virtualgl.git \
           file://0001-disable-opencl.patch \
           "
#SRC_URI[md5sum] = "deba9af32dc44f74cb6d2544fb3a9047"
#SRC_URI[sha256sum] = "8720d6906386d2000bc370cebd6208d84e31f3872bcc0cce57c8d6398210fbde"

S = "${WORKDIR}/git"

FILES_${PN} += "${exec_prefix}/bin/.vglrun.vars64 \
    ${libexecdir}/* \
    ${libdir}/lib*.so.* \
"

INSANE_SKIP_${PN}-dev += "dev-elf"

do_install_append() {
    mv ${D}${bindir}/glxinfo ${D}${bindir}/glxinfo-vgl
}

inherit pkgconfig cmake

BBCLASSEXTEND = "native nativesdk"
