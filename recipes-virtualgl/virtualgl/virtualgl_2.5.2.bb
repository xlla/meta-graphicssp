DESCRIPTION = "VirtualGL : 3D without boundaries"
LICENSE = "CLOSED"

DEPENDS = "libjpeg-turbo libx11 libxext libxtst libglu libxv virtual/libgl"

SRC_URI = "https://sourceforge.net/projects/virtualgl/files/2.5.2/VirtualGL-2.5.2.tar.gz/download"
SRC_URI[md5sum] = "1a9f404f4a35afa9f56381cb33ed210c"
SRC_URI[sha256sum] = "4f43387678b289a24139c5b7c3699740ca555a9f10011c979e51aa4df2b93238"

S = "${WORKDIR}/VirtualGL-${PV}"

inherit autotools cmake

