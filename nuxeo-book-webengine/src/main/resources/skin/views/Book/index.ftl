<@extends src="base.ftl">


<@block name="header">You signed in as ${Context.principal}</@block>

<@block name="content">

<!-- Main hero unit for a primary marketing message or call to action -->
<div class="hero-unit">
    <h1>${This.book.title}</h1>
    <p>${This.book.description}</p>
</div>


<div class="row">

  <div class="span8">
    <section>
      <div class="page-header">
        <h2>Book properties</h2>
      </div>
      <table class="table table-bordered">
        <tbody>
          <tr><th>ISBN</th><td>${This.book.isbn}</td></tr>
          <tr><th>Author</th><td>${This.book.author}</td></tr>
          <tr><th>Published on</th><td>${This.book.published_at}</td></tr>
        </tbody>
      </table>
    </section>
  </div>

  <div class="span4">

    <form class="well form-inline" onsubmit="return confirm('Do you really want to delete this book ? ')" action="${This.path}/@delete" method="get">
      <button type="submit" class="btn btn-danger">Delete</button>
      <a href="${This.path}/@views/edit" class="btn">Edit</a>
    </form>
  </div>
</div>

</@block>
</@extends>
