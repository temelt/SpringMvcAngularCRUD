(function () {

    var
        httpHeaders,
        message,
        as = angular.module('angularspring', []);

    as.config(function ($routeProvider, $httpProvider) {
        $routeProvider.when('/person', { controller: 'PersonController', templateUrl: 'person.html'});
        $routeProvider.when('/admin', { controller: 'AdminController', templateUrl: 'admin/admin.html'});

        $httpProvider.responseInterceptors.push(function ($q) {
            var setMessage = function (response) {
                if (response.data.text && response.data.type) {
                    message = {
                        text: response.data.text,
                        type: response.data.type,
                        show: true
                    };
                }
            };
            return function (promise) {
                return promise.then(
                    function (response) {
                        setMessage(response);
                        return response;
                    },
                    function (response) {
                        setMessage(response);
                        return $q.reject(response);
                    }
                );
            };
        });

        $httpProvider.responseInterceptors.push(function ($rootScope, $q) {
            return function (promise) {
                return promise.then(
                    function (response) {
                        return response;
                    },
                    function (response) {
                        if (response.status === 401) {
                            var deferred = $q.defer(),
                                req = {
                                    config: response.config,
                                    deferred: deferred
                                };
                            $rootScope.requests401.push(req);
                            $rootScope.$broadcast('event:loginRequired');
                            return deferred.promise;
                        }
                        return $q.reject(response);
                    }
                );
            };
        });
        httpHeaders = $httpProvider.defaults.headers;
    });


    as.run(function ($rootScope, $http, base64) {
        $rootScope.message = function () {
            return message;
        };

        $rootScope.requests401 = [];

        $rootScope.$on('event:loginRequired', function () {
            $('#login').modal('show');
        });

        $rootScope.$on('event:loginConfirmed', function () {
            var i,
                requests = $rootScope.requests401,
                retry = function (req) {
                    $http(req.config).then(function (response) {
                        req.deferred.resolve(response);
                    });
                };

            for (i = 0; i < requests.length; i += 1) {
                retry(requests[i]);
            }
            $rootScope.requests401 = [];
        });

        $rootScope.$on('event:loginRequest', function (event, username, password) {
            httpHeaders.common['Authorization'] = 'Basic ' + base64.encode(username + ':' + password);
            $http.get('action/user').success(function (data) {
                $rootScope.user = data;
                $rootScope.$broadcast('event:loginConfirmed');
            });
        });

        $rootScope.$on('event:logoutRequest', function () {
            httpHeaders.common['Authorization'] = null;
        });
    });

}());