initTree();

//初始化用户名称展示
initusername();
function initusername(){

    $.ajax({
        url:'../inituserphone',
        type:'post',
        data:{},
        success:function(data){
            $("#mingz").text(data);
        }
    })

}
function initTree(){
    $.ajax({
        url:"../findTree",
        type:"post",
        success:function(data){
            $('#myTree').treeview({
                data:data,
                onNodeSelected:function(event, node) {
                    $.addtabs({iframeHeight: 550});
                    $.addtabs.add({
                        id:node.id,
                        title:node.text,
                        url:"../"+node.href
                    });
                }
            })
        }
    })
}
