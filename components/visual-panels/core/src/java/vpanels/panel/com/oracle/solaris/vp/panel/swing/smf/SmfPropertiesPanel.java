/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * You can obtain a copy of the license at usr/src/OPENSOLARIS.LICENSE
 * or http://www.opensolaris.org/os/licensing.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at usr/src/OPENSOLARIS.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright (c) 2010, 2012, Oracle and/or its affiliates. All rights reserved.
 */

package com.oracle.solaris.vp.panel.swing.smf;

import java.awt.EventQueue;
import com.oracle.solaris.vp.panel.common.action.StructuredAction;
import com.oracle.solaris.vp.panel.common.model.*;
import com.oracle.solaris.vp.panel.common.smf.*;
import com.oracle.solaris.vp.panel.swing.model.ManagedObjectPropInfo;
import com.oracle.solaris.vp.panel.swing.view.TreeTableObjectsPanel;
import com.oracle.solaris.vp.util.common.propinfo.PropInfo;
import com.oracle.solaris.vp.util.misc.finder.Finder;

@SuppressWarnings({"serial"})
public class SmfPropertiesPanel extends TreeTableObjectsPanel {
    //
    // Static data
    //

    private static final ManagedObject empty = new EmptyManagedObject();

    //
    // Instance data
    //

    @SuppressWarnings({"unchecked"})
    private static final PropInfo<ManagedObject, ?>[] props =
	new PropInfo[] {
	    new ManagedObjectPropInfo(),
	    new PropertyValuePropInfo(),
	};

    //
    // Constructors
    //

    public SmfPropertiesPanel() {
        super(empty, props, null, null,
	    Finder.getString("service.properties.name"), null);

	setHeaderVisible(false);
	setShowContentBorder(false);
    }

    //
    // SmfPropertiesPanel methods
    //

    @SuppressWarnings({"unchecked"})
    public void init(SmfManagedObject object) {
	// Sanity check -- the UI should be updated only on the event thread
	assert EventQueue.isDispatchThread();

	FilterManagedObject filtered = getFilterManagedObject();
	filtered.setManagedObject(
	    object == null ? empty : object.getPropertyGroups());
    }
}
