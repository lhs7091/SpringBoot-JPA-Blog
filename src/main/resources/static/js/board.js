let index={
    init: function(){
        $("#btn-board-save").on("click", ()=>{ // function(){}, ()=> for this binding
            this.save();
        });
        $("#btn-delete").on("click", ()=>{ 
            this.deleteById();
        });
        $("#btn-board-update").on("click", ()=>{ 
            this.update();
        });
        $("#btn-reply-save").on("click", ()=>{ 
            this.replySave();
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
    },
    
    update: function(){
        let id = $("#id").val();
        
        let data = {
        	title:$("title").val(),
        	content:$("#content").val()
        };

        $.ajax({
            // request sign up
            type: "PUT",
            url: "/api/board/"+id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json" 
        }).done(function(resp){
            // success
            alert("update complete");
            location.href="/";
        }).fail(function(){
            // fail 
            alert(JSON.stringify(error));
        });
    },
    
    replySave: function(){
        let data = {
        	userId: $("#userId").val(),
        	boardId: $("#boardId").val(),
            content: $("#reply-content").val()
        };  
        
        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data), 
            contentType: "application/json; charset=utf-8", 
            dataType: "json" 
        }).done(function(resp){
            // success
            alert("complete of Reply update");
            location.href=`/board/${data.boardId}`;
        }).fail(function(){
            // fail 
            alert(JSON.stringify(error));
        });
        
    },
    
    replyDelete: function(boardId, replyId){
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json" 
        }).done(function(resp){
            // success
            alert("complete of Reply Delete");
            location.href=`/board/${boardId}`;
        }).fail(function(){
            // fail 
            alert(JSON.stringify(error));
        });
    },        

}

index.init();