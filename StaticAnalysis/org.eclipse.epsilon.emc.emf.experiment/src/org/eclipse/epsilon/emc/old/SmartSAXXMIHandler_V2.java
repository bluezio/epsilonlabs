package org.eclipse.epsilon.emc.old;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.FeatureNotFoundException;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ConfigurationCache;
import org.eclipse.emf.ecore.xmi.impl.SAXXMIHandler;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;

public class SmartSAXXMIHandler_V2 extends SAXXMIHandler{
	
	protected HashMap<String, HashMap<String, ArrayList<String>>> objectsAndRefNamesToVisit = new HashMap<String, HashMap<String,ArrayList<String>>>();
	protected HashMap<String, HashMap<String, ArrayList<String>>> actualObjectsToLoad = new HashMap<String, HashMap<String,ArrayList<String>>>();

 	protected HashMap<EClass, EObject> cache = new HashMap<EClass, EObject>();
 	
 	protected boolean handlingFeature = false;
 	
 	protected ArrayList<Integer> extentIndexToDelete = new ArrayList<Integer>(); 
	
	protected EClass traversal_currentClass;
	protected ArrayList<String> traversal_currentFeatures;
	
	protected boolean shouldHalt = false;
	protected String currentName = "";
	protected int currentElementsSize = -1;
	
	public void setObjectsAndRefNamesToVisit(
			HashMap<String, HashMap<String, ArrayList<String>>> objectsAndRefNamesToVisit) {
		this.objectsAndRefNamesToVisit = objectsAndRefNamesToVisit;
	}
	
	public void setActualObjectsToLoad(
			HashMap<String, HashMap<String, ArrayList<String>>> actualObjectsToLoad) {
		this.actualObjectsToLoad = actualObjectsToLoad;
	}
	
	public SmartSAXXMIHandler_V2(XMLResource xmiResource, XMLHelper helper,
			Map<?, ?> options) {
		super(xmiResource, helper, options);
	}
	
	public boolean cached(EClass eClass)
	{
		return cache.containsKey(eClass);
	}
	
	public EObject getCache(EClass eClass)
	{
		return cache.get(eClass);
	}
	
	public void insertCache(EClass eClass, EObject eObject)
	{
		if (!cache.containsKey(eClass)) {
			cache.put(eClass, eObject);	
		}
	}
	
	@Override
	@Deprecated
	protected EObject createObjectFromFactory(EFactory factory, String typeName) {
		
		EClass eClass = (EClass) factory.getEPackage().getEClassifier(typeName);
		//if the an instance of the class should be created
		if (shouldCreateObjectForClass(eClass)) {
			//prepare newObject
		    EObject newObject = null;
		    //if factory != null
		    if (factory != null)
		    {
		    	//create object
		    	newObject = helper.createObject(factory, typeName);

		    	//if object is not null, handle attributes and things
		    	if (newObject != null)
		    	{
		    		if (disableNotify)
		    			newObject.eSetDeliver(false);

		    		handleObjectAttribs(newObject);
		    	}
		    }
		    
		    //if this one is not a feature but rather an independent top level object, add to extent
		    if (!handlingFeature) {
		    		extent.add(newObject);	
			}
		    
		    EObject result = null;
			    if (factory != null)
			    {
//			    	result = helper.createObject(factory, typeName);
			    	result = EcoreUtil.copy(newObject);
//			    	if (result != null)
//			    	{
//			    		if (disableNotify)
//			    			result.eSetDeliver(false);
//
//			    		handleObjectAttribs(result);
//			    	}
			    }
		    return result;
		}
		else {
			EObject newObject = null;
			    if (factory != null)
			    {
			    	newObject = helper.createObject(factory, typeName);

//			    	if (newObject != null)
//			    	{
//			    		if (disableNotify)
//			    			newObject.eSetDeliver(false);
//
//			    		handleObjectAttribs(newObject);
//			    	}
			    }
		    return newObject;
		}
	}
	

	protected EObject createObjectFromFactory_v2(EFactory factory, String typeName) {
//		long init = System.nanoTime();
		
		EClass eClass = (EClass) factory.getEPackage().getEClassifier(typeName);
		//if the an instance of the class should be created
		if (shouldCreateObjectForClass(eClass)) {
			//prepare newObject
		    EObject newObject = null;
		    //if factory != null
		    if (factory != null)
		    {
		    	//create object
		    	newObject = helper.createObject(factory, typeName);

		    	//if object is not null, handle attributes and things
		    	if (newObject != null)
		    	{
//		    		if (disableNotify)
		    			newObject.eSetDeliver(false);

		    		handleObjectAttribs(newObject);
		    	}
		    }
		    
		    //if this one is not a feature but rather an independent top level object, add to extent
		    if (!handlingFeature) {
		    		extent.add(newObject);	
			}
		    
		    //prepare result
		    EObject result = null;
		    //if cached, return cache
		    if (cached(eClass)) {
		    	result = getCache(eClass);
			}
		    //if not cached, create cache and return cache
		    else {
			    if (factory != null)
			    {
			    	result = EcoreUtil.copy(newObject);
			    }
			    insertCache(eClass, result);
			}
		    return result;
		}
		else {
			//if cached return cache, if not create object and return cache
			EObject newObject = null;
			if (cached(eClass)) {
				newObject = getCache(eClass);
			}
			else {
			    if (factory != null)
			    {
			    	newObject = helper.createObject(factory, typeName);

			    }
			    insertCache(eClass, newObject);
			}
		    return newObject;
		}
	}
		
	
	@Override
	protected void handleObjectAttribs(EObject obj) {
    if (attribs != null)
    {
      InternalEObject internalEObject = (InternalEObject)obj;
      for (int i = 0, size = attribs.getLength(); i < size; ++i)
      {
        String name = attribs.getQName(i);
        if (name.equals(ID_ATTRIB))
        {
          xmlResource.setID(internalEObject, attribs.getValue(i));
        }
        else if (name.equals(hrefAttribute) && (!recordUnknownFeature || types.peek() != UNKNOWN_FEATURE_TYPE || obj.eClass() != anyType))
        {
          handleProxy(internalEObject, attribs.getValue(i));
        }
        else if (isNamespaceAware)
        {
          String namespace = attribs.getURI(i);
          if (!ExtendedMetaData.XSI_URI.equals(namespace) && !notFeatures.contains(name))
          {
            setAttribValue(obj, name, attribs.getValue(i));
          }
        }
        else if (!name.startsWith(XMLResource.XML_NS) && !notFeatures.contains(name))
        {
          setAttribValue(obj, name, attribs.getValue(i));
        }
      }
    }
  }

	@Override
	protected void setAttribValue(EObject object, String name, String value) {
		super.setAttribValue(object, name, value);
	}
	
	@Override
		public void endDocument() {
		
		for(int index: extentIndexToDelete)
		{
			extent.remove(index);
		}
	    if (deferredExtent != null)
	    {
	      extent.addAll(deferredExtent);
	    }

	    if (usedNullNamespacePackage)
	    {
	      helper.addPrefix("", "");
	    }
	    helper.recordPrefixToURIMapping();
	    helper.popContext();
	    handleForwardReferences(false);

	    if (disableNotify)
	    {
	      for (Iterator<?> i = EcoreUtil.getAllContents(xmlResource.getContents(), false); i.hasNext(); )
	      {
	        EObject eObject = (EObject)i.next();
	        eObject.eSetDeliver(true);
	      }
	    }

	    if (extendedMetaData != null)
	    {
	      if (extent.size() == 1)
	      {
	        EObject root = extent.get(0);
	        recordNamespacesSchemaLocations(root);
	      }

	      if (DEBUG_DEMANDED_PACKAGES)
	      {
	        // EATM temporary for debug purposes only.
	        //
	        Collection<EPackage> demandedPackages = EcoreUtil.copyAll(extendedMetaData.demandedPackages());
	        for (EPackage ePackage : demandedPackages)
	        {
	          ePackage.setName(ePackage.getNsURI());
	        }
	        extent.addAll(demandedPackages);
	      }
	    }
	  }
	
	@Override
		public void startElement(String uri, String localName, String name) {
			// TODO Auto-generated method stub

		if (shouldHalt) {
		    elements.push(name);
		}
		else {
			if (objects.size() == 0) {
				super.startElement(uri, localName, name);	
			}
			else {
				EObject peekObject = objects.peekEObject();
				
				if (peekObject instanceof DynamicEObjectImpl) {
					EClass leClass = peekObject.eClass();
					if (shouldProceed(leClass, name)) {
//						if (!shouldCreateObjectForClass(leClass)) {
//							extent.remove(peekObject);
//						}
//						if (shouldCreate(peekObject, name)) {
							super.startElement(uri, localName, name);
//						}
//						else {
//							halt(name);
//						}
					}
					else {
						halt(name);
					}
				}
				else {
					super.startElement(uri, localName, name);
//					if (peekObject instanceof EPackage) {
//						EPackage ePack = (EPackage) peekObject;
//						if (shouldProceed(ePack, name)) {
//							super.startElement(uri, localName, name);
//						}
//						else {
//							halt(name);
//						}
//					}
//					else if (peekObject instanceof EClass) {
//						EClass leClass = (EClass) peekObject;
//						if (shouldProceed(leClass, name)) {
//							super.startElement(uri, localName, name);
//						}
//						else {
//							halt(name);
//						}
//					}
				}
			}
		}
	}
	
		
	@Override
	public void endElement(String uri, String localName, String name) {
		
		if (shouldHalt && currentElementsSize != -1) {
			if (elements.size() >= currentElementsSize) {
				if (name.equals(currentName) && elements.size() == currentElementsSize) {
					shouldHalt = false;
					elements.pop();	
				}
				else {
					elements.pop();	
				}
			}
			else
			{
				shouldHalt = false;	
			}
		}
		else
		{
			super.endElement(uri, localName, name);	
		}
	}
	
	public void halt(String name)
	{
		currentElementsSize = elements.size();
		currentName = name;
		shouldHalt = true;
	}
	
//	public boolean shouldCreate(String featureName)
//	{
//		int stackSize = classStack.size();
//		if (stackSize == 0) {
//			return true;
//		}
//		else {
//			EClass topClass = classStack.get(stackSize-1);
//			for(EReference eReference : topClass.getEAllReferences())
//			{
//				if (eReference.getName().equals(featureName)) {
//					EClass eType = (EClass) eReference.getEType();
//					if (shouldCreateObjectForClass(eType)) {
//						classStack.add(eType);
//						return true;
//					}
//				}
//			}
//			classStack.remove(stackSize-1);
//			return false;
//		}
//	}
	
	public boolean shouldHandleFeature(EObject object, String featureName)
	{
		EClass eClass = object.eClass();
		String className = eClass.getName();
		String packageName = eClass.getEPackage().getName();
		HashMap<String, ArrayList<String>> subMap = actualObjectsToLoad.get(packageName);
		if (subMap != null) {
			ArrayList<String> features = subMap.get(className);
			if (features != null && features.contains(featureName)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	
	
	public boolean shouldCreate(EObject peekObject, String featureName)
	{
		EClass eClass = peekObject.eClass();
		EClass eType = null;
		for(EReference eReference : eClass.getEAllReferences())
		{
			if (eReference.getName().equals(featureName)) {
				eType = (EClass) eReference.getEType();
				if (shouldCreateObjectForClass(eType)) {
					return true;
				}
			}
		}
//		objects.add(eType.getEPackage().getEFactoryInstance().create(eType));

		return false;

	}

	
	public boolean shouldCreateObjectForClass(EClass eClass)
	{
		String epackage = eClass.getEPackage().getName();
		HashMap<String, ArrayList<String>> subMap = actualObjectsToLoad.get(epackage);
		if (subMap == null) {
			return false;
		}
		
		if (subMap.keySet().contains(eClass.getName())) {
			return true;
		}
		return false;
	}
	
	public boolean shouldProceed(EPackage ePack, String name)
	{
		String epackage = "ecore";
		HashMap<String, ArrayList<String>> subMap = objectsAndRefNamesToVisit.get("ecore");
		//if submap is not null
		if (subMap != null) {
			//get the features of the submap
			 ArrayList<String> features = subMap.get("EPackage");
			 //if features is not null
			 if (features != null) {
				//set the current features as features for caching 
				traversal_currentFeatures = features;
				//if features contains the feature name
				if (features.contains(name)) {
					return true;
				}
				//if features size is 0, should return true, too
				if (features.size() == 0) {
					return true;
				}
			}
		 }
		return false;
	}
	
	
	public boolean shouldProceed(EClass eClass, String name)
	{
		//if current class is not null and current class is equal to the class in question
		if (traversal_currentClass != null && traversal_currentClass.getName().equals(eClass.getName())) {
			if (traversal_currentFeatures.contains(name)) {
				return true;
			}
			if (traversal_currentFeatures.size() == 0) {
				return true;
			}
		}
		else {
			//set the current class to the eclass under question
			traversal_currentClass = eClass;
			//get epackage string
			String epackage = eClass.getEPackage().getName();
			//get classname string
			String className = eClass.getName();
			//get subMap
			HashMap<String, ArrayList<String>> subMap = objectsAndRefNamesToVisit.get(epackage);
			//if submap is not null
			if (subMap != null) {
				//get the features of the submap
				 ArrayList<String> features = subMap.get(className);
				 //if features is not null
				 if (features != null) {
					//set the current features as features for caching 
					traversal_currentFeatures = features;
					//if features contains the feature name
					if (features.contains(name)) {
						return true;
					}
					//if features size is 0, should return true, too
					if (features.size() == 0) {
						return true;
					}
				}
			 }

		}
		 return false;
		 
	}
	
	@Override
	protected void handleFeature(String prefix, String name) {
		
	    EObject peekObject = objects.peekEObject();

	    // This happens when processing an element with simple content that has elements content even though it shouldn't.
	    //
	    if (peekObject == null)
	    {
	      types.push(ERROR_TYPE);
	      error
	        (new FeatureNotFoundException
	          (name,
	           null,
	           getLocation(),
	           getLineNumber(),
	           getColumnNumber()));
	      return;
	    }

	    EStructuralFeature feature = getFeature(peekObject, prefix, name, true);
	    if (feature != null)
	    {
	    	if (shouldHandleFeature(peekObject, name)) {
				peekObject = extent.get(extent.size()-1);
				handlingFeature = true;
			}
	    	else {
				handlingFeature = false;
			}

	      int kind = helper.getFeatureKind(feature);
	      if (kind == XMLHelper.DATATYPE_SINGLE || kind == XMLHelper.DATATYPE_IS_MANY)
	      {
	        objects.push(null);
	        mixedTargets.push(null);
	        types.push(feature);
	        if (!isNull())
	        {
	          text = new StringBuffer();
	        }
	      }
	      else if (extendedMetaData != null)
	      {
	        EReference eReference = (EReference)feature;
	        boolean isContainment = eReference.isContainment();
	        if (!isContainment && !eReference.isResolveProxies() && extendedMetaData.getFeatureKind(feature) != ExtendedMetaData.UNSPECIFIED_FEATURE)
	        {
	          isIDREF = true;
	          objects.push(null);
	          mixedTargets.push(null);
	          types.push(feature);
	          text = new StringBuffer();
	        }
	        else
	        {
	          createObject(peekObject, feature);
	          EObject childObject = objects.peekEObject();
	          if (childObject != null)
	          {
	            if (isContainment)
	            {
	              EStructuralFeature simpleFeature = extendedMetaData.getSimpleFeature(childObject.eClass());
	              if (simpleFeature != null)
	              {
	                isSimpleFeature = true;
	                isIDREF = simpleFeature instanceof EReference;
	                objects.push(null);
	                mixedTargets.push(null);
	                types.push(simpleFeature);
	                text = new StringBuffer();
	              }
	            }
	            else if (!childObject.eIsProxy())
	            {
	              text = new StringBuffer();
	            }
	          }
	        }
	      }
	      else
	      {
	        createObject(peekObject, feature);
	      }
	    }
	    else
	    {
	      // Try to get a general-content feature.
	      // Use a pattern that's not possible any other way.
	      //
	      if (xmlMap != null && (feature = getFeature(peekObject, null, "", true)) != null)
	      {

	        EFactory eFactory = getFactoryForPrefix(prefix);

	        // This is for the case for a local unqualified element that has been bound.
	        //
	        if (eFactory == null)
	        {
	          eFactory = feature.getEContainingClass().getEPackage().getEFactoryInstance();
	        }

	        EObject newObject = null;
	        if (useNewMethods)
	        {
	          newObject = createObject(eFactory, helper.getType(eFactory, name), false);
	        }
	        else
	        {
	            newObject = createObjectFromFactory(eFactory, name);
	        }
	        newObject = validateCreateObjectFromFactory(eFactory, name, newObject, feature);
	        if (newObject != null)
	        {
	          setFeatureValue(peekObject, feature, newObject);
	        }
	        processObject(newObject);
	      }
	      else
	      {
	        // This handles the case of a substitution group.
	        //
	        if (xmlMap != null)
	        {
	          EFactory eFactory = getFactoryForPrefix(prefix);
	          EObject newObject = createObjectFromFactory(eFactory, name);
	          validateCreateObjectFromFactory(eFactory, name, newObject);
	          if (newObject != null)
	          {
	            for (EReference eReference : peekObject.eClass().getEAllReferences())
	            {
	              if (eReference.getEType().isInstance(newObject))
	              {
	                setFeatureValue(peekObject, eReference, newObject);
	                processObject(newObject);
	                return;
	              }
	            }
	          }
	        }

	        handleUnknownFeature(prefix, name, true, peekObject, null);
	      }
	    }
	  }

	
	
	
	@Override
	protected EObject createDocumentRoot(String prefix, String uri,
			String name, EFactory eFactory, boolean top) {

	    if (extendedMetaData != null && eFactory != null)
	    {
	      EPackage ePackage = eFactory.getEPackage();
	      EClass eClass = null;
	      if (useConfigurationCache)
	      {
	        eClass = ConfigurationCache.INSTANCE.getDocumentRoot(ePackage);
	        if (eClass == null)
	        {
	          eClass = extendedMetaData.getDocumentRoot(ePackage);
	          ConfigurationCache.INSTANCE.putDocumentRoot(ePackage, eClass);
	        }
	      }
	      else
	      {
	        eClass = extendedMetaData.getDocumentRoot(ePackage);
	      }
	      if (eClass != null)
	      {
	        // EATM Kind of hacky.
	        String typeName = extendedMetaData.getName(eClass);
	        @SuppressWarnings("deprecation") EObject newObject =
	          useNewMethods ?
	            createObject(eFactory, eClass, true) :
	              helper.createObject(eFactory, typeName);
	        validateCreateObjectFromFactory(eFactory, typeName, newObject);
	        if (top)
	        {
	          if (suppressDocumentRoot)
	          {
	            // Set up a deferred extent so the document root we create definitely will not be added to the resource.
	            //
	            List<EObject> oldDeferredExtent = deferredExtent;
	            try
	            {
	              deferredExtent = new ArrayList<EObject>();
	              processTopObject(newObject);
	            }
	            finally
	            {
	              deferredExtent = oldDeferredExtent;
	            }
	            handleFeature(prefix, name);

	            // Remove the document root's information from the top of the stacks.
	            //
	            objects.remove(0);
	            mixedTargets.remove(0);
	            types.remove(0);

	            // Process the new root object if any.
	            //
	            EObject peekObject = objects.peek();
	            if (peekObject == null)
	            {
	              // There's an EObject on the stack already.
	              //
	              if (objects.size() > 1)
	              {
	                // Excise the new root from the document root.
	                //
	                EcoreUtil.remove(peekObject = objects.get(0));
	              }
	              else
	              {
	                // If there is no root object, we're dealing with an EAttribute feature instead of an EReference feature.
	                // So create an instance of simple any type and prepare it to handle the text content.
	                //
	                SimpleAnyType simpleAnyType = (SimpleAnyType)EcoreUtil.create(anySimpleType);
	                simpleAnyType.setInstanceType(((EAttribute)types.peek()).getEAttributeType());
	                objects.set(0, simpleAnyType);
	                types.set(0, XMLTypePackage.Literals.SIMPLE_ANY_TYPE__RAW_VALUE);
	                mixedTargets.set(0, simpleAnyType.getMixed());
	                peekObject = simpleAnyType;
	              }
	            }
	            else
	            {
	              // Excise the new root from the document root.
	              //
	              EcoreUtil.remove(peekObject);
	            }
	            // Do the extent processing that should have been done for the root but was actualljy done for the document root.
	            //
	            if (deferredExtent != null)
	            {
	              deferredExtent.add(peekObject);
	            }
	            else
	            {
//	              extent.addUnique(peekObject);
	            }

	            // The new root object is the actual new object since all sign of the document root will now have disappeared.
	            //
	            newObject = peekObject;
	          }
	          else
	          {
	            processTopObject(newObject);
	            handleFeature(prefix, name);
	          }
	        }
	        return newObject;
	      }
	    }
	    return null;
	  
	}
	
	
	
	
	@Override
	protected void processTopObject(EObject object) {
	    if (object != null)
	    {
	      if (deferredExtent != null)
	      {
	        deferredExtent.add(object);
	      }
	      else
	      {
//	    	  extentIndexToDelete.add(extent.size());
//	    	  extent.addUnique(object);
	      }

	      if (extendedMetaData != null && !mixedTargets.isEmpty())
	      {
	        FeatureMap featureMap = mixedTargets.pop();
	        EStructuralFeature target = extendedMetaData.getMixedFeature(object.eClass());
	        if (target != null)
	        {
	          FeatureMap otherFeatureMap = (FeatureMap)object.eGet(target);
	          for (FeatureMap.Entry entry : new ArrayList<FeatureMap.Entry>(featureMap))
	          {
	            // Ignore a whitespace only text entry at the beginning.
	            //
	            if (entry.getEStructuralFeature() !=  XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT ||
	                  !"".equals(XMLTypeUtil.normalize(entry.getValue().toString(), true)))
	            {
	              otherFeatureMap.add(entry.getEStructuralFeature(), entry.getValue());
	            }
	          }
	        }
	        text = null;
	      }
	    }

	    processObject(object);
	  }
}
