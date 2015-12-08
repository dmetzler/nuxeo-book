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

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.rest.DocumentObject;
import org.nuxeo.ecm.webengine.WebException;
import org.nuxeo.ecm.webengine.forms.FormData;
import org.nuxeo.ecm.webengine.model.Resource;
import org.nuxeo.ecm.webengine.model.WebObject;

/**
 * @author dmetzler
 */
@WebObject(type = "Library")
@Produces("text/html;charset=UTF-8")
public class LibraryObject extends DocumentObject {

    public BookLibrary getLibrary() {
        return doc.getAdapter(BookLibrary.class);
    }

    @GET
    @Produces("application/json")
    public Object doGetJSon() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(getLibrary());
        } catch (IOException e) {
            throw WebException.wrap(e);
        }
    }

    @Override
    @POST
    public Response doPost() {
        FormData form = getContext().getForm();
        String title = form.getString("title");
        Book book = getLibrary().addBook(title);
        book.setAuthor(form.getString("author"));
        CoreSession session = getContext().getCoreSession();
        session.createDocument(book.getDocument());

        return redirect(getPath());

    }

    @Override
    @Path("{bookTitle}")
    public Resource traverse(@PathParam("bookTitle") String bookTitle) {
        Book book;
        book = getLibrary().getBook(bookTitle);
        return newObject("Book", book.getDocument());

    }

}
