let index={
    init: function(){
        $("#btn-save").on("click", ()=>{ // function(){}, ()=> for this binding
            this.save();
        });
        
        $("#btn-update").on("click", ()=>{ 
            this.update();
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
            url: "/auth/signUpProc",
            data: JSON.stringify(data), // http body data
            contentType: "application/json; charset=utf-8", // body data type
            dataType: "json" //when comming response, String type, json format to javascript format
        }).done(function(resp){
            // success
            alert("complete of sign up");
            console.log(resp);
            location.href="/";
        }).fail(function(){
            // fail 
            alert(JSON.stringify(error));
        });
    },
    update: function(){
        let data = {
        	id: $("#id").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        $.ajax({
            // request sign up
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data), 
            contentType: "application/json; charset=utf-8", 
            dataType: "json" 
        }).done(function(resp){
            // success
            alert("complete of user information updated");
            console.log(resp);
            location.href="/";
        }).fail(function(){
            // fail 
            alert(JSON.stringify(error));
        });
    }    
        

}

index.init();