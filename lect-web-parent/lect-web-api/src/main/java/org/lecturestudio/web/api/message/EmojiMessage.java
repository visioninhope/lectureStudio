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

package org.lecturestudio.web.api.message;

import java.time.ZonedDateTime;
import java.util.Objects;
import org.lecturestudio.web.api.model.Emoji;



public class EmojiMessage extends WebMessage {

	private Emoji emoji;
	
	public EmojiMessage() {
		this(null, null, null);
	}

	public EmojiMessage(Emoji emoji, String remoteAddress, ZonedDateTime date) {
		setEmoji(emoji);
		setRemoteAddress(remoteAddress);
		setDate(date);
	}

	public Emoji getEmoji() {
		return emoji;
	}

	public void setEmoji(Emoji emoji) {
		this.emoji = emoji;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		EmojiMessage other = (EmojiMessage) o;

		return Objects.equals(emoji, other.emoji);
	}

	@Override
	public int hashCode() {
		return Objects.hash(emoji);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getClass().getSimpleName());
		buffer.append(": ");
		buffer.append(getEmoji());
		buffer.append(", ");
		buffer.append(getDate());
		buffer.append(", ");
		buffer.append("RemoteAddress: ");
		buffer.append(getRemoteAddress());

		return buffer.toString();
	}

}
