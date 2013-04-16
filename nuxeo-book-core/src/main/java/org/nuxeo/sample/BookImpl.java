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

import java.util.Calendar;

import org.joda.time.DateTime;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;

/**
 * @author dmetzler
 *
 */
public class BookImpl implements Book {

    private DocumentModel doc;

    /**
     * @param doc
     */
    public BookImpl(DocumentModel doc) {
        this.doc = doc;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.Book#setIsbn(java.lang.String)
     */
    @Override
    public void setIsbn(String isbn) throws ClientException {
        doc.setPropertyValue("bk:isbn", isbn);

    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.Book#setAuthor(java.lang.String)
     */
    @Override
    public void setAuthor(String author) throws ClientException {
        doc.setPropertyValue("bk:author", author);

    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.Book#setRating(int)
     */
    @Override
    public void setRating(int rating) throws ClientException {
        doc.setPropertyValue("bk:rating", rating);

    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.Book#setPublicationDate(org.joda.time.DateTime)
     */
    @Override
    public void setPublicationDate(DateTime dateTime) throws ClientException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime.toDate());
        doc.setPropertyValue("bk:publication_date", cal);

    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.Book#getIsbn()
     */
    @Override
    public String getIsbn() throws ClientException {
        return doc.getProperty("bk:isbn").getValue(String.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.Book#getAuthor()
     */
    @Override
    public String getAuthor() throws ClientException {
        return doc.getProperty("bk:author").getValue(String.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.Book#getRating()
     */
    @Override
    public int getRating() throws ClientException {
        return doc.getProperty("bk:rating").getValue(Integer.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.Book#getPublicationDate()
     */
    @Override
    public DateTime getPublicationDate() throws ClientException {
        return new DateTime(doc.getProperty("bk:publication_date").getValue(
                Calendar.class));
    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.Book#setTitle(java.lang.String)
     */
    @Override
    public void setTitle(String title) throws ClientException {
        doc.setPropertyValue("dc:title", title);

    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.Book#getDocument()
     */
    @Override
    public DocumentModel getDocument() {
        return doc;
    }

    /* (non-Javadoc)
     * @see org.nuxeo.sample.Book#getTitle()
     */
    @Override
    public String getTitle() throws ClientException {
        return doc.getProperty("dc:title").getValue(String.class);
    }

}
