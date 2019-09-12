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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axiom.soap.SOAPFaultReason;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.context.NamedValue;
import org.apache.axis2.handlers.AbstractHandler;
import org.apache.axis2.transport.http.HTTPConstants;

/**
 * Is responsible for adding a custom HTTP <i>X-Error</i> header to the response that contains a description of the 
 * error that prevented the successful processing of the request. Adding of the header can be disable by setting the
 * message context property {@link #DISABLE_ADD_ERROR_HEADER} to <code>Boolean.TRUE</code>.
 * 
 * @author Sander Fieten (sander at holodeck-b2b.org)
 */
public class SetHTTPXErrorHeader extends AbstractHandler {
	/**
	 * Name of the additional HTTP header that contains the error message
	 */
	private static final String X_ERROR_HEADER = "X-Error";
	/**
	 * Axis2 message context property that can be use to disable addition of the header
	 */
	public static final String DISABLE_ADD_ERROR_HEADER = "hb2b-org-axis2-rest-no-header";
	
	/* (non-Javadoc)
	 * @see org.apache.axis2.engine.Handler#invoke(org.apache.axis2.context.MessageContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InvocationResponse invoke(MessageContext msgContext) throws AxisFault {
		// Check if adding of the header has been disabled  
		Boolean noHeader;
		try {
			noHeader = (Boolean) msgContext.getProperty(DISABLE_ADD_ERROR_HEADER);
		} catch (ClassCastException cce) {
			// Ignore, use the default value to include header
			noHeader = Boolean.FALSE;
		}
		if (noHeader != null && noHeader)
			return InvocationResponse.CONTINUE;
		
		String	errorText;
		try {
			errorText = msgContext.getFailureReason() != null ? msgContext.getFailureReason().getMessage() : null;
			if (errorText == null) {
                SOAPFaultReason faultReason = msgContext.getEnvelope().getBody().getFault().getReason();
                errorText = faultReason.getFirstElement() != null ? faultReason.getFirstElement().getText() : 
                													faultReason.getText();
			}
		} catch (Exception e) {
			errorText = "No information available";
		}
		
		// There may already exist custom headers, but if not create them  
        Object customHeaders = msgContext.getProperty(HTTPConstants.HTTP_HEADERS);
        if (customHeaders == null) {
        	customHeaders = new HashMap<>();
        	msgContext.setProperty(HTTPConstants.HTTP_HEADERS, customHeaders);
        }
        // Add the new "X-Error" header to the set of custom header
        if (customHeaders instanceof List)
        	((List) customHeaders).add(new NamedValue(X_ERROR_HEADER, errorText));
        else if (customHeaders instanceof Map) 
        	((Map) customHeaders).put(X_ERROR_HEADER, errorText);            
         
        
		return InvocationResponse.CONTINUE;
	}

}
