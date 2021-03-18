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

package org.lecturestudio.javafx.beans.converter;

import static java.util.Objects.nonNull;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import org.lecturestudio.core.beans.Converter;

public class FontConverter implements Converter<org.lecturestudio.core.text.Font, Font> {

	public static final FontConverter INSTANCE = new FontConverter();


	@Override
	public Font to(org.lecturestudio.core.text.Font value) {
		FontPosture posture = fromLectFontPosture(value.getPosture());
		FontWeight weight = fromLectFontWeight(value.getWeight());

		return Font.font(value.getFamilyName(), weight, posture, value.getSize());
	}

	@Override
	public org.lecturestudio.core.text.Font from(Font value) {
		String style = value.getStyle();
		String[] styles = style.split(" ");

		org.lecturestudio.core.text.Font font = new org.lecturestudio.core.text.Font(value.getFamily(), value.getSize());
		font.setPosture(postureFromStyle(styles));
		font.setWeight(weightFromStyle(styles));

		return font;
	}

	private static org.lecturestudio.core.text.FontPosture postureFromStyle(String[] styles) {
		for (String style : styles) {
			FontPosture posture = FontPosture.findByName(style);

			if (nonNull(posture)) {
				return toLectFontPosture(posture);
			}
		}

		return org.lecturestudio.core.text.FontPosture.REGULAR;
	}

	private static org.lecturestudio.core.text.FontWeight weightFromStyle(String[] styles) {
		for (String style : styles) {
			FontWeight weight = FontWeight.findByName(style);

			if (nonNull(weight)) {
				return toLectFontWeight(weight);
			}
		}

		return org.lecturestudio.core.text.FontWeight.NORMAL;
	}

	private static FontPosture fromLectFontPosture(org.lecturestudio.core.text.FontPosture posture) {
		switch (posture) {
			case REGULAR:
				return FontPosture.REGULAR;

			case ITALIC:
				return FontPosture.ITALIC;

			default:
				return FontPosture.REGULAR;
		}
	}

	private static org.lecturestudio.core.text.FontPosture toLectFontPosture(FontPosture posture) {
		switch (posture) {
			case REGULAR:
				return org.lecturestudio.core.text.FontPosture.REGULAR;

			case ITALIC:
				return org.lecturestudio.core.text.FontPosture.ITALIC;

			default:
				return org.lecturestudio.core.text.FontPosture.REGULAR;
		}
	}

	private static FontWeight fromLectFontWeight(org.lecturestudio.core.text.FontWeight weight) {
		switch (weight) {
			case THIN:
				return FontWeight.THIN;

			case EXTRA_LIGHT:
				return FontWeight.EXTRA_LIGHT;

			case LIGHT:
				return FontWeight.LIGHT;

			case NORMAL:
				return FontWeight.NORMAL;

			case MEDIUM:
				return FontWeight.MEDIUM;

			case SEMI_BOLD:
				return FontWeight.SEMI_BOLD;

			case BOLD:
				return FontWeight.BOLD;

			case EXTRA_BOLD:
				return FontWeight.EXTRA_BOLD;

			case BLACK:
				return FontWeight.BLACK;

			default:
				return FontWeight.NORMAL;
		}
	}

	private static org.lecturestudio.core.text.FontWeight toLectFontWeight(FontWeight weight) {
		switch (weight) {
			case THIN:
				return org.lecturestudio.core.text.FontWeight.THIN;

			case EXTRA_LIGHT:
				return org.lecturestudio.core.text.FontWeight.EXTRA_LIGHT;

			case LIGHT:
				return org.lecturestudio.core.text.FontWeight.LIGHT;

			case NORMAL:
				return org.lecturestudio.core.text.FontWeight.NORMAL;

			case MEDIUM:
				return org.lecturestudio.core.text.FontWeight.MEDIUM;

			case SEMI_BOLD:
				return org.lecturestudio.core.text.FontWeight.SEMI_BOLD;

			case BOLD:
				return org.lecturestudio.core.text.FontWeight.BOLD;

			case EXTRA_BOLD:
				return org.lecturestudio.core.text.FontWeight.EXTRA_BOLD;

			case BLACK:
				return org.lecturestudio.core.text.FontWeight.BLACK;

			default:
				return org.lecturestudio.core.text.FontWeight.NORMAL;
		}
	}

}
