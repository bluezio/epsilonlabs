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
package org.openide.util.lookup;


import org.netbeans.lib.jmi.mapping.JMIMapperCFImpl;
import org.netbeans.lib.jmi.mapping.JMIMapperImpl;
import org.netbeans.lib.jmi.util.Logger;
import org.netbeans.lib.jmi.xmi.ConsumerFactory;
import org.netbeans.lib.jmi.xmi.ProducerFactory;
import org.netbeans.lib.jmi.xmi.ReaderFactory;
import org.netbeans.lib.jmi.xmi.WriterFactory;
import org.netbeans.lib.jmi.xmi.XMISaxReaderImpl;
import org.netbeans.lib.jmi.xmi.XMIWriterImpl;
import org.netbeans.lib.jmi.xmi.XmiDtdProducer;
import org.netbeans.mdr.NBMDRManagerImpl;
import org.openide.util.Lookup;

public class MdrModelLookup extends Lookup{
	private ProxyLookup delegate = new ProxyLookup();

	private boolean inited = false;

	private void called() {
		if(!inited) {
			inited = true;
			delegate.setLookups(new Lookup[] {
				Lookups.singleton(new XMISaxReaderImpl()),
				Lookups.singleton(new XMIWriterImpl()),
				Lookups.singleton(new XmiDtdProducer()),
				Lookups.singleton(new JMIMapperImpl()),
				Lookups.singleton(new JMIMapperCFImpl()),
				Lookups.singleton(new ReaderFactory()),
				Lookups.singleton(new WriterFactory()),
				Lookups.singleton(new ConsumerFactory()),
				Lookups.singleton(new ProducerFactory()),
				Lookups.singleton(new Logger()),
				Lookups.singleton(new NBMDRManagerImpl())
			});
		}
	}

	@Override
	public Object lookup(Class clazz) {
		called();
		Object ret = delegate.lookup(clazz);
		return ret;
	}


	@Override
	public Lookup.Result lookup(Lookup.Template template) {
		called();
		Lookup.Result ret = delegate.lookup(template);
		return ret;
	}
}
