package org.eclipse.epsilon.egl.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.epsilon.commons.util.StringProperties;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.EmfUtil;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.ModelRepository;

public class ModelManager {
	
	protected ServletContext servletContext;
	protected ModelRepository currentModelRepository;
	
	public void setCurrentModelRepository(ModelRepository currentModelRepository) {
		this.currentModelRepository = currentModelRepository;
	}
	
	public ModelRepository getCurrentModelRepository() {
		return currentModelRepository;
	}
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}
	
	public void loadModel(String name, String aliases, String modelFile, String metamodelUri, boolean expand) throws EolModelLoadingException {
		
		EmfModel model = new EmfModel();
		
		StringProperties properties = new StringProperties();
		properties.put(EmfModel.PROPERTY_NAME, name);
		properties.put(EmfModel.PROPERTY_ALIASES, aliases);
		properties.put(EmfModel.PROPERTY_EXPAND, expand + "");
		properties.put(EmfModel.PROPERTY_MODEL_FILE, this.getServletContext().getRealPath(modelFile));
		properties.put(EmfModel.PROPERTY_METAMODEL_URI, metamodelUri);
		properties.put(EmfModel.PROPERTY_IS_METAMODEL_FILE_BASED, "false");
		properties.put(EmfModel.PROPERTY_READONLOAD, "true");
		properties.put(EmfModel.PROPERTY_STOREONDISPOSAL, "false");
		
		model.load(properties, null);
		getCurrentModelRepository().addModel(model);
		
	}

	public void loadModel(String name, String modelFile, String metamodelUri) throws EolModelLoadingException {
		loadModel(name, "", modelFile, metamodelUri, true);
	}
	
	public void loadModelByFile(String name, String aliases, String modelFile, String metamodelFile, boolean expand) throws EolModelLoadingException {
		
		EmfModel model = new EmfModel();
		
		StringProperties properties = new StringProperties();
		properties.put(EmfModel.PROPERTY_NAME, name);
		properties.put(EmfModel.PROPERTY_ALIASES, aliases);
		properties.put(EmfModel.PROPERTY_EXPAND, expand + "");
		properties.put(EmfModel.PROPERTY_MODEL_FILE, this.getServletContext().getRealPath(modelFile));
		properties.put(EmfModel.PROPERTY_METAMODEL_FILE, this.getServletContext().getRealPath(metamodelFile));
		properties.put(EmfModel.PROPERTY_IS_METAMODEL_FILE_BASED, "true");
		properties.put(EmfModel.PROPERTY_READONLOAD, "true");
		properties.put(EmfModel.PROPERTY_STOREONDISPOSAL, "false");
		
		model.load(properties, null);
		getCurrentModelRepository().addModel(model);
		
	}
	
	public void loadModelByFile(String name, String modelFile, String metamodelFile) throws EolModelLoadingException {
		loadModelByFile(name, "", modelFile, metamodelFile, true);
	}	
	
	protected ArrayList<String> registeredMetamodels = new ArrayList<String>(); 
	
	public void registerMetamodel(String metamodelFile) throws Exception {
		if (registeredMetamodels.contains(metamodelFile)) return;
		EmfUtil.register(URI.createURI(getServletContext().getResource(metamodelFile).toString()), EPackage.Registry.INSTANCE);
		registeredMetamodels.add(metamodelFile);
	}
	
}
