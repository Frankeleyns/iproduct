$(function a() {
	$("[data-hide]").click(function(e){
		$(this).parents("div").find("div").toggle(700);
	});
	
	
	//删除一个对象
	$(document).on("click","[del-x]",function(){
        var del_div = $(this).parents("div");
        var del_id =  $(this).parents("div").attr("data-id");
        console.log(del_id);
		var url = "/product/"+del_id+"/delete";
		console.log(url);
        $.get(url,function(e){
        	if(e == "success"){
              del_div.hide("slow");
        	  setTimeout(function(){
        		  del_div.remove();
        		  location.reload();
        	  },2000);
        	}
        });
	});
	
	var ids = []; //创建一个数组用于存放需要传输的id
	
	Array.prototype.remove = function (array){   //自定义数组(Array)原型函数remove用于移除数组中的一个指定元素
		var index = array.indexOf(array);
		if(index > -1){
			this.splice(index,1);
		}
	}
	
	//选中出现批量操作
	var check = 0; //计算点击复选框的次数
	$(document).on("click","input[type='checkbox']",function(){
		var pro_id = $(this).parent().attr("data-id");
		if(this.checked){
			$(".hide").show("fast");
            ids.push(pro_id);
			check++;
		}else{
			check--;
			ids.remove(pro_id);
			if(check == 0){
				$(".hide").hide("fast");
			}
		}
	});
	
	
	$(document).on("click","[data-files-delete]",function(){
		  var url = "/product/deletes";
		  var json_id = JSON.stringify(ids);
          console.log(json_id);
          var data = {
        		  json_id:json_id
          }
          $.post(url,data,function(e){
        	  if(e != null && e == "success"){
        		  for(var i=0;i<ids.length;i++){
                	  var pro_div = $('[data-id="' +ids[i]+ '"]');
                	  pro_div.hide("slow");
                  }
                  $(".hide").hide("fast");
 
                  setTimeout(function(){
                	  location.reload();
                  },1500);
        	  }else{
        		  alert("传输错误");
        	  }
          });
       
	});
	
		
})
