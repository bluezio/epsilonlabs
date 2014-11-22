package org.eclipse.epsilon.emc.emf;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl;
import org.eclipse.epsilon.eol.analysis.optimisation.loading.context.ModelContainer;

public class SmartEmfXMIResource extends EmfXMIResource{

	protected ArrayList<ModelContainer> modelContainers = new ArrayList<ModelContainer>(); // <-------------------- point of change

	public void addModelContainer(ModelContainer modelContainer)
	{
		modelContainers.add(modelContainer);
	}
	
	public void setModelContainers(ArrayList<ModelContainer> modelContainers) {
		this.modelContainers = modelContainers;
	}

	public SmartEmfXMIResource(URI uri) {
		super(uri);
	}

	@Override
	protected XMLLoad createXMLLoad() {
		SmartXMILoadImpl xmiLoadImpl = new SmartXMILoadImpl(createXMLHelper());
		xmiLoadImpl.setModelContainers(modelContainers);
		return xmiLoadImpl; // <----------------------- point of change
	}
	
	@Override
	protected XMLLoad createXMLLoad(Map<?, ?> options) {
		if (options != null && Boolean.TRUE.equals(options.get(OPTION_SUPPRESS_XMI)))
	    {
			SmartXMILoadImpl xmiLoadImpl = new SmartXMILoadImpl(new XMLHelperImpl(this));
			xmiLoadImpl.setModelContainers(modelContainers);
			return xmiLoadImpl;
	    }
	    else
	    {
	      return super.createXMLLoad(options);
	    }
	}
}
