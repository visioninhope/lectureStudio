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

package org.lecturestudio.web.service.rs.provider;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.lecturestudio.web.api.model.ClassroomServiceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Provider
@ApplicationScoped
public class ExceptionHandler implements ExceptionMapper<Throwable> {

	private static final Logger LOG = LogManager.getLogger(ExceptionHandler.class);


	@Override
	@Produces({MediaType.APPLICATION_JSON})
	public Response toResponse(Throwable exception) {
		LOG.error("An error has occurred", exception);

		ClassroomServiceResponse message = new ClassroomServiceResponse();
		message.statusCode = ClassroomServiceResponse.Status.ERROR.getCode();
		message.statusMessage = exception.getMessage();

		Response.ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR);
		builder.type(MediaType.APPLICATION_JSON);
		builder.entity(message);

		return builder.build();
	}

}
