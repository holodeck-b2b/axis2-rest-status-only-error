/**
 * 
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
 * @author Sander Fieten (sander at chasquis-consulting.com)
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
