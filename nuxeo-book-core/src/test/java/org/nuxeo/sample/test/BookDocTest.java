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

import com.google.inject.Inject;
import static org.nuxeo.sample.test.AssertDoc.assertThatDocType;

/**
 * @author dmetzler
 *
 */

@RunWith(FeaturesRunner.class)
@Features(CoreFeature.class)
@Deploy("nuxeo-book-core")
@RepositoryConfig(cleanup = Granularity.METHOD)
public class BookDocTest {
    @Inject
    CoreSession session;

    @Test
    public void iCanCreateABook() throws Exception {
        DocumentModel doc = session.createDocumentModel("/", "myBook", "Book");

        doc.setPropertyValue("dc:title", "My Book");

        doc = session.createDocument(doc);

        session.save();

        assertTrue(session.exists(doc.getRef()));

    }

    @Test
    public void canSetAnISBNonABook() throws Exception {
        DocumentModel doc = session.createDocumentModel("/", "myBook", "Book");
        doc.setPropertyValue("bk:isbn", "12345");
        doc = session.createDocument(doc);
        session.save();

        assertTrue(session.exists(doc.getRef()));
        doc = session.getDocument(doc.getRef());
        assertEquals("myBook", doc.getName());

        assertEquals("12345", doc.getProperty("bk:isbn").getValue(String.class));

        assertThatDocType("Book", "myBook", session).withSchemas("dublincore",
                "common", "book").canBeCreated();
    }
}
