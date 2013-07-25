'use strict';

angular.module('nuxeoBookAngularApp')
  .controller('ListCtrl', function ($scope, nxSession, defaultSchemas) {
    $scope.books = nxSession.getDocument("/books").getChildren(defaultSchemas);
  })

  .controller("EditCtrl", function($scope, nxSession, $routeParams, $location, defaultSchemas) {
    $scope.doc = nxSession.getDocument($routeParams.docId).fetch(defaultSchemas)

    $scope.save = function() {
      $scope.doc.save();
      $location.path("/")
    }

    $scope.destroy = function() {
      $scope.doc.delete();
      $location.path("/");
    }
    
    
  })
  .controller("CreateCtrl", function($scope, nxSession, $location) {
    $scope.doc = { type:"Book", properties: {}}

    $scope.save = function() {      
      $scope.doc.name = $scope.doc.properties["dc:title"];
      nxSession.createDocument("/books", $scope.doc)
      $location.path("/")
    }
    
  });    
  
