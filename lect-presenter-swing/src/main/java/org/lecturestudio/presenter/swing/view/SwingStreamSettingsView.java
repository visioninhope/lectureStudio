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

package org.lecturestudio.presenter.swing.view;

import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lecturestudio.core.audio.AudioFormat;
import org.lecturestudio.core.beans.IntegerProperty;
import org.lecturestudio.core.beans.ObjectProperty;
import org.lecturestudio.core.beans.StringProperty;
import org.lecturestudio.core.converter.IntegerStringConverter;
import org.lecturestudio.core.view.Action;
import org.lecturestudio.presenter.api.view.StreamSettingsView;
import org.lecturestudio.swing.beans.ConvertibleObjectProperty;
import org.lecturestudio.swing.components.IPTextField;
import org.lecturestudio.swing.util.SwingUtils;
import org.lecturestudio.swing.view.SwingView;

@SwingView(name = "stream-settings", presenter = org.lecturestudio.presenter.api.presenter.StreamSettingsPresenter.class)
public class SwingStreamSettingsView extends JPanel implements StreamSettingsView {

	private JComboBox<String> streamAudioCodecCombo;

	private JComboBox<AudioFormat> streamAudioFormatCombo;

	private JTextField cameraBitrateTextField;

	private IPTextField broadcastAddressTextField;

	private JTextField broadcastPortTextField;

	private JTextField broadcastTlsPortTextField;

	private JButton closeButton;

	private JButton resetButton;


	SwingStreamSettingsView() {
		super();
	}

	@Override
	public void setStreamAudioFormat(ObjectProperty<AudioFormat> audioFormat) {
		SwingUtils.invoke(() -> {
			SwingUtils.bindBidirectional(streamAudioFormatCombo, audioFormat);
		});
	}

	@Override
	public void setStreamAudioFormats(List<AudioFormat> formats) {
		SwingUtils.invoke(() -> streamAudioFormatCombo
				.setModel(new DefaultComboBoxModel<>(new Vector<>(formats))));
	}

	@Override
	public void setStreamAudioCodecName(StringProperty audioCodecName) {
		SwingUtils.bindBidirectional(streamAudioCodecCombo, audioCodecName);
	}

	@Override
	public void setStreamAudioCodecNames(String[] codecNames) {
		SwingUtils.invoke(() -> streamAudioCodecCombo
				.setModel(new DefaultComboBoxModel<>(codecNames)));
	}

	@Override
	public void setStreamCameraBitrate(IntegerProperty bitrate) {
		SwingUtils.bindBidirectional(cameraBitrateTextField,
				new ConvertibleObjectProperty<>(bitrate,
						new IntegerStringConverter("#")));
	}

	@Override
	public void setBroadcastAddress(StringProperty address) {
		SwingUtils.bindBidirectional(broadcastAddressTextField, address);
	}

	@Override
	public void setBroadcastPort(IntegerProperty port) {
		SwingUtils.bindBidirectional(broadcastPortTextField,
				new ConvertibleObjectProperty<>(port,
						new IntegerStringConverter("#")));
	}

	@Override
	public void setBroadcastTlsPort(IntegerProperty port) {
		SwingUtils.bindBidirectional(broadcastTlsPortTextField,
				new ConvertibleObjectProperty<>(port,
						new IntegerStringConverter("#")));
	}

	@Override
	public void setOnClose(Action action) {
		SwingUtils.bindAction(closeButton, action);
	}

	@Override
	public void setOnReset(Action action) {
		SwingUtils.bindAction(resetButton, action);
	}
}
