# Nuxeo book sample

[![Build Status](https://travis-ci.org/dmetzler/nuxeo-book.png?branch=master)](https://travis-ci.org/dmetzler/nuxeo-book)

This sample Nuxeo project contains the following : 

 * nuxeo-book-core : The Core types definitions which defines the Book and the Library document types 
 * nuxeo-book-webengine : a WebEngine application that allows to manager libraries and book. 
 * nuxeo-book-angular : a Yeoman Angular app that takes advantage of the Nuxeo API


## How to use

First build the two bundles with Maven :

    
    $ mvn clean install


Copy the two bundles in the `$NUXEO_HOME/nxserver/bundles` and start Nuxeo.

You should now have an *Library* application at [http://localhost:8080/nuxeo/site/library]().

## Angular application

A prerequisite is to have a library called `books` in your Nuxeo repository. You can create it with the WebEngine application.

A single page app is located in `nuxeo-book-angular`. To build it you have to install `npm`, `grunt` and `bower` then in the directory run 

    $ npm install
    $ bower install
    $ grunt server

Your browser should open and you should be able to create Books in you library.

## Disclaimer

This sample is not intended to be a full featured application and is here only to show basic samples of Nuxeo code in Java and Javascript. 