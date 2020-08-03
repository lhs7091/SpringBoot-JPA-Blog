let index={
    init: function(){
        $("#btn-board-save").on("click", ()=>{ // function(){}, ()=> for this binding
            this.save();
        });
        $("#btn-delete").on("click", ()=>{ // function(){}, ()=> for this binding
            this.deleteById();
        });
    },

    save: function(){
        //alert('call save function of save');
        // check ID on input tag html
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        //console.log(data);

        $.ajax({
            // request sign up
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), // http body data
            contentType: "application/json; charset=utf-8", // body data type
            dataType: "json" //when comming response, String type, json format to javascript format
        }).done(function(resp){
            // success
            alert("save complete");
            //console.log(resp);
            location.href="/";
        }).fail(function(){
            // fail 
            alert(JSON.stringify(error));
        });
    },
    
    deleteById: function(){
		var id = $("#id").text();
		
        $.ajax({
            // request sign up
            type: "DELETE",
            url: "/api/board/"+id,
            dataType: "json",
            contentType: "application/json; charset=utf-8" 
        }).done(function(resp){
            // success
            alert("Delete complete");
            location.href="/";
        }).fail(function(){
            // fail 
            alert(JSON.stringify(error));
        });
    }    

}

index.init();