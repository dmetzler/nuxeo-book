<@extends src="base.ftl">


<@block name="header">You signed in as ${Context.principal}</@block>

<@block name="content">

<!-- Main hero unit for a primary marketing message or call to action -->
<div class="hero-unit">
    <h1>Hello, Welcome to our library</h1>
    <p>This is simple webengine project to show how it's easy to build web application on top of Nuxeo.</p>
    <p>You just have to use the menu provided in the toolbar to navigate thru libraries<p>

</div>


<div style="margin: 10px 10px 10px 10px">
<p>
This is the view corresponding to your root object: ${This.class.simpleName}.
</p>

<p>
You can find the code of this view in: src/main/resources/skin/views/${This.class.simpleName}
</p>

<p>
To render a view from an WebEngine object you should create @GET annotated method which is returning the view: getView("viewname") where <i>viewname</i> is the file name (without the ftl extension) in the views/ObjectName folder.
</p>

<p>
In a view you can access the object instance owning the view using ${r"${This}"} variable or the request context using the ${r"${Context}"} variable.
</p>

<p>
Also, you can use @block statements to create reusable layouts.
</p>

</div>

</@block>
</@extends>
