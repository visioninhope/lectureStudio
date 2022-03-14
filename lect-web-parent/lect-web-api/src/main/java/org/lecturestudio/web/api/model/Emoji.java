/*
 * Copyright (C) 2022 TU Darmstadt, Department of Computer Science,
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

package org.lecturestudio.web.api.model;

import java.io.Serializable;
import java.util.Objects;


public class Emoji extends ServiceModel implements Comparable<Emoji>, Cloneable, Serializable {

	private String emoji;


	public Emoji() {
		this(null);
	}

	public Emoji(String emoji) {
		this.setEmoji(emoji);
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}


	@Override
	public int compareTo(Emoji other) {
		return emoji.compareToIgnoreCase(other.emoji);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Emoji other = (Emoji) o;

		return getServiceId() == other.getServiceId() && Objects.equals(emoji, other.emoji);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getServiceId(), emoji);
	}

	@Override
	public Emoji clone() {
		return new Emoji(emoji);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + emoji;
	}

}
