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

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.adapter.DocumentAdapterFactory;

/**
 * @author dmetzler
 *
 */
public class BookFactory implements DocumentAdapterFactory {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.nuxeo.ecm.core.api.adapter.DocumentAdapterFactory#getAdapter(org.
     * nuxeo.ecm.core.api.DocumentModel, java.lang.Class)
     */
    @Override
    public Object getAdapter(DocumentModel doc, Class<?> itf) {

        if ("Book".equals(doc.getType())) {
            return new BookImpl(doc);
        } else if ("BookLibrary".equals(doc.getType())) {
            return new BookLibraryImpl(doc);
        }

        return null;
    }

}
