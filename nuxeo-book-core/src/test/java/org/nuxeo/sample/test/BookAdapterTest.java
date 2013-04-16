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
package org.nuxeo.sample.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.sample.Book;

import com.google.inject.Inject;

/**
 * @author dmetzler
 *
 */
@RunWith(FeaturesRunner.class)
@Features(CoreFeature.class)
@Deploy("nuxeo-book-core")
@RepositoryConfig(cleanup = Granularity.METHOD)

public class BookAdapterTest {


    @Inject
    CoreSession session;


    @Test
    public void iCanGetAnAdapter() throws Exception {

        DocumentModel doc = session.createDocumentModel("/","myBook","Book");

        Book book = doc.getAdapter(Book.class);

        assertNotNull(book);


        book.setTitle("My book");
        book.setIsbn("12345");
        book.setAuthor("Damien Metzler");
        book.setRating(4);
        book.setPublicationDate(new DateTime(2013, 4, 13, 0, 0, 0, 0));

        doc = session.createDocument(doc);

        doc = session.getDocument(doc.getRef());

        assertEquals("My book",book.getTitle());
        assertEquals("12345", book.getIsbn());
        assertEquals("Damien Metzler", book.getAuthor());
        assertEquals(4,book.getRating());
        assertEquals("13/04/2013", book.getPublicationDate().toString("dd/MM/YYYY"));






    }
}
