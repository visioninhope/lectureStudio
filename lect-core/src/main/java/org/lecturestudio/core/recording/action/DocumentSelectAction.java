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

package org.lecturestudio.core.recording.action;

import java.io.IOException;

import org.lecturestudio.core.bus.ApplicationBus;
import org.lecturestudio.core.bus.event.StreamDocumentEvent;
import org.lecturestudio.core.bus.event.StreamDocumentSelectEvent;
import org.lecturestudio.core.controller.ToolController;
import org.lecturestudio.core.model.DocumentType;

public class DocumentSelectAction extends DocumentAction {

	public DocumentSelectAction(DocumentType type, String documentTitle, String documentFileName) {
		super(type, documentTitle, documentFileName);
	}

	public DocumentSelectAction(byte[] data) throws IOException {
		super(data);
	}

	@Override
	public void execute(ToolController controller) throws Exception {
		DocumentType docType = getDocumentType();
		String docTitle = getDocumentTitle();

		StreamDocumentEvent event = new StreamDocumentSelectEvent(docType, docTitle);
		event.setDocumentFileName(getDocumentFileName());
		event.setDocumentChecksum(getDocumentChecksum());

		ApplicationBus.post(event);
	}

	@Override
	public ActionType getType() {
		return ActionType.DOCUMENT_SELECTED;
	}

}
