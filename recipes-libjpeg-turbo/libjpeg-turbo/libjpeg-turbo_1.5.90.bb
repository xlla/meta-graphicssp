SUMMARY = "Hardware accelerated JPEG compression/decompression library"
DESCRIPTION = "libjpeg-turbo is a derivative of libjpeg that uses SIMD instructions (MMX, SSE2, NEON) to accelerate baseline JPEG compression and decompression"
HOMEPAGE = "http://libjpeg-turbo.org/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://cdjpeg.h;endline=13;md5=8184bcc7c4ac7b9edc6a7bc00f231d0b \
                    file://jpeglib.h;endline=16;md5=7ea97dc83b0f59052ee837e61ef0e08f \
                    file://djpeg.c;endline=11;md5=c59e19811c006cb38f82d6477134d314 \
"
DEPENDS_append_x86-64_class-target = " nasm-native"
DEPENDS_append_x86_class-target    = " nasm-native"

# DEPENDS = "openjdk-8-native "

SRC_URI = "git://github.com/libjpeg-turbo/libjpeg-turbo.git;protocol=git;tag=${PV} \
           file://0001-fix-package_qa-error.patch \
           "

#SRC_URI[md5sum] = "097b6dd4931b322ba74186deb0fb6613"
#SRC_URI[sha256sum] = "21f3936fd776723e2e7e0eec14d96717446da3bc961ffd319a17b9d19a24d85a"
#UPSTREAM_CHECK_URI = "http://sourceforge.net/projects/libjpeg-turbo/files/"
#UPSTREAM_CHECK_REGEX = "/libjpeg-turbo/files/(?P<pver>(\d+[\.\-_]*)+)/"

PE= "1"
S = "${WORKDIR}/git"
PACKAGE_ARCH = "aarch64"
# Drop-in replacement for jpeg
# PROVIDES = "jpeg"
# RPROVIDES_${PN} += "jpeg"
# RREPLACES_${PN} += "jpeg"
# RCONFLICTS_${PN} += "jpeg"

export JAVA_HOME="${STAGING_LIBDIR_NATIVE}/jvm/openjdk-8-native"

inherit pkgconfig cmake 

# Add nasm-native dependency consistently for all build arches is hard
# EXTRA_OECONF_append_class-native = " --without-simd"

# Work around missing x32 ABI support
# EXTRA_OECONF_append_class-target = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", "--without-simd", "", d)}"

# Work around missing non-floating point ABI support in MIPS
# EXTRA_OECONF_append_class-target = " ${@bb.utils.contains("MIPSPKGSFX_FPU", "-nf", "--without-simd", "", d)}"

# Provide a workaround if Altivec unit is not present in PPC
# EXTRA_OECONF_append_class-target_powerpc = " ${@bb.utils.contains("TUNE_FEATURES", "altivec", "", "--without-simd", d)}"
# EXTRA_OECONF_append_class-target_powerpc64 = " ${@bb.utils.contains("TUNE_FEATURES", "altivec", "", "--without-simd", d)}"

# EXTRA_OECMAKE += " -DWITH_JAVA=1"


# Add nasm-native dependency consistently for all build arches is hard
EXTRA_OECMAKE_append_class-native = " -DWITH_SIMD=False"
EXTRA_OECMAKE_append_class-nativesdk = " -DWITH_SIMD=False"

# Work around missing x32 ABI support
EXTRA_OECMAKE_append_class-target = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", "-DWITH_SIMD=False", "", d)}"

# Work around missing non-floating point ABI support in MIPS
EXTRA_OECMAKE_append_class-target = " ${@bb.utils.contains("MIPSPKGSFX_FPU", "-nf", "-DWITH_SIMD=False", "", d)}"

# Provide a workaround if Altivec unit is not present in PPC
EXTRA_OECMAKE_append_class-target_powerpc = " ${@bb.utils.contains("TUNE_FEATURES", "altivec", "", "-DWITH_SIMD=False", d)}"
EXTRA_OECMAKE_append_class-target_powerpc64 = " ${@bb.utils.contains("TUNE_FEATURES", "altivec", "", "-DWITH_SIMD=False", d)}"

DEBUG_OPTIMIZATION_append_armv4 = " ${@bb.utils.contains('TUNE_CCARGS', '-mthumb', '-fomit-frame-pointer', '', d)}"
DEBUG_OPTIMIZATION_append_armv5 = " ${@bb.utils.contains('TUNE_CCARGS', '-mthumb', '-fomit-frame-pointer', '', d)}"


# CFLAGS_append = " -fPIC "

def get_build_time(d):
    if d.getVar('SOURCE_DATE_EPOCH') != None:
        import datetime
        return " --with-build-date="+ datetime.datetime.fromtimestamp(float(d.getVar('SOURCE_DATE_EPOCH'))).strftime("%Y%m%d")
    return ""

EXTRA_OECONF_append_class-target = "${@get_build_time(d)}"

# PACKAGES += " jpeg-tools jpeg-tools-dev "
# PACKAGES += " libturbojpeg libturbojpeg-dev "

PACKAGES =+ "jpeg-tools libturbojpeg"

DESCRIPTION_jpeg-tools = "The jpeg-tools package includes client programs to access libjpeg functionality.  These tools allow for the compression, decompression, transformation and display of JPEG files and benchmarking of the libjpeg library."
FILES_jpeg-tools = "${bindir}/*"
# INSANE_SKIP_jpeg-tools = "dev-so"

DESCRIPTION_libturbojpeg = "A SIMD-accelerated JPEG codec which provides only TurboJPEG APIs"
FILES_libturbojpeg = "${libdir}/libturbojpeg.so.*"


# DESCRIPTION_jpeg-tools = "The jpeg-tools package includes client programs to access libjpeg functionality.  These tools allow for the compression, decompression, transformation and display of JPEG files and benchmarking of the libjpeg library."
# FILES_jpeg-tools = "${bindir}/*"
# INSANE_SKIP_jpeg-tools = "useless-rpaths"

# DESCRIPTION_libturbojpeg = "A SIMD-accelerated JPEG codec which provides only TurboJPEG APIs"
# FILES_libturbojpeg = "${libdir}/libturbojpeg.so.* ${libdir}/libjpeg.so.*"
# INSANE_SKIP_libturbojpeg = "useless-rpaths"

# FILES_${PN}="${libdir}/lib*jpeg.so.* ${libdir}/pkgconfig ${includedir}" 
# FILES_${PN}-dev="${libdir}/lib*.so  ${libdir}/libjpeg.so.62.3.0  ${libdir}/libturbojpeg.so.0.2.0 ${bindir}/*" 
# RDEPENDS_${PN}-staticdev = ""
# FILES_libjpeg-turbo-dev = "${libdir}/libjpeg.so.* \ 
#               ${libdir}/libturbojpeg.so.* \
#             " 
# RDEPENDS_${PN}-dbg = ""
# INSANE_SKIP_${PN} += "useless-rpaths"


BBCLASSEXTEND = "native nativesdk"
