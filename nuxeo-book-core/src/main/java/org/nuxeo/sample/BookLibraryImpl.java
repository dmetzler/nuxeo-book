/*
 * (C) Copyright 2013 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     dmetzler
 */
package org.nuxeo.sample;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelList;

/**
 * @author dmetzler
 *
 */
public class BookLibraryImpl implements BookLibrary {

    private DocumentModel doc;

    /**
     * @param doc
     */
    public BookLibraryImpl(DocumentModel doc) {
        this.doc = doc;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.BookLibrary#setTitle(java.lang.String)
     */
    @Override
    public void setTitle(String title) throws ClientException {
        doc.setPropertyValue("dc:title", title);

    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.BookLibrary#getDoc()
     */
    @Override
    @JsonIgnore
    public DocumentModel getDoc() {
        return doc;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.BookLibrary#addBook(java.lang.String)
     */
    @Override
    public Book addBook(String name) throws ClientException {
        CoreSession session = doc.getCoreSession();
        DocumentModel docBook = session.createDocumentModel(doc.getPathAsString(), name,
                Book.DOCTYPE);
        docBook.setPropertyValue("dc:title", name);
        return docBook.getAdapter(Book.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.BookLibrary#getTitle()
     */
    @Override
    public String getTitle() throws ClientException {
        return doc.getProperty("dc:title").getValue(String.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.BookLibrary#getBooks()
     */
    @Override
    public List<Book> getBooks() throws ClientException {
        String query = String.format(
                "SELECT * FROM %s WHERE ecm:path STARTSWITH '%s'",
                Book.DOCTYPE, getDoc().getPathAsString());

        List<Book> result = new ArrayList<>();
        for(DocumentModel docBook : doc.getCoreSession().query(query)) {
            result.add(docBook.getAdapter(Book.class));
        }
        return result;
    }

    /* (non-Javadoc)
     * @see org.nuxeo.sample.BookLibrary#getBook(java.lang.String)
     */
    @Override
    public Book getBook(String bookTitle) throws ClientException {
        String query = String.format(
                "SELECT * FROM %s WHERE ecm:path STARTSWITH '%s' AND dc:title='%s'",
                Book.DOCTYPE, getDoc().getPathAsString(), bookTitle);
        DocumentModelList docs = doc.getCoreSession().query(query);
        if(docs.size() == 0) {
            return null;
        } else {
            return docs.get(0).getAdapter(Book.class);
        }
    }

}
