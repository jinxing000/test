<html>
<body>
<h2>Hello World!</h2>
</body>

<script>
    $http({
        method:'post',
        url:'http://192.168.0.103:8080/golf/index',
        //url:'http://localhost:8080/golf/druid/index.html',
        data:{},
        headers:{'Content-Type': 'application/x-www-form-urlencoded'},
        transformRequest: function(obj) {
            var str = [];
            for(var p in obj){
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function(req){
        console.log(req);
    })

    $http.post(
            'http://192.168.0.103:8080/golf/index',
            '',
            { headers:{'Content-Type': 'application/x-www-form-urlencoded'}}
    ).then(function success(data){
        console.log(data);
    },function error(err){
        console.log(err);
    });

</script>
</html>


// this.$http.post('http://192.168.0.103:8080/golf/index').then(function(data){
//     // 响应成功回调
//     console.log(data)
// }, function(data){
//     // 响应错误回调
//     console.log(data)
// });