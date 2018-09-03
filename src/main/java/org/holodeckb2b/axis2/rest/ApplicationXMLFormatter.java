/**
 * 
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
 * @author Sander Fieten (sander at chasquis-consulting.com)
 */
public class ApplicationXMLFormatter extends org.apache.axis2.transport.http.ApplicationXMLFormatter {

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
	 * Gets the bytes for this message, which in case of an error is just an empty array. Otherwise the parent's 
	 * implementation is used to generate the content.
	 * 
	 *  {@inheritDoc}
	 */
	@Override
	public byte[] getBytes(MessageContext messageContext, OMOutputFormat format, boolean preserve) throws AxisFault {

		if (messageContext.getFLOW() == MessageContext.OUT_FAULT_FLOW)
			return new byte[0];
		else
			return super.getBytes(messageContext, format, preserve);		
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
