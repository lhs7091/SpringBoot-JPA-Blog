let index={
    init: function(){
        $("#btn-save").on("click", ()=>{
            this.save();
        });
    },

    save: function(){
        //alert('call save function of save');
        // check ID on input tag html
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        //console.log(data);

        // 3 parameters change to json and request insert query by ajax communication
        $.ajax().done().fail();
    }
}

index.init();