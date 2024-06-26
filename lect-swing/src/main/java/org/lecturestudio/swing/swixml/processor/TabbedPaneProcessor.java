/*
 * Copyright (C) 2020 TU Darmstadt, Department of Computer Science,
 * Embedded Systems and Applications Group.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.lecturestudio.swing.swixml.processor;

import java.awt.LayoutManager;

import javax.swing.*;

import org.lecturestudio.swing.components.SettingsTab;
import org.lecturestudio.swing.util.TabLabelTransformer;

import org.swixml.LogAware;
import org.swixml.Parser;
import org.swixml.processor.TagProcessor;

import org.w3c.dom.Element;

public class TabbedPaneProcessor implements TagProcessor, LogAware {

	@Override
	public boolean process(Parser parser, Object parent, Element child,
						   LayoutManager layoutMgr) throws Exception {
		if (!"Tab".equalsIgnoreCase(child.getLocalName())) {
			return false;
		}
		if (!(parent instanceof JTabbedPane)) {
			logger.warning("Tab tag is valid only inside JTabbedPane tag. Ignored!");
			return false;
		}

		final JTabbedPane tabbedPane = (JTabbedPane) parent;
		final SettingsTab tab = (SettingsTab) parser.getSwing(child, null);
		final String paneName = tabbedPane.getName();
		final int tabPlacement = tabbedPane.getTabPlacement();
		final JLabel tabLabel = TabLabelTransformer.transformTabLabel(tab,
				tabPlacement, paneName);

		tabbedPane.addTab(null, tab.getContent());
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tabLabel);

		return true;
	}

}
