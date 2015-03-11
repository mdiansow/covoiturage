angular.module('CarModule', ['ui.bootstrap','ngCookies', 'ngRoute'])
.factory('UserService', function() {
  return {
      connect : 'false',
  };
})
// Event controller
.controller('GlobalController', function($scope, $route, $cookies, $cookieStore, $window, $http) {
    $scope.cities = [];

    $http({
        method: 'GET',
        url: '/rest/user/'+$cookies.username
    }).success (function (data){
        $scope.user = data;
        var date = new Date($scope.user.dateDeNaissance);
        $scope.user.dateDeNaissance = date;
    })

    if (typeof $cookies.username != "undefined"){
         $scope.connect = false;
    } else {
        $scope.connect = true;
    }
    $http({
        method: 'GET',
        url: '/rest/city/'
    }).success (function (data){
        $scope.cities = data;
    })

    $scope.events = [];
    $scope.eventSearch = [];

    $http({
        method: 'GET',
        url: '/rest/event/'
    }).success (function (data){
        $scope.events = data;
    })

    $scope.searchEvent = function (id){
        $http({
            method: 'POST',
            url: '/rest/event/search',
            data: $scope.eventSearch,
            headers : {
              'Content-Type' : 'application/json'
            }
            }).success (function (data){
            console.log("Event\t  "+data)
        })
    }
    $scope.joinEvent = function (id){
        $http({
            method: 'GET',
            url: '/rest/event/username='+$cookies.username+'&eventID='+id,
        }).success (function (data){
//            $scope.events = data;
            console.log("Event\t  "+data)
        })
    }

    $scope.event = {};

    $scope.editEvent = function(id) {

    if (id == 'new') {
        $scope.edit = true;
        $scope.incomplete = true;

        $http({
            method : 'POST',
            url : '/rest/event/create/'+$cookies.username,
            data : {"date":$scope.event.date,"depart": angular.fromJson($scope.event.depart),"arrivee":angular.fromJson($scope.event.arrivee),"prix":$scope.event.prix},
            headers : {
              'Content-Type' : 'application/json', 'ACTION' : 'ADD'
            }
        }).success(function(data){
            console.log(data)
        })
    } else {
        $scope.edit = false;
        $scope.date = $scope.events[id-1].date;
        $scope.depart = $scope.events[id-1].depart;
        $scope.arrivee = $scope.events[id-1].arrivee;
        $scope.prix = $scope.events[id-1].prix;
    }
    };

$scope.prenom = '';
$scope.nom = '';
$scope.password = '';
$scope.passw2 = '';
$scope.username = '';

$scope.users = [
    {id:1, prenom:'Hege'  , nom:"Pege", username:"HPege" },
    {id:2, prenom:'Kim'   , nom:"Pim" },
    {id:3, prenom:'Jack'  , nom:"Jones" },
    {id:4, prenom:'John'  , nom:"Doe" },
    {id:5, prenom:'Peter' , nom:"Pan" }
];

$scope.edit = true;
$scope.error = false;
$scope.incomplete = false;


$scope.user={prenom:''  , nom:'', username:'' };

$scope.editUser = function(id) {
    if (id == 'new') {
        $scope.edit = true;
        $scope.incomplete = true;
        $scope.users.push({id:$scope.users.length, prenom: $scope.user.prenom  , nom:$scope.user.nom, username:$scope.user.username });

        $http({
            method : 'POST',
            url : '/rest/user/create',
            data : $scope.user,
            headers : {
              'Content-Type' : 'application/json',
            }
        }).success(function(data){
            $scope.user = { };
            console.log(data)
        })
    } else {
        $scope.edit = false;
        $scope.user.prenom = $scope.users[id-1].prenom;
        $scope.user.nom = $scope.users[id-1].nom;
        $scope.user.username = $scope.users[id-1].username;
    }
};


$scope.$watch('password',function() {$scope.test();});
$scope.$watch('passw2',function() {$scope.test();});
$scope.$watch('prenom', function() {$scope.test();});
$scope.$watch('nom', function() {$scope.test();});
$scope.test = function() {
    if ($scope.password !== $scope.passw2) {
        $scope.error = true;
    } else {
        $scope.error = false;
    }
    $scope.incomplete = false;
    if ($scope.edit && (!$scope.user.prenom.length || !$scope.user.nom.length|| !$scope.user.password.length || !$scope.passw2.length)) {
        $scope.incomplete = true;
    }
};
    if (typeof $cookies.username != "undefined"){
         $scope.username = $cookies.username;
         $scope.connect = false;
    } else {
        $scope.connect = true;
    }
$scope.logout = function () {
    $cookieStore.remove("username");
    $scope.connect = true;
    $scope.user = '';
    $scope.username = '';
    $scope.username = '';
    console.log($cookies.username)
}

$scope.login = function(){
    $http({
        method: 'POST',
        url: '/rest/user/login',
        transformRequest: function(obj) {
            var str = [];
            for(var p in obj)
            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            return str.join("&");
        },
        data: {username: $scope.username, password: $scope.password},
        headers : {
            'Content-Type' : 'application/x-www-form-urlencoded',
        }
    }).success(function (data, status, headers, config) {
        // Reload page
        $route.reload();
        // any required additional processing here
        var results = [];
        results.data = data;
        results.headers = headers();
        results.status = status;
        results.config = config;
        if (typeof results.headers.username != "undefined") {
            $scope.connect =  false,
            $cookies.username = results.headers.username

            $scope.edit = false;

            $http({
                method: 'GET',
                url: '/rest/user/'+$cookies.username
            }).success (function (data){
                $scope.user = data;
                var date = new Date($scope.user.dateDeNaissance);
                $scope.user.dateDeNaissance = date;
            })
        }
    })
}
});