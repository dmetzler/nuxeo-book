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
        <h2>Edit "${This.book.title}"</h2>
      </div>


      <form name="editBook" class="form-horizontal" action="${This.path}/@put" method="post">

        <div class="control-group">
          <label class="control-label" for="dc:title">Title</label>
          <div class="controls">
            <input required name="dc:title" value="${This.book.title}" />
          </div>
        </div>

        <div class="control-group">
          <label class="control-label" for="dc:description">Description</label>
          <div class="controls">
            <textarea name="dc:description">${This.book.description}</textarea>
          </div>
        </div>

        <div class="control-group">
          <label class="control-label" for="bk:author">Author</label>
          <div class="controls">
            <input name="bk:author" value="${This.book.author}">
          </div>
        </div>

        <div class="control-group">
          <label class="control-label" for="bk:isbn">ISBN</label>
          <div class="controls">
            <input required name="bk:isbn" value="${This.book.isbn}">
          </div>
        </div>


        <div class="control-group">

          <div class="controls">
            <button type="submit" class="btn btn-primary">Save</button>
          </div>
        </div>

    </section>
  </div>


</div>

</@block>
</@extends>
