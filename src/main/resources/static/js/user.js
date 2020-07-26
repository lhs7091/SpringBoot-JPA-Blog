let index={
    init: function(){
        $("#btn-save").on("click", ()=>{ // function(){}, ()=> for this binding
            this.save();
        });
        $("#btn-login").on("click", ()=>{ 
            this.login();
        });
    },

    save: function(){
        //alert('call save function of save');
        // check ID on input tag html
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        //console.log(data);

        // 3 parameters change to json and request insert query by ajax communication
        // ajax call : default = ansync
        // return dataType: default = json
        $.ajax({
            // request sign up
            type: "POST",
            url: "/blog/api/user",
            data: JSON.stringify(data), // http body data
            contentType: "application/json; charset=utf-8", // body data type
            dataType: "json" //when comming response, String type, json format to javascript format
        }).done(function(resp){
            // success
            alert("complete of sign up");
            console.log(resp);
            location.href="/blog";
        }).fail(function(){
            // fail 
            alert(JSON.stringify(error));
        });
    },
    
        login: function(){

        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        };

        $.ajax({
            type: "POST",
            url: "/blog/api/user/login",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json" 
        }).done(function(resp){
            alert("complete of Login");
            console.log(resp);
            location.href="/blog";
        }).fail(function(){
            alert(JSON.stringify(error));
        });
    }
}

index.init();