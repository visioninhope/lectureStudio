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

package org.lecturestudio.swing.window;

import com.google.common.eventbus.Subscribe;

import org.lecturestudio.core.camera.bus.event.CameraImageEvent;
import org.lecturestudio.core.bus.ApplicationBus;
import org.lecturestudio.core.bus.event.ShutdownEvent;
import org.lecturestudio.core.app.ApplicationContext;
import org.lecturestudio.swing.components.CameraImagePanel;
import org.lecturestudio.swing.event.CameraWindowClosedEvent;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.UIManager.getSystemLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;

public class CameraDecoWindow extends DecoratedWindow {

	private CameraImagePanel cameraPanel;


	public CameraDecoWindow(ApplicationContext context) {
		super(context);

		init();
	}

	@Subscribe
	public void onEvent(final CameraImageEvent event) {
		invoke(() -> cameraPanel.paintImage(event.getImage()));
	}

	@Subscribe
	public void onEvent(final ShutdownEvent event) {
		invoke(this::shutdown);
	}

	public void close() {
		getWindow().dispatchEvent(new WindowEvent(getWindow(), WindowEvent.WINDOW_CLOSING));
	}

	private void init() {
		initListeners();
		initSystemLookAndFeel();
		initComponents();

		setTitle("Camera");
		setSize(640, 480);
		
		ApplicationBus.register(this);
	}

	private void initListeners() {
		getWindow().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		getWindow().addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				shutdown();
			}
		});
	}

	protected void initSystemLookAndFeel() {
		String systemClassName = getSystemLookAndFeelClassName();
		try {
			setLookAndFeel(systemClassName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void initComponents() {
		cameraPanel = new CameraImagePanel(getContext());
		cameraPanel.setPreferredSize(new Dimension(640, 480));

		getWindow().getContentPane().add(cameraPanel);
	}

	protected void shutdown() {
		ApplicationBus.unregister(this);
		ApplicationBus.post(new CameraWindowClosedEvent());
		
		cameraPanel.dispose();
		
		dispose();
	}

}
