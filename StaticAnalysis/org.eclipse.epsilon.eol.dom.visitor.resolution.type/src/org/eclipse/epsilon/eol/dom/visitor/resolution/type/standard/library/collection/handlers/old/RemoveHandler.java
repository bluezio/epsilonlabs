package org.eclipse.epsilon.eol.dom.visitor.resolution.type.standard.library.collection.handlers.old;

import org.eclipse.epsilon.eol.metamodel.*;

public class RemoveHandler {

	public void handle(MethodCallExpression methodCall)
	{ 
		if (methodCall.getArguments().size() == 1) { //there should only be one argument
			CollectionType targetType = (CollectionType) methodCall.getTarget().getResolvedType(); //obtain target type, at this stage, preliminary examination should already have the conclusion that the type is of type Collection
			Type contentType = targetType.getContentType(); //obtain content type from the collection
			
			if (contentType == null) {
				//this means the operation is performed on a collection of any, so no need for further actions
			}
			else {
				LiteralExpression argument = (LiteralExpression) methodCall.getArguments().get(0); //get the argument
				Type argumentType = argument.getResolvedType(); //get the type of the argument
				if (contentType.getClass() == argumentType.getClass()) {
					//match, no further actions
				}
				else {
					//handle type mismatch
				}
			}
			
			methodCall.setResolvedType(null); //in either case, remove() does not have a return type
			
		}
		else {
			//handle arguments number incorrect
		}
	}

}
