#
# CDDL HEADER START
#
# The contents of this file are subject to the terms of the
# Common Development and Distribution License (the "License").
# You may not use this file except in compliance with the License.
#
# You can obtain a copy of the license at usr/src/OPENSOLARIS.LICENSE
# or http://www.opensolaris.org/os/licensing.
# See the License for the specific language governing permissions
# and limitations under the License.
#
# When distributing Covered Code, include this CDDL HEADER in each
# file and include the License file at usr/src/OPENSOLARIS.LICENSE.
# If applicable, add the following below this CDDL HEADER, with the
# fields enclosed by brackets "[]" replaced with your own identifying
# information: Portions Copyright [yyyy] [name of copyright owner]
#
# CDDL HEADER END
#

#
# Copyright (c) 2015, 2020, Oracle and/or its affiliates. All rights reserved.
#

BUILD_BITS =            32_and_64
# All components using gobject-introspection must be built with gcc as
# gobject-introspection does not produce correct results when using Studio.
COMPILER =              gcc
include ../../../../make-rules/shared-macros.mk

COMPONENT_NAME=		gst-plugins-good
COMPONENT_VERSION=	1.16.2
COMPONENT_PROJECT_URL=	https://gstreamer.freedesktop.org/
COMPONENT_ARCHIVE=	$(COMPONENT_SRC).tar.xz
COMPONENT_ARCHIVE_HASH= \
	sha256:40bb3bafda25c0b739c8fc36e48380fccf61c4d3f83747e97ac3f9b0171b1319
COMPONENT_ARCHIVE_URL=	https://gstreamer.freedesktop.org/src/$(COMPONENT_NAME)/$(COMPONENT_ARCHIVE)
COMPONENT_SIG_URL=	$(COMPONENT_ARCHIVE_URL).asc
COMPONENT_BUGDB=	gnome/multimedia
COMPONENT_ANITYA_ID=	1263

TPNO=			55665

PYTHON_VERSION = 3.7

include $(WS_MAKE_RULES)/common.mk

# Use the build area of gstreamer and gst-plugins-base
GST_BASE = $(WS_COMPONENTS)/desktop/gstreamer1/gstreamer/build/prototype/$(MACH)
GST_PL_BASE = $(WS_COMPONENTS)/desktop/gstreamer1/gst-plugins-base/build/prototype/$(MACH)
PKG_CONFIG_PATHS += $(GST_BASE)$(USRLIB)/pkgconfig:$(GST_PL_BASE)$(USRLIB)/pkgconfig
 
CPPFLAGS += -I$(GST_BASE)/usr/include/gstreamer-1.0 -I$(GST_PL_BASE)/usr/include/gstreamer-1.0/
LD_OPTIONS += -L $(GST_BASE)$(USRLIB) -L $(GST_PL_BASE)$(USRLIB)

CXXFLAGS += -std=c++11

# configure script uses AC_CHECK_PROG erroneously; AC_CHECK_PROG looks for a
# program in $PATH by default, and fails even if you provide an absolute path
# to the command (no idea why); override the failed check.
CONFIGURE_ENV += ac_cv_prog_HAVE_CXX=yes

# No need for firewire support.
CONFIGURE_OPTIONS += --disable-dv1394
# Linux only; requires ALSA.
CONFIGURE_OPTIONS += --disable-monoscope
CONFIGURE_OPTIONS += --disable-static
# sunaudio no longer builds; interfaces used in gstreamer are gone (mixer.h).
CONFIGURE_OPTIONS += --disable-sunaudio
CONFIGURE_OPTIONS += --enable-bz2
CONFIGURE_OPTIONS += --enable-cairo
CONFIGURE_OPTIONS += --enable-jpeg
CONFIGURE_OPTIONS += --enable-libpng
CONFIGURE_OPTIONS += --enable-oss
CONFIGURE_OPTIONS += --enable-oss4
CONFIGURE_OPTIONS += --enable-pulse
CONFIGURE_OPTIONS += --enable-soup
CONFIGURE_OPTIONS += --enable-speex
CONFIGURE_OPTIONS += --enable-taglib
CONFIGURE_OPTIONS += --enable-vpx
CONFIGURE_OPTIONS += --enable-x
CONFIGURE_OPTIONS += --enable-xshm
CONFIGURE_OPTIONS += --enable-zlib
# missing header to build it
CONFIGURE_OPTIONS += --disable-gst_v4l2
CONFIGURE_OPTIONS += --with-x

# Makefile.am files of this component are not compatible with Solaris make.
# Use GNU make to avoid the configuration error when makefile fragments are processed.
CONFIGURE_OPTIONS += MAKE=$(GMAKE)

# Ensure system-default python is not used.
CONFIGURE_ENV += PYTHON="$(PYTHON.3.7)"

# Ensure Python modules are installed into vendor package directories.
CONFIGURE_ENV += am_cv_python_pythondir=$(PYTHON_VENDOR_PACKAGES)
CONFIGURE_ENV += am_cv_python_pyexecdir=$(PYTHON_VENDOR_PACKAGES)

# Required due to patches.
COMPONENT_PREP_ACTION += ( cd $(@D) ; NOCONFIGURE=1 $(CONFIG_SHELL) autogen.sh );

COMPONENT_TEST_MASTER = $(COMPONENT_TEST_RESULTS_DIR)/results-$(BITS)-$(MACH).master
COMPONENT_TEST_ARGS =   -k -i
COMPONENT_TEST_TRANSFORMS += \
        '-n ' \
        '-e "/TOTAL:/p" ' \
        '-e "/SKIP:/p" ' \
        '-e "/PASS:/p" ' \
        '-e "/FAIL:/p" ' \
        '-e "/ERROR:/p" '

REQUIRED_PACKAGES += codec/flac
REQUIRED_PACKAGES += codec/libogg
REQUIRED_PACKAGES += codec/libtheora
REQUIRED_PACKAGES += codec/libvorbis
REQUIRED_PACKAGES += codec/libvpx
REQUIRED_PACKAGES += codec/speex
REQUIRED_PACKAGES += image/library/libjpeg
REQUIRED_PACKAGES += image/library/libpng14
REQUIRED_PACKAGES += library/aalib
REQUIRED_PACKAGES += library/audio/pulseaudio
REQUIRED_PACKAGES += library/audio/taglib
REQUIRED_PACKAGES += library/desktop/cairo
REQUIRED_PACKAGES += library/desktop/gdk-pixbuf
REQUIRED_PACKAGES += library/desktop/gobject/gobject-introspection
REQUIRED_PACKAGES += library/desktop/gstreamer-1
REQUIRED_PACKAGES += library/desktop/gstreamer-1/gst-plugins-base
REQUIRED_PACKAGES += library/desktop/gtk3
REQUIRED_PACKAGES += library/desktop/libvisual
REQUIRED_PACKAGES += library/desktop/orc
REQUIRED_PACKAGES += library/desktop/pango
REQUIRED_PACKAGES += library/glib2
REQUIRED_PACKAGES += library/libsoup
REQUIRED_PACKAGES += library/ncurses
REQUIRED_PACKAGES += library/zlib
REQUIRED_PACKAGES += runtime/python-37
REQUIRED_PACKAGES += system/library/gcc/gcc-c++-runtime
REQUIRED_PACKAGES += system/library/gcc/gcc-c-runtime
REQUIRED_PACKAGES += system/library/math
REQUIRED_PACKAGES += x11/library/libx11
REQUIRED_PACKAGES += x11/library/libxdamage
REQUIRED_PACKAGES += x11/library/libxext
REQUIRED_PACKAGES += x11/library/libxfixes
REQUIRED_PACKAGES += x11/library/libxv
