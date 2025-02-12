package org.eclipse.epsilon.eol.visitor.resolution.type.operationDefinitionHandler;

import java.util.ArrayList;

import metamodel.connectivity.emf.EMFMetamodelDriver;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.eol.metamodel.*;
import org.eclipse.epsilon.eol.visitor.resolution.type.context.TypeResolutionContext;
import org.eclipse.epsilon.eol.visitor.resolution.type.operationDefinitionUtil.StandardLibraryOperationDefinitionContainer;

public class _deprecated_AsTypeHandler extends AnyOperationDefinitionHandler{

	public _deprecated_AsTypeHandler(TypeResolutionContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean appliesTo(String name, ArrayList<Type> argTypes) {
		// TODO Auto-generated method stub
		return name.equals("asType") && argTypes.size() == 1;
	}

	@Override
	public OperationDefinition handle(
			FeatureCallExpression featureCallExpression, Type contextType,
			ArrayList<Type> argTypes) {
		StandardLibraryOperationDefinitionContainer container = context.getOperationDefinitionControl().getStandardLibraryOperationDefinitionContainer();
		
		OperationDefinition result = container.getOperation(((MethodCallExpression) featureCallExpression).getMethod().getName(), argTypes);

		NameExpression param = (NameExpression) ((MethodCallExpression) featureCallExpression).getArguments().get(0);
		if (context.getTypeUtil().isKeyWord(param.getName()))
		{
			result.setReturnType(EcoreUtil.copy(argTypes.get(0)));
			return result;
		}
		else {
			String message = "argument \"" + param.getName() + "\" is not a defined type or a model element type";
			context.getLogBook().addError(param, message);
		}
		return result;
	}
}
