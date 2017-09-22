app.factory('Cart', function($resource){
    return $resource('/rest/cart', {sku: '@sku', quantity: '@quantity'},{
        update: {method: 'PUT'}
    });
});

app.controller('cart', function($scope, Cart, cart_data){
    var cart_default = {'items':[], 'itemCount':0, 'total':0.0};
    $scope.cart = cart_data != null ? cart_data : cart_default;

    $scope.add = function(sku){
        $scope.cart = Cart.save({sku:sku});
    };

    $scope.add = function(sku, quantity){
        $scope.cart = Cart.save({sku:sku, quantity:quantity});
    };

    $scope.update = function(sku, quantity){
        $scope.cart = Cart.update({sku:sku, quantity:quantity});
    };

    $scope.remove = function(sku){
        $scope.cart = Cart.remove({sku:sku});
    };
});