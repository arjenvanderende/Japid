package cn.bran.japid.classmeta;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.bran.japid.tags.Each;
import cn.bran.japid.template.JapidTemplateBase;
import cn.bran.japid.template.JapidTemplateBaseStreaming;
import cn.bran.japid.template.RenderResult;

/**
 * lots of the code block generation is done here
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class AbstractTemplateClassMetaData {
	private static final String SPACE = " ";
	private static final String STATIC = "static";
	private static final String IMPORT = "import";
	private static Set<String> globalStaticImports = new HashSet<String>();
	private Set<String> staticImports = new HashSet<String>();
	private String originalTemplate;

	// control if we use a streaming based API or StringBuilder based API
	public static boolean streaming = false;
	// if we need to track the time to render
	boolean stopWatch = false;

	public String getOriginalTemplate() {
		return originalTemplate;
	}

	public void setOriginalTemplate(String originalTemplate) {
		this.originalTemplate = originalTemplate.replace('\\', '/');
	}

	public StringBuilder sb = new StringBuilder();
	protected static final String SEMI = ";";
	protected static final String TAB = "\t";
	protected static final String RENDER_RESULT = RenderResult.class.getName();
	public String packageName;
	public String className;

	// each line: byte[] _lineXXX=new byte[]{12, 23, 45};
	List<String> statics = new ArrayList<String>();
	int staticCounter = 0;

	// List<String>importsLines = new ArrayList<String>();

	/**
	 * the main body part
	 */
	public String body;
	protected List<InnerClassMeta> innersforTagCalls = new ArrayList<InnerClassMeta>();

	public AbstractTemplateClassMetaData() {
		super();
	}

	protected void pln(Object... ss) {
		for (Object o : ss) {
			sb.append(o);
		}
		sb.append("\n");
	}

	void p(String s) {
		sb.append(s);
	}

	public void addCallTagBodyInnerClass(String className, int count, String classArgs, String body) {
		this.innersforTagCalls.add(new InnerClassMeta(className, count, classArgs, body));
	}

	/**
	 * 
	 */
	public void printHeaders() {
		if (packageName != null) {
			pln("package " + packageName + SEMI);
		}
		pln("import java.util.*;");
		pln("import java.io.*;");
		// some nameing convention suport
		// cannot
		// pln("import japidviews._tags.*;");
		// pln("import japidviews._layouts.*;");

		if (streaming)
			pln("import " + cn.bran.japid.tags.streaming.Each.class.getName() + ";");
		else
			pln("import " + cn.bran.japid.tags.Each.class.getName() + ";");
		// pln("import java.math.*;");
		// pln("import static java.lang.Math.*;");
		// // should decouple with JavaExtensions
		// pln("import static play.templates.JavaExtensions.*;");
		for (String l : globalImports) {
			l = l.trim();
			if (!l.endsWith(";"))
				l = l + ";";
			if (!l.startsWith(IMPORT))
				l = IMPORT + SPACE + l;
			pln(l);
		}

		for (String l : imports) {
			l = l.trim();
			if (!l.endsWith(";"))
				l = l + ";";
			if (!l.startsWith(IMPORT))
				l = IMPORT + SPACE + l;
			pln(l);
		}

		for (String l : globalStaticImports) {
			l = l.trim();
			if (!l.startsWith(IMPORT))
				l = IMPORT + SPACE + STATIC + SPACE + l;

			if (!l.endsWith(".*;")) {
				l += ".*;";
			}
			pln(l);
		}

		for (String l : staticImports) {
			l = l.trim();
			if (!l.startsWith(IMPORT))
				l = IMPORT + SPACE + STATIC + SPACE + l;

			if (!l.endsWith(".*;")) {
				l += ".*;";
			}
			pln(l);
		}

		pln("// NOTE: This file was generated from: " + originalTemplate);
		pln("// Change to this file will be lost next time the template file is compiled.");

	}

	protected void embedSourceTemplateName() {
		pln("\t" + "public static final String sourceTemplate = \"" + originalTemplate + "\";");
	}

	protected void embedContentType() {
		String t = contentType == null ? "text/html" : contentType;
		pln("\t" + "public static final String contentType = \"" + t + "\";");
	}

	/**
	 * 
	 */
	protected void callTags() {
		// inners
		for (InnerClassMeta inner : this.innersforTagCalls) {
			// create a resuable instance _tagName_indexand a instance
			// initializer
			String tagClassName = inner.tagName;
			String field = "private " + tagClassName + " _" + inner.tagName + inner.counter + " = new " + tagClassName + "(getOut());";
			pln("\t" + field);

			if (inner.renderBody != null) {
				// body class
				pln(inner.toString());
			}
		}
	}

	protected void printAnnotations() {
		for (Class<? extends Annotation> anno : typeAnnotations) {
			pln("@" + anno.getName());
		}
	}

	/**
	 * add import lines to the to be generated imports lines, import and the
	 * ending ; are optional
	 * 
	 * @param imp
	 */
	public static void addImportLineGlobal(String imp) {
		imp = imp.trim();
		if (imp.startsWith(IMPORT)) {
			imp = imp.substring(IMPORT.length()).trim();
		}

		globalImports.add(imp);
	}

	// protected void buildStatics() {
	// for (int i = 0; i < statics.size(); i++) {
	// pln("static byte[] static_" + i + " = getBytes(" + statics.get(i) +
	// ");");
	// }
	// }

	protected void buildStatics() {
		for (int i = 0; i < statics.size(); i++) {
			if (streaming)
				pln("static private byte[] static_" + i + " = getBytes(" + statics.get(i) + ");");
			else
				pln("static private String static_" + i + " = " + statics.get(i) + ";");
		}
	}

	protected void addConstructors() {
		// add one default and one to take a PrintWriter
		// pln(TAB + "public " + className + "() {\r\n" +
		// "		super();\r\n" +
		// "	}");
		if (streaming)
			pln(TAB + "public " + className + "(OutputStream out) {");
		else
			pln(TAB + "public " + className + "(StringBuilder out) {");
		pln(TAB + TAB + "super(out);");
		pln(TAB + "}");
	}

	/**
	 * 
	 */
	protected void classDeclare() {
		if (superClass == null) {
			String superName = JapidTemplateBase.class.getName();
			if (streaming)
				superName = JapidTemplateBaseStreaming.class.getName();
			if (this.getClass() == LayoutClassMetaData.class) {
				// abstract
				pln("public abstract class " + className + " extends " + superName + "{");
			} else {
				pln("public class " + className + " extends " + superName + "{");
			}
		} else {
			pln("public class " + className + " extends " + superClass + "{");
		}

	}

	public String superClass;

	public static void addImportStatic(Class<?> class1) {
		String className = class1.getName();
		globalStaticImports.add(className);
	}

	/**
	 * this is for globally adding static imports, usually by tools.
	 * 
	 * @param imp
	 */
	public static void addImportStaticGlobal(String imp) {
		if (imp.startsWith(IMPORT))
			imp = imp.substring(IMPORT.length()).trim();

		if (imp.startsWith(STATIC))
			imp = imp.substring(IMPORT.length()).trim();

		globalStaticImports.add(imp);
	}

	public void addImport(Class<?> class1) {
		String className = class1.getName();
		addImportLine(className);
	}

	private static Set<String> globalImports = new HashSet<String>();
	private Set<String> imports = new HashSet<String>();

	/**
	 * 
	 * @param text
	 *            something like \"hello\"
	 * @return
	 */
	public String addStaticText(String text) {
		if (text != null && !text.isEmpty()) {
			this.statics.add(text);
			return "static_" + (statics.size() - 1);
		} else
			return null;
	}

	/**
	 * add class level annotation
	 * 
	 * @param anno
	 */
	public static void addAnnotation(Class<? extends Annotation> anno) {
		typeAnnotations.add(anno);
	}

	static Set<Class<? extends Annotation>> typeAnnotations = new HashSet<Class<? extends Annotation>>();

	public void setContentType(String contentType) {
		// this.contentType = contentType;
	}

	String contentType;

	public void turnOnStopwatch() {
		this.stopWatch = true;
	}

	public void addStaticImports(String im) {
		staticImports.add(im);
	}

	public void addImportLine(String line) {
		this.imports.add(line);
	}
}