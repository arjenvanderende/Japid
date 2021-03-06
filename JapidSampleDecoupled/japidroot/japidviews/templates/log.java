package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import japidviews._tags.*;
import play.i18n.Lang;
import play.mvc.Http.*;
import controllers.*;
//
// NOTE: This file was generated from: japidviews/templates/log.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class log extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/templates/log.html";
	{
		putHeader("Content-Type", "text/html; charset=utf-8");
		setContentType("text/html; charset=utf-8");
	}

// - add implicit fields with Play

	final play.mvc.Http.Request request = play.mvc.Http.Request.current(); 
	final play.mvc.Http.Response response = play.mvc.Http.Response.current(); 
	final play.mvc.Scope.Session session = play.mvc.Scope.Session.current();
	final play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current();
	final play.mvc.Scope.Params params = play.mvc.Scope.Params.current();
	final play.data.validation.Validation validation = play.data.validation.Validation.current();
	final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);
	final play.Play _play = new play.Play(); 

// - end of implicit fields with Play 


	public log() {
		super(null);
	}
	public log(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/ };
	public static final String[] argTypes = new String[] {/* arg types of the template*/ };
	public static final Object[] argDefaults= new Object[] { };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.log.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	public cn.bran.japid.template.RenderResult render() {
		long __t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 0, japidviews/templates/log.html
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply() {
		return new log().render();
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
p("\n" + 
"log directives are used to print a line of information to the console. \n" + 
"It can take an argument of String\n" + 
"</p>\n");// line 1, log.html
		System.out.println("japidviews/templates/log.html(line 5): " + "");
		p("</p>\n" + 
"\n" + 
"hello world!\n" + 
"</p>\n" + 
"\n");// line 5, log.html
		 String a = "a";// line 11, log.html
 int i = 10;// line 12, log.html
		p("now with argument\n");// line 12, log.html
		System.out.println("japidviews/templates/log.html(line 14): " + a + i);
		p("\n" + 
"</p>\n" + 
"now with a message literal\n");// line 14, log.html
		System.out.println("japidviews/templates/log.html(line 18): " + "a message ");
		p("</p>");// line 18, log.html
		
		endDoLayout(sourceTemplate);
	}

}