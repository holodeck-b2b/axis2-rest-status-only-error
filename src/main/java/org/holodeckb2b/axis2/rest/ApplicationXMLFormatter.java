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

import java.io.OutputStream;

import org.apache.axiom.om.OMOutputFormat;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;

/**
 * Is an Axis2 <i>formatter</i> for handling REST responses and which ensures that no content is included in case of
 * an <code>AxisFault</code> was thrown.
 *
 * @author Sander Fieten (sander at holodeck-b2b.org)
 */
public class ApplicationXMLFormatter extends org.apache.axis2.kernel.http.ApplicationXMLFormatter {

	/**
	 * Gets the Content-Type of the response. As there is no content in case of an error, <code>null</code> is
	 * returned in such cases. Otherwise the content-type is determined by the parent implementation.
	 *
	 * {@inheritDoc}
	 */
	@Override
	public String getContentType(MessageContext messageContext, OMOutputFormat format, String soapAction) {
		if (messageContext.getFLOW() == MessageContext.OUT_FAULT_FLOW)
			return null;
		else
			return super.getContentType(messageContext, format, soapAction);
	}

	/**
	 * Writes the content of this message, which in case of an error is nothing, to the output stream. If not an
	 * error the parent's implementation is used to write the content.
	 *
	 *  {@inheritDoc}
	 */
	@Override
    public void writeTo(MessageContext messageContext, OMOutputFormat format, OutputStream outputStream,
    					boolean preserve) throws AxisFault {

		if (messageContext.getFLOW() == MessageContext.OUT_FAULT_FLOW)
			return;
		else
			super.writeTo(messageContext, format, outputStream, preserve);
	}
}
