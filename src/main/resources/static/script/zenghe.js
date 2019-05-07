initTree();
function initTree(){
    $.ajax({
        url:"../zenghe",
        type:"post",
        success:function(data){
            $('#myTree').treeview({
                data:data,
                onNodeSelected:function(event, node) {
                    $.addtabs({iframeHeight: 820});
                    $.addtabs.add({
                        id:node.id,
                        title:node.text,
                        url:node.href
                    });
                }
            })
        }
    })
}
