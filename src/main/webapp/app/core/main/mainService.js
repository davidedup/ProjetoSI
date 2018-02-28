app.factory("mainService", function ($http,BASE_SERVER_URL) {

    return {
        getAllProducts: _getAllProducts,
        updateProductById: _updateProductById
    };
    
    function _getAllProducts() {
        return $http.get("/produto/")
    }

    function _updateProductById(id, data) {
        return $http.put("/produto/" + id, data)
    }
    
});