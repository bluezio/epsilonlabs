package org.eclipse.epsilon.epl.dt.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epsilon.common.dt.editor.contentassist.IAbstractModuleEditorTemplateContributor;
import org.eclipse.jface.text.templates.Template;

public class EplEditorStaticTemplateContributor implements IAbstractModuleEditorTemplateContributor {
	
	List<Template> templates = null;
	public List<Template> getTemplates() {
		if (templates == null) {
			templates = new ArrayList<Template>();
			templates.add(new Template("transform", "transform rule", "", "rule ${rulename} \r\n\ttransform s : ${sourcemodel}!${sourcetype}\r\n\tto t : ${targetmodel}!${targettype} {\r\n\t${cursor}\r\n}",false));
			templates.add(new Template("common transform", "transform rule", "", "rule ${name} \r\n\ttransform s : Source!${name}\r\n\tto t : Target!${name} {\r\n\t${cursor}\r\n}",false));
			templates.add(new Template("pre", "block executed before the rules", "", "pre ${name} {\r\n\t${cursor}\r\n}",false));
			templates.add(new Template("post", "block executed after the rules", "", "post ${name} {\r\n\t${cursor}\r\n}",false));
			templates.add(new Template("equivalent", "equivalent in the target model", "", "equivalent()",false));
			templates.add(new Template("equivalents", "equivalents in the target model", "", "equivalents()",false));
		}
		return templates;
	}
}
