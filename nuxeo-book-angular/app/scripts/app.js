'use strict';


angular.module('nuxeoBookAngularApp', ["nxSession"])
  .value("defaultSchemas", ["dublincore","book"])
  .factory("nxSession", function(nxSessionFactory) {
    return nxSessionFactory({apiRootPath:"nuxeo/site/api"})
  })

  //Routing configuration
  .config(function($routeProvider) {
    $routeProvider.
      when('/', {
        controller:'ListCtrl', 
        templateUrl:'views/list.html'
      }).
      when('/edit/:docId', {
        controller:'EditCtrl', 
        templateUrl:'views/detail.html'
      }).
      when('/new', {
        controller:'CreateCtrl', 
        templateUrl:'views/detail.html'
      }).
      otherwise({redirectTo:'/'});
  });


