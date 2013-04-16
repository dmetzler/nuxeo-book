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

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.core.api.UnrestrictedSessionRunner;
import org.nuxeo.runtime.model.DefaultComponent;

/**
 * @author dmetzler
 *
 */
public class LibraryComponent extends DefaultComponent implements
        LibraryService {

    public static String NAME = "org.nuxeo.sample.library.service";

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.LibraryService#getAllLibraries()
     */
    @Override
    public List<BookLibrary> getAllLibraries(CoreSession session)
            throws ClientException {

        String query = String.format(
                "SELECT * FROM %s WHERE ecm:currentLifeCycleState != 'deleted' AND ecm:isCheckedInVersion = 0",
                BookLibrary.DOCTYPE);

        DocumentModelList docs = session.query(query);
        List<BookLibrary> libraries = new ArrayList<>();
        for (DocumentModel doc : docs) {
            libraries.add(doc.getAdapter(BookLibrary.class));
        }
        return libraries;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.nuxeo.sample.LibraryService#createLibrary(java.lang.String,
     * org.nuxeo.ecm.core.api.CoreSession)
     */
    @Override
    public BookLibrary createLibrary(String libraryTitle, CoreSession session)
            throws ClientException {

        if (getLibrary(libraryTitle, session) != null) {
            throw new LibraryException("Library already exists");

        } else {

            DocumentModel doc = session.createDocumentModel(
                    getLibraryRoot(session).getPathAsString(), libraryTitle,
                    BookLibrary.DOCTYPE);

            BookLibrary library = doc.getAdapter(BookLibrary.class);
            library.setTitle(libraryTitle);

            doc = session.createDocument(doc);
            return doc.getAdapter(BookLibrary.class);
        }

    }

    /**
     * @param libraryTitle
     * @return
     */
    private BookLibrary getLibrary(String libraryTitle, CoreSession session)
            throws ClientException {
        String query = String.format(
                "SELECT * FROM %s WHERE ecm:currentLifeCycleState != 'deleted' AND ecm:isCheckedInVersion = 0 AND dc:title = '%s'",
                BookLibrary.DOCTYPE, libraryTitle);

        DocumentModelList docs = session.query(query);
        if (docs.size() == 0) {
            return null;
        } else {
            return docs.get(0).getAdapter(BookLibrary.class);
        }

    }

    /**
     * @return
     * @throws ClientException
     */
    private DocumentModel getLibraryRoot(CoreSession session)
            throws ClientException {
        String rootPath = "/default-domain/libraryRoot";
        if (!session.exists(new PathRef(rootPath))) {
            UnrestrictedSessionRunner rootCreator = new UnrestrictedSessionRunner(
                    session) {

                @Override
                public void run() throws ClientException {
                    DocumentModel doc;
                    if (!session.exists(new PathRef("/default-domain"))) {
                        doc = session.createDocumentModel("/",
                                "default-domain", "Domain");
                        doc.setPropertyValue("dc:title", "Default domain");
                        doc = session.createDocument(doc);

                    }

                    doc = session.getDocument(new PathRef("/default-domain"));

                    doc = session.createDocumentModel(doc.getPathAsString(),
                            "libraryRoot", "Workspace");
                    doc.setPropertyValue("dc:title", "Libraries root");
                    doc = session.createDocument(doc);
                    session.save();
                }
            };

            rootCreator.runUnrestricted();
        }

        return session.getDocument(new PathRef(rootPath));
    }
}
