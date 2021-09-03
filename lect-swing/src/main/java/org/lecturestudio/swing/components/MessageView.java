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

package org.lecturestudio.swing.components;

import static java.util.Objects.isNull;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.lecturestudio.swing.border.RoundedBorder;

public class MessageView extends JPanel {

	private JLabel fromLabel;

	private JLabel timeLabel;

	private JTextArea textArea;


	public MessageView() {
		super();

		initialize();
	}

	public void setDate(ZonedDateTime date) {
		if (isNull(date)) {
			return;
		}

		ZonedDateTime dateUTC = date.withZoneSameInstant(ZoneId.systemDefault());
		String formattedDate = dateUTC.format(DateTimeFormatter.ofPattern("H:mm"));

		timeLabel.setText(formattedDate);
	}

	public void setUserName(String host) {
		fromLabel.setText(host);
	}

	public void setMessage(String message) {
		textArea.setText(message);
	}

	public void pack() {
		setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height));
		setMaximumSize(new Dimension(getMaximumSize().width, getPreferredSize().height));
		setMinimumSize(new Dimension(200, getPreferredSize().height));
	}

	private void initialize() {
		setLayout(new BorderLayout(1, 1));
		setBackground(Color.WHITE);
		setBorder(new RoundedBorder(Color.LIGHT_GRAY, 5));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.weightx = 1.D;

		fromLabel = new JLabel();

		JPanel controlPanel = new JPanel(new GridBagLayout());
		controlPanel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
		controlPanel.setOpaque(false);
		controlPanel.add(fromLabel, constraints);

		constraints.anchor = GridBagConstraints.EAST;
		constraints.weightx = 1.D;
		constraints.gridx = 1;

		timeLabel = new JLabel();
		timeLabel.setForeground(Color.BLUE);

		controlPanel.add(timeLabel, constraints);

		textArea = new JTextArea();
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(textArea.getFont().deriveFont(12f));

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		add(controlPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}
}
