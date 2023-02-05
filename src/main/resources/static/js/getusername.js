function login_username(){
    $.ajax({
        url: "/get_username",
        type: "GET",
        dataType: "JSON",
        success: function(json) {
            if (json!=null){
                console.log($("body > header > div > div.col-md-9.top-item > ul > li:nth-child(9) > a").html('<span class="fa fa-user"></span>'+'&nbsp;'+"欢迎您:"+json));
            }

        }
    });
}