<@extends src="base.ftl">


<@block name="header">You signed in as ${Context.principal}</@block>

<@block name="content">

<!-- Main hero unit for a primary marketing message or call to action -->
<div class="hero-unit">
    <h1>Hello, Welcome to our library</h1>
    <p>This is simple webengine project to show how it's easy to build web application on top of Nuxeo.</p>
</div>


<div class="row">

  <div class="span8">
    <section>
      <div class="page-header">
        <h2>List of libraries</h2>
      </div>
      <ul>
        <#list This.libraries as library>
          <li><a href="${This.path}/${library.title}">${library.title}</a></li>
        </#list>
      </ul>
    </section>
  </div>

  <div class="span4">
    <form class="well form-inline" action="${This.path}" method="post">
      <legend>Create a new library</library>
      <input name="title" placeholder="Enter library's title"/>
      <button type="submit" class="btn btn-primary">Create</button>
    </form>
  </div>
</div>
</@block>
</@extends>
