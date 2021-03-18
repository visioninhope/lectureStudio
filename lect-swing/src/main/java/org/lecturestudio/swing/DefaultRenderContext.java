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

package org.lecturestudio.swing;

import org.lecturestudio.core.render.RenderContext;
import org.lecturestudio.core.render.RenderService;
import org.lecturestudio.core.view.ViewType;
import org.lecturestudio.swing.renderer.ArrowRenderer;
import org.lecturestudio.swing.renderer.EllipseRenderer;
import org.lecturestudio.swing.renderer.GridRenderer;
import org.lecturestudio.swing.renderer.LineRenderer;
import org.lecturestudio.swing.renderer.PointerRenderer;
import org.lecturestudio.swing.renderer.RectangleRenderer;
import org.lecturestudio.swing.renderer.SelectRenderer;
import org.lecturestudio.swing.renderer.StrokeRenderer;
import org.lecturestudio.swing.renderer.TeXRenderer;
import org.lecturestudio.swing.renderer.TextRenderer;
import org.lecturestudio.swing.renderer.TextSelectionRenderer;
import org.lecturestudio.swing.renderer.ZoomRenderer;

public class DefaultRenderContext extends RenderContext {

	public DefaultRenderContext() {
		setRenderer(ViewType.Preview, createPreviewRenderContext());
		setRenderer(ViewType.User, createUserRenderContext());
		setRenderer(ViewType.Presentation, createPresentationRenderContext());
	}
	
	private RenderService createPreviewRenderContext() {
		RenderService service = new RenderService();
		service.registerRenderer(new StrokeRenderer());
		service.registerRenderer(new TeXRenderer());
		service.registerRenderer(new TextRenderer());
		service.registerRenderer(new TextSelectionRenderer());
		service.registerRenderer(new ZoomRenderer());
		service.registerRenderer(new ArrowRenderer());
		service.registerRenderer(new LineRenderer());
		service.registerRenderer(new RectangleRenderer());
		service.registerRenderer(new EllipseRenderer());
		service.registerRenderer(new SelectRenderer());

		return service;
	}

	private RenderService createUserRenderContext() {
		RenderService service = new RenderService();
		service.registerRenderer(new StrokeRenderer());
		service.registerRenderer(new PointerRenderer());
		service.registerRenderer(new TeXRenderer());
		service.registerRenderer(new TextRenderer());
		service.registerRenderer(new TextSelectionRenderer());
		service.registerRenderer(new ZoomRenderer());
		service.registerRenderer(new ArrowRenderer());
		service.registerRenderer(new LineRenderer());
		service.registerRenderer(new RectangleRenderer());
		service.registerRenderer(new EllipseRenderer());
		service.registerRenderer(new SelectRenderer());
		service.registerRenderer(new GridRenderer());

		return service;
	}

	private RenderService createPresentationRenderContext() {
		RenderService service = new RenderService();
		service.registerRenderer(new StrokeRenderer());
		service.registerRenderer(new PointerRenderer());
		service.registerRenderer(new TeXRenderer());
		service.registerRenderer(new TextRenderer());
		service.registerRenderer(new TextSelectionRenderer());
		service.registerRenderer(new ZoomRenderer());
		service.registerRenderer(new ArrowRenderer());
		service.registerRenderer(new LineRenderer());
		service.registerRenderer(new RectangleRenderer());
		service.registerRenderer(new EllipseRenderer());
		service.registerRenderer(new SelectRenderer());
		service.registerRenderer(new GridRenderer());

		return service;
	}
	
}
