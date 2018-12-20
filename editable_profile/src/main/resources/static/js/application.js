var App = angular.module("App", ["ngResource", "ui.bootstrap"]);

App.config(function ($resourceProvider) {
    $resourceProvider.defaults.stripTrailingSlashes = false;
});

App.factory('Api', ['$resource', function ($resource) {
    return {
        profile: $resource('/api/profile/:id/', {id: '@id'}, {
            update: {method: 'PUT'}
        }),
        gender: $resource('/api/attributes/gender', {}, {}),
        ethnicity: $resource('/api/attributes/ethnicity', {}, {}),
        religion: $resource('/api/attributes/religion', {}, {}),
        figure: $resource('/api/attributes/figure', {}, {}),
        maritalStatus: $resource('/api/attributes/maritalStatus', {}, {}),
        cities: $resource('/api/attributes/cities', {}, {})

    }
}]);

App.controller("RootController", ["$scope", "$filter", "Api", function ($scope, $filter, Api) {

    $scope.attrs = {};

    Api.profile.query().$promise.then(function (resp) {
        $scope.profiles = resp;
    });
    Api.gender.query().$promise.then(function (resp) {
        $scope.attrs['gender'] = resp;
    });
    Api.ethnicity.query().$promise.then(function (resp) {
        $scope.attrs['ethnicity'] = resp;
    });
    Api.religion.query().$promise.then(function (resp) {
        $scope.attrs['religion'] = resp;
    });
    Api.figure.query().$promise.then(function (resp) {
        $scope.attrs['figure'] = resp;
    });
    Api.maritalStatus.query().$promise.then(function (resp) {
        $scope.attrs['maritalStatus'] = resp;
    });
    Api.cities.query().$promise.then(function (resp) {
        $scope.attrs['cities'] = resp;
    });

    $scope.save = function (draft) {
        $scope.vMessage = null;
        $scope.vErrors = null;
        var promise;
        if (draft.id) {
            promise = Api.profile.update(draft).$promise.then(function (resp) {
                draft = resp;
                $("#newProfileModal").modal('hide');
            })
        } else {
            promise = Api.profile.save(draft).$promise.then(function (resp) {
                $scope.profiles.push(resp);
                $("#newProfileModal").modal('hide');
            })
        }
        promise.catch(function (resp) {
            if (resp.data.type === "ValidationModelException") {
                $scope.vMessage = resp.data.message;
                $scope.vErrors = resp.data.errors;
            }
        });

    };

    $scope.newProfileForm = function () {
        $scope.draft = {};
        $("#newProfileModal").modal('show');
    };

    $scope.editForm = function (profile) {
        $scope.draft = profile;
        $("#newProfileModal").modal('show');
    };

    $scope.remove = function (profile) {
        profile.$remove({id: profile.id}).then(function () {
            $scope.profiles = $scope.profiles.filter(function (el) {
                return el.id !== profile.id;
            });

        });
    };

    $scope.heightToString = function (value) {
        return Math.round((value / 10) * 100) / 100 + " cm."
    };
}]);

App.directive("fileread", [function () {
    return {
        scope: {
            fileread: "="
        },
        link: function (scope, element, attributes) {
            element.bind("change", function (changeEvent) {
                var reader = new FileReader();
                reader.onload = function (loadEvent) {
                    scope.$apply(function () {
                        scope.fileread = loadEvent.target.result.split(",")[1];
                    });
                };
                reader.readAsDataURL(changeEvent.target.files[0]);
            });
        }
    }
}]).directive('timestamp', function () {
        return {
            restrict: 'A',
            require: 'ngModel',
            link: function (scope, element, attrs, ngModel) {
                ngModel.$formatters.push(function (value) {
                    return new Date(value);
                });
            }
        };
    });