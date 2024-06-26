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
package org.lecturestudio.core.bus.event;

import org.lecturestudio.core.ExecutableState;

public class ExecutableEvent extends BusEvent {

	/** The state of the {@link ExecutableEvent} */
	private final ExecutableState state;


	/**
	 * Create the {@link ExecutableEvent} with the specified state.
	 *
	 * @param state The state.
	 */
	public ExecutableEvent(ExecutableState state) {
		this.state = state;
	}

	/**
	 * Get the state.
	 *
	 * @return The state.
	 */
	public ExecutableState getState() {
		return state;
	}

	/**
	 * Indicates whether {@link ExecutableEvent} is starting.
	 *
	 * @return {@code true} if the {@link #state} equals {@code
	 * ExecutableState.Starting}, otherwise {@code false}.
	 */
	public boolean starting() {
		return state == ExecutableState.Starting;
	}

	/**
	 * Indicates whether {@link ExecutableEvent} has started.
	 *
	 * @return {@code true} if the {@link #state} equals {@code
	 * ExecutableState.Started}, otherwise {@code false}.
	 */
	public boolean started() {
		return state == ExecutableState.Started;
	}

	/**
	 * Indicates whether {@link ExecutableEvent} is stopping.
	 *
	 * @return {@code true} if the {@link #state} equals {@code
	 * ExecutableState.Stopping}, otherwise {@code false}.
	 */
	public boolean stopping() {
		return state == ExecutableState.Stopping;
	}

	/**
	 * Indicates whether {@link ExecutableEvent} has stopped.
	 *
	 * @return {@code true} if the {@link #state} equals {@code
	 * ExecutableState.Stopped}, otherwise {@code false}.
	 */
	public boolean stopped() {
		return state == ExecutableState.Stopped;
	}
	
}
