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
# Copyright (c) 2015, 2019, Oracle and/or its affiliates. All rights reserved.
#

BUILD_BITS =            64_and_32
# All components using gobject-introspection must be built with gcc as
# gobject-introspection does not produce correct results when using Studio.
COMPILER =              gcc
include ../../../make-rules/shared-macros.mk

COMPONENT_NAME=		gtksourceview
COMPONENT_VERSION=	3.24.10
COMPONENT_PROJECT_URL=	https://developer.gnome.org/gtkmm/stable/
COMPONENT_ARCHIVE_HASH= \
    sha256:ab5406cdd0bdcf66afcd52b1e048a2f43c0f3ab391644ee30d9ac419b93cd59b
COMPONENT_BUGDB=	gnome/libraries
COMPONENT_ANITYA_ID=	7724

TPNO=			54077

include $(WS_MAKE_RULES)/gnome-component.mk

# Required for other components.
CONFIGURE_OPTIONS += --enable-introspection
# Required for other components.
CONFIGURE_OPTIONS += --enable-vala

# Uses gcc extensions for zero-sized struct/union.
CFLAGS.studio += $(studio_FEATURES_EXTENSIONS)

# Assumes GNU xgettext in PATH.
CONFIGURE_ENV += PATH=$(GNUBIN):$(PATH)

COMPONENT_POST_INSTALL_ACTION.64 += \
        $(INSTALL) -D -m 0444 $(COMPONENT_DIR)/files/libgtksourceview-3.0.3lib \
                $(PROTO_DIR)/usr/share/man/man3lib/libgtksourceview-3.0.3lib;

# for the tests
unexport SHELLOPTS
COMPONENT_TEST_MASTER = $(COMPONENT_TEST_RESULTS_DIR)/results-all.master
COMPONENT_TEST_ARGS =   -k -i
COMPONENT_TEST_TRANSFORMS += \
        '-n ' \
        '-e "/TOTAL:/p" ' \
        '-e "/SKIP:/p" ' \
        '-e "/PASS:/p" ' \
        '-e "/FAIL:/p" ' \
        '-e "/ERROR:/p" '

REQUIRED_PACKAGES += developer/documentation-tool/gtk-doc
REQUIRED_PACKAGES += developer/vala
REQUIRED_PACKAGES += library/desktop/atk
REQUIRED_PACKAGES += library/desktop/cairo
REQUIRED_PACKAGES += library/desktop/gobject/gobject-introspection
REQUIRED_PACKAGES += library/desktop/gdk-pixbuf
REQUIRED_PACKAGES += library/desktop/gtk3
REQUIRED_PACKAGES += library/desktop/pango
REQUIRED_PACKAGES += library/glib2
REQUIRED_PACKAGES += library/libxml2
REQUIRED_PACKAGES += system/library/gcc/gcc-c-runtime
REQUIRED_PACKAGES += system/library/math

