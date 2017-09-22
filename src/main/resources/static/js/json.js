app.factory('Product', function($resource){
    return $resource('/rest/product/find');
});

app.controller('product', function($scope, Product){
    $scope.product = {};

    var data = {
        sku: 'MP-001',
        name: 'Milk'
    };

    $scope.send = function(){
       $scope.product = Product.save({},data);
    };
});