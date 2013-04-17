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

import org.joda.time.DateTime;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;

/**
 * @author dmetzler
 *
 */
public interface Book {

    String DOCTYPE = "Book";

    /**
     * @param string
     * @throws ClientException
     */
    void setIsbn(String string) throws ClientException;

    /**
     * @param string
     */
    void setAuthor(String string) throws ClientException;;

    /**
     * @param i
     */
    void setRating(int i) throws ClientException;;

    /**
     * @param dateTime
     */
    void setPublicationDate(DateTime dateTime) throws ClientException;;

    /**
     * @return
     */
    String getIsbn() throws ClientException;;

    /**
     * @return
     */
    String getAuthor() throws ClientException;;

    /**
     * @return
     */
    int getRating() throws ClientException;;

    String getDescription() throws ClientException;

    void setDescription(String description) throws ClientException;

    /**
     * @return
     */
    DateTime getPublicationDate() throws ClientException;

    /**
     * @param string
     * @throws ClientException
     */
    void setTitle(String string) throws ClientException;

    /**
     * @return
     */
    DocumentModel getDocument();

    /**
     * @return
     * @throws ClientException
     */
    String getTitle() throws ClientException;


}
