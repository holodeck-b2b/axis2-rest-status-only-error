/*
 * Copyright (C) 2018 The Holodeck B2B Team, Sander Fieten
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.holodeckb2b.axis2.rest;

import static org.apache.axis2.Constants.Configuration.MESSAGE_FORMATTER;
import static org.apache.axis2.addressing.AddressingConstants.DISABLE_ADDRESSING_FOR_OUT_MESSAGES;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.handlers.AbstractHandler;

/**
 * Is responsible for setting the Axis2 message formatter to {@link ApplicationXMLFormatter} to ensure there is no
 * entity body in case of an error (i.e. when an <code>AxisFault</code> was thrown).
 * 
 * @author Sander Fieten (sander at holodeck-b2b.org)
 */
public class SetMessageFormatter extends AbstractHandler {

	/* (non-Javadoc)
	 * @see org.apache.axis2.engine.Handler#invoke(org.apache.axis2.context.MessageContext)
	 */
	@Override
	public InvocationResponse invoke(MessageContext msgContext) throws AxisFault {
        msgContext.setProperty(MESSAGE_FORMATTER, new ApplicationXMLFormatter());
        msgContext.setDoingREST(true);
        msgContext.setProperty(DISABLE_ADDRESSING_FOR_OUT_MESSAGES, Boolean.TRUE);
		return InvocationResponse.CONTINUE;
	}

}
