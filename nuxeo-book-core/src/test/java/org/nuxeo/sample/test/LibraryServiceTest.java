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

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.sample.BookLibrary;
import org.nuxeo.sample.LibraryException;
import org.nuxeo.sample.LibraryService;

import com.google.inject.Inject;

/**
 * @author dmetzler
 *
 */
@RunWith(FeaturesRunner.class)
@Features(CoreFeature.class)
@Deploy("nuxeo-book-core")
@RepositoryConfig(cleanup = Granularity.METHOD)
public class LibraryServiceTest {

    @Inject
    LibraryService ls;

    @Inject
    CoreSession session;

    @Test
    public void iCanCreateAndFetchAllLibraries() throws Exception {
        List<BookLibrary> libraries = ls.getAllLibraries(session);

        assertThat(libraries).isNotNull();
        assertThat(libraries.size()).isEqualTo(0);

        BookLibrary library = ls.createLibrary("My Library",session);
        assertThat(library).isNotNull();
        assertThat(library.getTitle()).isEqualTo("My Library");

        session.save();

        libraries = ls.getAllLibraries(session);
        assertThat(libraries.size()).isEqualTo(1);
    }

    @Test(expected=LibraryException.class)
    public void cantCreateTwoLibrariesWithSameTitle() throws Exception {
        String sameTitle = "My Library";
        ls.createLibrary(sameTitle, session);
        session.save();
        ls.createLibrary(sameTitle, session);
    }
}
