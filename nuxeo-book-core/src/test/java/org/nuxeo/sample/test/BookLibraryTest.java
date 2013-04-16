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
import static org.junit.Assert.assertTrue;

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
import org.nuxeo.sample.BookLibrary;

import com.google.inject.Inject;

/**
 * @author dmetzler
 *
 */
@RunWith(FeaturesRunner.class)
@Features(CoreFeature.class)
@Deploy("nuxeo-book-core")
@RepositoryConfig(cleanup = Granularity.METHOD)
public class BookLibraryTest {

    @Inject
    CoreSession session;


    @Test
    public void iCanCreateABookLibrary() throws Exception {
        DocumentModel doc = session.createDocumentModel("/", "library", "BookLibrary");
        doc.setPropertyValue("dc:title", "My library");

        doc = session.createDocument(doc);
        session.save();

        assertTrue(session.exists(doc.getRef()));



    }

    @Test
    public void iCanAddBookToBookLibrary() throws Exception {
        DocumentModel doc = session.createDocumentModel("/", "library", "BookLibrary");
        doc.setPropertyValue("dc:title", "My library");
        doc = session.createDocument(doc);
        session.save();

        doc = session.createDocumentModel("/library","myBook","Book");
        doc.setPropertyValue("dc:title", "My book");
        doc = session.createDocument(doc);
        session.save();

    }

    @Test
    public void iHaveAnAdapterOnLibrary() throws Exception {
        DocumentModel doc = session.createDocumentModel("/", "library", "BookLibrary");
        BookLibrary library = doc.getAdapter(BookLibrary.class);

        assertNotNull(library);


        library.setTitle("My library");

        session.createDocument(library.getDoc());

        assertEquals("My library", library.getTitle());

        Book book = library.addBook("myBook");
        book.setTitle("My Book");
        session.createDocument(book.getDocument());



    }


}
