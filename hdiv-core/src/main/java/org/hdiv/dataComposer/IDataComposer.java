/**
 * Copyright 2005-2013 hdiv.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hdiv.dataComposer;

import org.hdiv.state.IPage;
import org.hdiv.state.IState;

/**
 * DataComposers's main interface. There are three implementations: encryption, hash and memory
 * 
 * @author Roberto Velasco
 */
public interface IDataComposer {

	/**
	 * It generates a new encoded value for the parameter <code>parameter</code> and the value <code>value</code> passed
	 * as parameters. The returned value guarantees the confidentiality in the encoded and memory strategies if
	 * confidentiality indicator <code>confidentiality</code> is true.
	 * 
	 * @param parameter
	 *            HTTP parameter name
	 * @param value
	 *            value generated by server
	 * @param editable
	 *            parameter type: editable(textbox, password,etc.) or non editable (hidden, select, radio, ...)
	 * @return Codified value to send to the client
	 */
	public String compose(String parameter, String value, boolean editable);

	/**
	 * It generates a new encoded value for the parameter <code>parameter</code> and the value <code>value</code> passed
	 * as parameters. The returned value guarantees the confidentiality in the encoded and memory strategies if
	 * confidentiality indicator <code>confidentiality</code> is true.
	 * 
	 * @param action
	 *            target action
	 * @param parameter
	 *            HTTP parameter name
	 * @param value
	 *            value generated by server
	 * @param editable
	 *            parameter type: editable(textbox, password,etc.) or non editable (hidden, select, radio, ...)
	 * @return Codified value to send to the client
	 */
	public String compose(String action, String parameter, String value, boolean editable);

	/**
	 * It generates a new encoded value for the parameter <code>parameter</code> and the value <code>value</code> passed
	 * as parameters. The returned value guarantees the confidentiality in the encoded and memory strategies if
	 * confidentiality indicator <code>confidentiality</code> is true.
	 * 
	 * @param parameter
	 *            HTTP parameter name
	 * @param value
	 *            value generated by server
	 * @param editable
	 *            parameter type: editable(textbox, password,etc.) or non editable (hidden, select, radio, ...)
	 * @param editableName
	 *            editable name (text or textarea)
	 * @return Codified value to send to the client
	 * @since HDIV 1.1
	 */
	public String compose(String parameter, String value, boolean editable, String editableName);

	/**
	 * Adds a new IParameter object, generated from the values passed as parameters, to the current state
	 * <code>state</code>. If confidentiality is activated it generates a new encoded value that will be returned by the
	 * server for the parameter <code>parameter</code> in the encoded and memory strategies.
	 * 
	 * @param parameter
	 *            HTTP parameter
	 * @param value
	 *            value generated by server
	 * @param editable
	 *            Parameter type: editable(textbox, password,etc.) or non editable (hidden, select, radio, ...)
	 * @param isActionParam
	 *            parameter added in action attribute
	 * @param charEncoding
	 *            character encoding
	 * @return Codified value to send to the client
	 */
	public String compose(String parameter, String value, boolean editable, boolean isActionParam, String charEncoding);

	/**
	 * Adds a new IParameter object, generated from the values passed as parameters, to the current state
	 * <code>state</code>. If confidentiality is activated it generates a new encoded value that will be returned by the
	 * server for the parameter <code>parameter</code> in the encoded and memory strategies.
	 * 
	 * @param parameter
	 *            HTTP parameter
	 * @param value
	 *            value generated by server
	 * @param editable
	 *            Parameter type: editable(textbox, password,etc.) or non editable (hidden, select, radio, ...)
	 * @param editableName
	 *            editable name (text or textarea)
	 * @param isActionParam
	 *            parameter added in action attribute
	 * @param method
	 *            http method, GET or POST
	 * @param charEncoding
	 *            character encoding
	 * @return Codified value to send to the client
	 * @since HDIV 2.1.5
	 */
	public String compose(String parameter, String value, boolean editable, String editableName, boolean isActionParam,
			String method, String charEncoding);

	/**
	 * Adds a new IParameter object, generated from the values passed as parameters, to the current state
	 * <code>state</code>. If confidentiality is activated it generates a new encoded value that will be returned by the
	 * server for the parameter <code>parameter</code> in the encoded and memory strategies.
	 * <p>
	 * Custom method for form field.
	 * 
	 * @param parameter
	 *            HTTP parameter
	 * @param value
	 *            value generated by server
	 * @param editable
	 *            Parameter type: editable(textbox, password,etc.) or non editable (hidden, select, radio, ...)
	 * @param editableName
	 *            editable name (text or textarea)
	 * @return Codified value to send to the client
	 * @since HDIV 2.1.5
	 */
	public String composeFormField(String parameter, String value, boolean editable, String editableName);

	/**
	 * It generates a new encoded value for the parameter <code>parameter</code> and the value <code>value</code> passed
	 * as parameters. The returned value guarantees the confidentiality in the encoded and memory strategies if
	 * confidentiality indicator <code>confidentiality</code> is true.
	 * 
	 * @param action
	 *            target action
	 * @param parameter
	 *            parameter name
	 * @param value
	 *            value generated by server
	 * @param editable
	 *            parameter type: editable(textbox, password,etc.) or non editable (hidden, select,...)
	 * @param isActionParam
	 *            parameter added in action attribute
	 * @param charEncoding
	 *            character encoding
	 * @return Codified value to send to the client
	 */
	public String compose(String action, String parameter, String value, boolean editable, boolean isActionParam,
			String charEncoding);

	/**
	 * It is called by each request or form of the html page sent back by the server.
	 * 
	 * @return State id for this request. It can be null if it is impossible to precalculate the id for some strategy.
	 */
	public String beginRequest();

	/**
	 * It is called by each request or form of the html page returned by the server, as long as the destiny of the
	 * request is an action.
	 * 
	 * @param action
	 *            Action target
	 * @return State id for this request. It can be null if it is impossible to precalculate the id for some strategy.
	 */
	public String beginRequest(String action);

	/**
	 * It is called by each request or form of the html page returned by the server, as long as the destiny of the
	 * request is an action. The created IState is based on the passed as parameter.
	 * 
	 * @param state
	 *            Base state
	 * 
	 * @return State id for this request. It can be null if it is impossible to precalculate the id for some strategy.
	 */
	public String beginRequest(IState state);

	/**
	 * It is called in the pre-processing stage of each request or form existing in the page returned by the server.
	 * 
	 * @return Identifier composed by the page identifier and the state identifier.
	 */
	public String endRequest();

	/**
	 * Initializes {@link IDataComposer}.
	 */
	public void init();

	/**
	 * It is called in the pre-processing stage of each user request.
	 */
	public void startPage();

	/**
	 * It is called in the pre-processing stage of each user request. Create a new {@link IPage} based on an existing
	 * page.
	 * 
	 * @param existingPage
	 *            other IPage
	 */
	public void startPage(IPage existingPage);

	/**
	 * It is called in the post-processing stage of each user request.
	 */
	public void endPage();

	/**
	 * Creates a new parameter called <code>newParameter</code> and adds all the values of <code>oldParameter</code>
	 * stored in the state to it.
	 * 
	 * @param oldParameter
	 *            name of the parameter stored in the state
	 * @param newParameter
	 *            name of the new parameter
	 */
	public void mergeParameters(String oldParameter, String newParameter);

	/**
	 * Adds the flow identifier to the page of type <code>IPage</code>.
	 * 
	 * @since HDIV 2.0.3
	 */
	public void addFlowId(String id);

	/**
	 * Returns true if this method is executed between beginRequest and endRequest
	 * 
	 * @return is request started?
	 * @since HDIV 2.1.0
	 */
	public boolean isRequestStarted();
}
