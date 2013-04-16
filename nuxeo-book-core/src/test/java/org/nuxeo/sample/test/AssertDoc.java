package org.nuxeo.sample.test;

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

import static org.junit.Assert.fail;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.io.Serializable;

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;

/**
 * This class can be used to test a doctype for given properties : * schemas *
 * facets * properties with a fluent API
 *
 * <code>
 * assertThatDocType("MyDocType", "thetestname", session)
 *           .withSchemas("common","dublincore","uid")
 *           .withFacet("Commentable","Versionable","Indexable")
 *           .withProperty("myschema:myprop")
 *           .canBeCreated();
 * </code>
 *
 * @author dmetzler
 *
 */
public class AssertDoc {

    private DocumentModel doc;

    private final CoreSession session;

    /**
     * Constructor : use
     * {@link AssertDoc#assertThatDocType(String, String, CoreSession)}
     *
     * @param doc
     * @param session
     */
    private AssertDoc(DocumentModel doc, CoreSession session) {
        this.doc = doc;
        this.session = session;
    }

    /**
     * @param the name of the property
     * @return
     */
    public AssertDoc withProperty(String propertyName) {
        return withProperty(propertyName, "dummyValue");
    }

    /**
     * @param propertyName
     * @param value the test value to set
     * @return self
     */
    AssertDoc withProperty(String propertyName, Serializable value) {
        try {
            doc.setPropertyValue(propertyName, value);
        } catch (ClientException e) {
            fail("Unable to set property [" + propertyName + "] with value "
                    + value + " : " + e.getMessage());
        }
        return this;
    }

    /**
     * Test if the session can create the document
     *
     * @return self
     */
    public AssertDoc canBeCreated() {
        try {
            doc = session.createDocument(doc);
        } catch (ClientException e) {
            fail("Unable to create document : " + e.getMessage());
        }
        return this;
    }

    /**
     *
     * @param docType The doctype to test
     * @param name The name of the test doctype that will be created at the root
     *            of the repository
     * @param session the session that will be tested
     * @return an AssertDoc
     */
    public static AssertDoc assertThatDocType(String docType, String name,
            CoreSession session) {
        DocumentModel doc = null;
        try {
            doc = session.createDocumentModel("/", name, docType);
        } catch (ClientException e) {
            fail("Unable to created docType [" + docType + "] : "
                    + e.getMessage());
        }
        return new AssertDoc(doc, session);
    }

    /**
     * Test if the doc has a given schema
     *
     * @param the schema's name
     * @return self
     */
    public AssertDoc withSchema(String schemaName) {
        try {
            if (doc.getPart(schemaName) == null) {
                fail("Document doesn't have schema [" + schemaName + "]");
            }
        } catch (ClientException e) {
            fail("Document doesn't have schema [" + schemaName + "] : "
                    + e.getMessage());
        }
        return this;
    }

    /**
     * Test if the doc has given schemas
     *
     * @param schemas list of schemas to check
     * @return
     */
    public AssertDoc withSchemas(String... schemas) {
        for (String schema : schemas) {
            withSchema(schema);
        }
        return this;
    }

    /**
     * Test if the doc has given facets
     *
     * @param facets list of facets to check
     * @return
     */
    public AssertDoc withFacet(String... facets) {
        for (String facet : facets) {
            withFacet(facet);
        }
        return this;
    }

    /**
     * Test if the doc has a given facet
     *
     * @param facet
     * @return
     */
    public AssertDoc withFacet(String facet) {
        if (!doc.hasFacet(facet)) {
            fail("Document doesn't have facet [" + facet + "]");
        }
        return this;
    }

    /**
     * Test if the doc has the given lifecycle
     * @param the exepected lifecycle
     */
    public AssertDoc hasLifecycle(String lifecycleName) {
        try {
            assertThat(doc.getLifeCyclePolicy(), is(lifecycleName));
        } catch (ClientException e) {
            fail("Document doesn't have any lifecycle associated. Try to call canBeCreated() before : "
                    + e.getMessage());
        }
        return this;

    }

}