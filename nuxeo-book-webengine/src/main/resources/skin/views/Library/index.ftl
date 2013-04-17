<@extends src="base.ftl">


<@block name="header">You signed in as ${Context.principal}</@block>

<@block name="content">

<!-- Main hero unit for a primary marketing message or call to action -->
<div class="hero-unit">
    <h1>${This.library.title}</h1>
    <p>${This.library.description}</p>
</div>


<div class="row">

  <div class="span8">
    <section>
      <div class="page-header">
        <h2>List of books</h2>
      </div>
      <table class="table table-bordered">
        <thead>
          <tr><th>Title</th><th>Author</th></tr>
        </thead>
        <tbody>
        <#list This.library.books as book>
          <tr>
            <td><a href="${This.path}/${book.title}">${book.title}</a></td>
            <td>${book.author}</td>
        </#list>
        </tbody>
      </table>
    </section>
  </div>

  <div class="span4">
    <form class="well form-inline" action="${This.path}" method="post">
      <legend>Create a new book</library>
      <input name="title" placeholder="Enter book's title"/><br/>
      <input name="author" placeholder="Enter book's autor"/>
      <button type="submit" class="btn btn-primary">Create</button>
    </form>
  </div>
</div>

</@block>
</@extends>
