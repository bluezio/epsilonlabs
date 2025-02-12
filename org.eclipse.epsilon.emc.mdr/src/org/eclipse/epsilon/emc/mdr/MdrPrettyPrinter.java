/*******************************************************************************
 * Copyright (c) 2008 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.emc.mdr;

import javax.jmi.reflect.RefObject;

import org.eclipse.epsilon.eol.execute.prettyprinting.PrettyPrinter;

public class MdrPrettyPrinter implements PrettyPrinter{

	public boolean appliesTo(Object o) {
		return o instanceof RefObject;
	}

	public String print(Object o) {
		return MdrUtil.refObjectToString(o);
	}


}
