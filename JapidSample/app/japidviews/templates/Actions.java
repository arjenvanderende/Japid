package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/templates/Actions.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class Actions extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/templates/Actions.html";
static private final String static_0 = ""
;
static private final String static_1 = "\n" + 
"\n" + 
"<form url=\""
;
static private final String static_2 = "\"></form>\n" + 
"<form url=\""
;
static private final String static_3 = "\"></form>\n" + 
"<form url=\""
;
static private final String static_4 = "\"></form>\n" + 
"<form url='"
;
static private final String static_5 = "'></form>\n" + 
"<form url=\""
;
static private final String static_6 = "\"></form>\n" + 
"";
	public Actions() {
		super(null);
	}
	public Actions(StringBuilder out) {
		super(out);
	}
	models.japidsample.Post post;
	public cn.bran.japid.template.RenderResult render(models.japidsample.Post post) {
		this.post = post;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {

		play.mvc.Http.Request request = play.mvc.Http.Request.current(); assert request != null;
		play.mvc.Http.Response response = play.mvc.Http.Response.current(); assert response != null;
		play.mvc.Scope.Flash flash = play.mvc.Scope.Flash.current();assert flash != null;
		play.mvc.Scope.Session session = play.mvc.Scope.Session.current();assert session != null;
		play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current(); assert renderArgs != null;
		play.mvc.Scope.Params params = play.mvc.Scope.Params.current();assert params != null;
		play.data.validation.Validation validation = play.data.validation.Validation.current();assert validation!= null;
		cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation.errors());assert errors != null;
		play.Play _play = new play.Play(); assert _play != null;
p(static_0);// line 1
p(static_1);// line 1
p(lookup("showAll", new Object[]{}));// line 4
p(static_2);// line 4
p(lookup("Clients.showAccounts", post.title, post.title));// line 5
p(static_3);// line 5
p(lookupAbs("Clients.showAccounts", post.title.substring(1, 2)));// line 6
p(static_4);// line 6
p(lookupAbs("Clients.showAccounts", new String[]{"aa", "bb"}));// line 7
p(static_5);// line 7
p(lookupStatic("/public/stylesheets/main.css"));// line 8
p(static_6);// line 8

	}
}