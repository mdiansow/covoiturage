angular.module('CarModule', ['ui.bootstrap','ngCookies'])

.controller('TabsDemoCtrl', function ($scope, $window) {
  $scope.tabs = [
    { title:'Add a car', content:'Dynamic content 1' },
    { title:'Create new event', content:'Dynamic content 2' }
  ];

  $scope.alertMe = function() {
    setTimeout(function() {
      $window.alert('You\'ve selected the alert tab!');
    });
  };
})
.controller('UserController', function userController($scope, $http) {
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
        console.log("Create user " + id + " " +$scope.user.prenom)

        $http({
            method : 'POST',
            url : '/rest/user/create',
            data : $scope.user,
            headers : {
              'Content-Type' : 'application/json', 'ACTION' : 'ADD'
            }
        }).success(function(data){
            $scope.user = {prenom:''  , nom:'', username:'' };
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
})

.controller('loginController',function($scope, $cookies, $cookieStore, $window, $http) {

    if (typeof $cookies.username != "undefined"){
         $scope.username = $cookies.username;
         $scope.connect = false;
    } else {
        $scope.connect = true;
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
        // any required additional processing here
        var results = [];
        results.data = data;
        results.headers = headers();
        results.status = status;
        results.config = config;
        if (typeof results.headers.username != "undefined") {
            $scope.connect =  false,
            $cookies.username = results.headers.username
        }
        console.log(results.headers.username)
        console.log(results.data)
    })
}
});