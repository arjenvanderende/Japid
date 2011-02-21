package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import	 	models.japidsample.Post;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
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
//
// NOTE: This file was generated from: japidviews/templates/AllPost.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class AllPost extends Layout
{	public static final String sourceTemplate = "japidviews/templates/AllPost.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}
	public AllPost() {
		super(null);
	}
	public AllPost(StringBuilder out) {
		super(out);
	}
	private String blogTitle;
	private List<Post> allPost;
	public cn.bran.japid.template.RenderResult render(String blogTitle, List <Post> allPost) {
		this.blogTitle = blogTitle;
		this.allPost = allPost;
		long t = -1;
		t = System.currentTimeMillis();
		super.layout();
		t = System.currentTimeMillis() - t;
		System.out.println("[AllPost] rendering time: " + t);
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {

// -- set up the tag objects
final Display _Display1 = new Display(getOut());
_Display1.setActionRunners(getActionRunners());

final Tag2 _Tag22 = new Tag2(getOut());
_Tag22.setActionRunners(getActionRunners());

// -- end of the tag objects


// - add implicit variables 

		final Request request = Request.current(); assert request != null;

		final Response response = Response.current(); assert response != null;

		final Flash flash = Flash.current();assert flash != null;

		final Session session = Session.current();assert session != null;

		final RenderArgs renderArgs = RenderArgs.current(); assert renderArgs != null;

		final Params params = Params.current();assert params != null;

		final Validation validation = Validation.current();assert validation!= null;

		final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);assert errors != null;

		final play.Play _play = new play.Play(); assert _play != null;

// - end of implicit variables 


//------
;// line 1
p("\n");// line 4
p("\n");// line 6
for (Post p: allPost) { // line 8
p("        ");// line 8
p("\n" + 
"\n" + 
"	    ");// line 9
_Display1.render(p, "home", new Display.DoBody<String>(){
public void render(final String title) {
// line 11
p("		   The real title is: ");// line 11
p(title);// line 12
p(";\n" + 
"	    ");// line 12

}
}
);
// line 11
}// line 14
p("\n");// line 14
_Tag22.render(blogTitle);
// line 16
p("\n" + 
"<p>cool</p>");// line 16

	}

	@Override protected void title() {
		p("Home");;
	}
}